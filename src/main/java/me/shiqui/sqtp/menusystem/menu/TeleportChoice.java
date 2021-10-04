package me.shiqui.sqtp.menusystem.menu;

import me.shiqui.sqtp.Sqtp;
import me.shiqui.sqtp.menusystem.PlayerMenuUtility;
import me.shiqui.sqtp.menusystem.TeleportMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class TeleportChoice extends TeleportMenu {

    private Player target;
    public TeleportChoice(PlayerMenuUtility playerMenuUtility, Sqtp plugin, Player target) {
        super(playerMenuUtility, plugin, target);
        this.target = target;
    }

    @Override
    public String getMenuName() {
        return ChatColor.BOLD+""+ChatColor.BLUE+"Select The Teleport Method";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player)e.getWhoClicked();
        String c;

        switch (e.getCurrentItem().getType()){
            case GRAY_STAINED_GLASS_PANE:
                break;
            case LIME_STAINED_GLASS_PANE:
                c = "tpt "+this.target.getDisplayName();
                p.performCommand(c);
                p.closeInventory();
                break;
            case BLUE_STAINED_GLASS_PANE:
                c = "tph "+this.target.getDisplayName();
                p.performCommand(c);
                p.closeInventory();
                break;

        }

    }

    @Override
    public void setMenuItems() {

        Player p = playerMenuUtility.getOwner();
        if(!Sqtp.tptCoolDown.containsKey(p.getUniqueId()) || System.currentTimeMillis() - Sqtp.tptCoolDown.get(p.getUniqueId()) > 60000){
            ItemStack tpt = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            ItemMeta metatpt = tpt.getItemMeta();
            metatpt.setDisplayName(ChatColor.DARK_PURPLE+"tpt");
            List<String> loretpt = new ArrayList<>();
            loretpt.add(ChatColor.LIGHT_PURPLE + "Teleport to the target player");
            metatpt.setLore(loretpt);
            tpt.setItemMeta(metatpt);

            inventory.setItem(11, tpt);
        }else{
            ItemStack tpt = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta metatpt = tpt.getItemMeta();
            metatpt.setDisplayName(ChatColor.GRAY+"tpt");
            List<String> loretpt = new ArrayList<>();
            loretpt.add(ChatColor.GRAY + "This teleport is on cooldown");
            loretpt.add(ChatColor.GRAY + "You can only use this command once every 60 seconds");
            metatpt.setLore(loretpt);
            tpt.setItemMeta(metatpt);

            inventory.setItem(11, tpt);
        }

        if(!Sqtp.tphCoolDown.containsKey(p.getUniqueId()) || System.currentTimeMillis() - Sqtp.tphCoolDown.get(p.getUniqueId()) > 60000){
            ItemStack tph = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
            ItemMeta metatph = tph.getItemMeta();
            metatph.setDisplayName(ChatColor.DARK_PURPLE+"tph");
            List<String> loretph = new ArrayList<>();
            loretph.add(ChatColor.LIGHT_PURPLE + "Teleport the target player to you");
            metatph.setLore(loretph);
            tph.setItemMeta(metatph);

            inventory.setItem(15, tph);
        }else{
            ItemStack tph = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta metatph = tph.getItemMeta();
            metatph.setDisplayName(ChatColor.GRAY+"tph");
            List<String> loretph = new ArrayList<>();
            loretph.add(ChatColor.GRAY + "This teleport is on cooldown");
            loretph.add(ChatColor.GRAY + "You can only use this command once every 60 seconds");
            metatph.setLore(loretph);
            tph.setItemMeta(metatph);

            inventory.setItem(15, tph);
        }


    }
}
