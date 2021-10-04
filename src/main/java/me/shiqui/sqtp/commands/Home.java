package me.shiqui.sqtp.commands;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Home implements CommandExecutor {

    private final Sqtp plugin;
    public Home(Sqtp plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){

            Player player = (Player)sender;
            UUID id = player.getUniqueId();

            if(!plugin.hasHome(id)){
                player.sendMessage(ChatColor.RED + "You do not have a home set.");
            }else{
                player.sendMessage(ChatColor.GREEN + "You are teleporting to home.");
                Location home = plugin.getHome(id);

                plugin.teleport(player, home, "Teleporting to home.");
            }

        }

        return true;

    }

}