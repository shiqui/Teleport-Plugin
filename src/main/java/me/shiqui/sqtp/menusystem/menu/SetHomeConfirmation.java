package me.shiqui.sqtp.menusystem.menu;

import me.shiqui.sqtp.Sqtp;
import me.shiqui.sqtp.menusystem.Menu;
import me.shiqui.sqtp.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetHomeConfirmation extends Menu {

    public SetHomeConfirmation(PlayerMenuUtility playerMenuUtility, Sqtp plugin) {
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
            case GRAY_STAINED_GLASS_PANE:
                break;
            case LIME_STAINED_GLASS_PANE:
                p.performCommand("sethome");
                p.closeInventory();
                break;
            case RED_STAINED_GLASS_PANE:
                new MainMenu(Sqtp.getPlayerMenuUtility(p),plugin).open();
                break;

        }

    }

    @Override
    public void setMenuItems() {

        Player p = playerMenuUtility.getOwner();

        ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(" ");
        List<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.YELLOW + "Do you want to set home to your current location?");
        if(plugin.hasHome(p.getUniqueId())){
            Location l = plugin.getHome(p.getUniqueId());
            int x = (int) Math.round(l.getX());
            int y = (int) Math.round(l.getY());
            int z = (int) Math.round(l.getZ());
            lore1.add(ChatColor.YELLOW + "It will overwrite your previous home at"+ChatColor.YELLOW+" x: " + x + " y: " + y + " z: " + z);
        }
        meta1.setLore(lore1);
        item1.setItemMeta(meta1);
        for (int i =0; i < 18; i++){
            inventory.setItem(i, item1);
        }

        ItemStack yes = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta metayes = yes.getItemMeta();
        metayes.setDisplayName(" ");
        List<String> loreyes = new ArrayList<>();
        loreyes.add(ChatColor.DARK_GREEN + "Yes");
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
        loreno.add(ChatColor.DARK_RED + "No");
        metano.setLore(loreno);
        no.setItemMeta(metano);
        for (int i =0; i < 3; i++){
            for (int j =0; j < 4; j++){
                inventory.setItem(j+i*9+23, no);
            }
        }

    }


}
