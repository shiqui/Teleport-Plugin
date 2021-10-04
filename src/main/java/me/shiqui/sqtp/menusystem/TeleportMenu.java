package me.shiqui.sqtp.menusystem;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;


public abstract class TeleportMenu implements InventoryHolder {

    protected Inventory inventory;
    protected PlayerMenuUtility playerMenuUtility;
    protected Sqtp plugin;
    protected Player target;

    public TeleportMenu(PlayerMenuUtility playerMenuUtility, Sqtp plugin, Player target) {
        this.playerMenuUtility = playerMenuUtility;
        this.plugin = plugin;
        this.target = target;
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void open(){

        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItems();

        playerMenuUtility.getOwner().openInventory(inventory);

    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
