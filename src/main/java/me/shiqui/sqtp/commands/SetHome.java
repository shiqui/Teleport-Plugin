package me.shiqui.sqtp.commands;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SetHome implements CommandExecutor {

    private final Sqtp plugin;
    public SetHome(Sqtp plugin){

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){

            Player player = (Player)sender;
            Location l = player.getLocation();
            UUID id = player.getUniqueId();

            plugin.addHome(id, l);

            plugin.getFiles().addHome(player.getUniqueId(), l);

            int x = (int) Math.round(l.getX());
            int y = (int) Math.round(l.getY());
            int z = (int) Math.round(l.getZ());

            player.sendMessage(ChatColor.YELLOW + "Your home has been set to " + ChatColor.WHITE + " x: " + x + " y: " + y + " z: " + z);

        }

        return true;

    }

}