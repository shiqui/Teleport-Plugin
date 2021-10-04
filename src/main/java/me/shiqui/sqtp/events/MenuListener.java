package me.shiqui.sqtp.events;

import me.shiqui.sqtp.menusystem.Menu;
import me.shiqui.sqtp.menusystem.TeleportMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory() == null)
            return;

        InventoryHolder holder = e.getClickedInventory().getHolder();

        if (holder instanceof Menu){
            e.setCancelled(true);

            if (e.getCurrentItem()==null){
                return;
            }

            Menu menu = (Menu) holder;
            menu.handleMenu(e);

        }else if (holder instanceof TeleportMenu){
            e.setCancelled(true);

            if (e.getCurrentItem()==null){
                return;
            }

            TeleportMenu menu = (TeleportMenu) holder;
            menu.handleMenu(e);
        }

    }

}
