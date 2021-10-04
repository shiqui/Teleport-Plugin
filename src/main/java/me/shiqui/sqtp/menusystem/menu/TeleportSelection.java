package me.shiqui.sqtp.menusystem.menu;

import me.shiqui.sqtp.Sqtp;
import me.shiqui.sqtp.menusystem.Menu;
import me.shiqui.sqtp.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class TeleportSelection extends Menu {

    public ArrayList<Player> onlinePlayers = new ArrayList<>(plugin.getServer().getOnlinePlayers());

    public TeleportSelection(PlayerMenuUtility playerMenuUtility, Sqtp plugin) {
        super(playerMenuUtility, plugin);
    }

    @Override
    public String getMenuName() {
        return ChatColor.BOLD+""+ChatColor.BLUE+"Select Your Target";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();

        switch (e.getCurrentItem().getType()){
            case PLAYER_HEAD:
                SkullMeta meta = (SkullMeta) e.getCurrentItem().getItemMeta();
                new TeleportChoice(Sqtp.getPlayerMenuUtility(p),plugin,(Player)meta.getOwningPlayer()).open();
        }

    }

    @Override
    public void setMenuItems() {
        Player p = playerMenuUtility.getOwner();

        int count = 0;
        for (Player onlinePlayer : onlinePlayers) {
                if (!Sqtp.tptRequests.containsKey(onlinePlayer.getUniqueId()) && !Sqtp.tphRequests.containsKey(onlinePlayer.getUniqueId()) && onlinePlayer != p) {
                    inventory.setItem(count, getHead(onlinePlayer));
                    count++;
                }

            if(count==45) {break;}
        }

    }

    public static ItemStack getHead(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metahead = (SkullMeta) head.getItemMeta();
        metahead.setDisplayName(ChatColor.BLUE+player.getName());
        metahead.setOwningPlayer(player);
        head.setItemMeta(metahead);
        return head;
    }



}
