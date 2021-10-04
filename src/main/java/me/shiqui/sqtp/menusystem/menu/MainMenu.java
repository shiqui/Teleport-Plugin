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
import java.util.Map;

public class MainMenu extends Menu {


    public MainMenu(PlayerMenuUtility playerMenuUtility, Sqtp plugin) {
        super(playerMenuUtility, plugin);
    }

    @Override
    public String getMenuName() {
        return ChatColor.BOLD+""+ChatColor.BLUE+"Main Menu";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();

        switch (e.getCurrentItem().getType()){
            case OAK_DOOR:
                p.performCommand("home");
                p.closeInventory();
                break;
            case OAK_SAPLING:
                new SetHomeConfirmation(Sqtp.getPlayerMenuUtility(p),plugin).open();
                break;
            case ENDER_EYE:
                new TeleportSelection(Sqtp.getPlayerMenuUtility(p),plugin).open();
                break;
            case CHEST:
            case GRAY_STAINED_GLASS_PANE:
                break;
            case RED_STAINED_GLASS_PANE:
                p.performCommand("tpc");
            case PLAYER_HEAD:
        }

    }

    @Override
    public void setMenuItems() {

        Player p = playerMenuUtility.getOwner();

        ItemStack item1 = new ItemStack(Material.OAK_DOOR);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.BOLD+""+ChatColor.DARK_GREEN+"Home");
        List<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.GREEN + "Teleport to home");
        if(plugin.hasHome(p.getUniqueId())){
            Location l = plugin.getHome(p.getUniqueId());
            int x = (int) Math.round(l.getX());
            int y = (int) Math.round(l.getY());
            int z = (int) Math.round(l.getZ());
            lore1.add(ChatColor.WHITE + "Your home is at x: " + x + " y: " + y + " z: " + z);
        }else{
            lore1.add(ChatColor.YELLOW + "You do not have a home set");
        }
        meta1.setLore(lore1);
        item1.setItemMeta(meta1);
        inventory.setItem(10, item1);


        ItemStack item2 = new ItemStack(Material.OAK_SAPLING);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.BOLD+""+ChatColor.DARK_GREEN+"Set Home");
        List<String> lore2 = new ArrayList<>();
        lore2.add(ChatColor.GREEN + "Set home to your current location");
        if(plugin.hasHome(p.getUniqueId())){
            Location l = plugin.getHome(p.getUniqueId());
            int x = (int) Math.round(l.getX());
            int y = (int) Math.round(l.getY());
            int z = (int) Math.round(l.getZ());
            lore2.add(ChatColor.WHITE + "Your home is at x: " + x + " y: " + y + " z: " + z);
        }else{
            lore2.add(ChatColor.YELLOW + "You do not have a home set");
        }
        meta2.setLore(lore2);
        item2.setItemMeta(meta2);
        inventory.setItem(12, item2);


        ItemStack item3 = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta3 = item3.getItemMeta();
        meta3.setDisplayName(ChatColor.BOLD+""+ChatColor.DARK_PURPLE+"Outgoing Teleport Request");
        List<String> lore3 = new ArrayList<>();
        lore3.add(ChatColor.LIGHT_PURPLE + "Make a teleport request");
        meta3.setLore(lore3);
        item3.setItemMeta(meta3);
        inventory.setItem(14, item3);




        ItemStack item4 = new ItemStack(Material.CHEST);
        ItemMeta meta4 = item4.getItemMeta();
        meta4.setDisplayName(ChatColor.BOLD+""+ChatColor.DARK_PURPLE+"Incoming Teleport Request");
        List<String> lore4 = new ArrayList<>();
        lore4.add(ChatColor.LIGHT_PURPLE + "Pending request is shown below, click on it to accept or deny");
        meta4.setLore(lore4);
        item4.setItemMeta(meta4);
        inventory.setItem(16, item4);


        ItemStack item5 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta5 = item5.getItemMeta();
        meta5.setDisplayName(ChatColor.BOLD+""+ChatColor.RED+"Cancel your outgoing request");
        item5.setItemMeta(meta5);
        inventory.setItem(23, item5);


        if(Sqtp.tptRequests.containsKey(p.getUniqueId()) || Sqtp.tphRequests.containsKey(p.getUniqueId())){

            Player source = Bukkit.getPlayer(Sqtp.tptRequests.get(p.getUniqueId()));

            inventory.setItem(25, getHead(source));

        }else{

            ItemStack item6 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta meta6 = item6.getItemMeta();
            meta6.setDisplayName(ChatColor.BOLD+""+ChatColor.GRAY+"You do not have any pending request");
            item6.setItemMeta(meta6);
            inventory.setItem(25, item6);

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