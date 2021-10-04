package me.shiqui.sqtp.menusystem.menu;

import me.shiqui.sqtp.Sqtp;
import me.shiqui.sqtp.menusystem.Menu;
import me.shiqui.sqtp.menusystem.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RequestDecision extends Menu {

    public RequestDecision(PlayerMenuUtility playerMenuUtility, Sqtp plugin) {
        super(playerMenuUtility, plugin);
    }

    @Override
    public String getMenuName() {
        return ChatColor.BOLD+""+ChatColor.BLUE+"Confirmation Menu";
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
                break;
            case LIME_STAINED_GLASS_PANE:
                p.performCommand("tpa");
                p.closeInventory();
                break;
            case RED_STAINED_GLASS_PANE:
                p.performCommand("tpd");
                p.closeInventory();
                break;

        }

    }

    @Override
    public void setMenuItems() {

        Player p = playerMenuUtility.getOwner();

        if(Sqtp.tptRequests.containsKey(p.getUniqueId())){
            ItemStack item1 = getHead(Sqtp.tptRequests.get(p.getUniqueId()),"tpt");
            inventory.setItem(5,item1);
        }else if(Sqtp.tphRequests.containsKey(p.getUniqueId())){
            ItemStack item1 = getHead(Sqtp.tphRequests.get(p.getUniqueId()),"tph");
            inventory.setItem(5,item1);
        }


        ItemStack yes = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta metayes = yes.getItemMeta();
        metayes.setDisplayName(" ");
        List<String> loreyes = new ArrayList<>();
        loreyes.add(ChatColor.DARK_GREEN + "Accept");
        metayes.setLore(loreyes);
        yes.setItemMeta(metayes);
        for (int i =0; i < 3; i++){
            for (int j =0; j < 4; j++){
                inventory.setItem(j+i*9+18, yes);
            }
        }

        ItemStack no = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta metano = no.getItemMeta();
        metano.setDisplayName(" ");
        List<String> loreno = new ArrayList<>();
        loreno.add(ChatColor.DARK_RED + "Deny");
        metano.setLore(loreno);
        no.setItemMeta(metano);
        for (int i =0; i < 3; i++){
            for (int j =0; j < 4; j++){
                inventory.setItem(j+i*9+23, no);
            }
        }

    }

    public static ItemStack getHead(UUID id, String type) {

        Player player = Bukkit.getPlayer(id);
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta metahead = (SkullMeta) head.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(type.equals("tph")){
            lore.add(ChatColor.WHITE +player.getName()+ "has made a tph request");
        }else if(type.equals("tpt")){
            lore.add(ChatColor.WHITE +player.getName()+ "has made a tpt request");
        }
        metahead.setLore(lore);
        metahead.setDisplayName(ChatColor.BLUE+player.getName());
        metahead.setOwningPlayer(player);
        head.setItemMeta(metahead);
        return head;
    }
}
