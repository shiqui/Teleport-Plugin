package me.shiqui.sqtp.commands;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Tpd implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player target = (Player)sender;

            if(Sqtp.tptRequests.containsKey(target.getUniqueId())){

                Player player = Bukkit.getPlayer(Sqtp.tptRequests.get(target.getUniqueId()));

                target.sendMessage(ChatColor.DARK_RED + "The teleport request has been denied");
                player.sendMessage(ChatColor.DARK_RED + "The teleport request has been denied");

                Sqtp.tptRequests.remove(target.getUniqueId());

            }else if(Sqtp.tphRequests.containsKey(target.getUniqueId())){

                Player player = Bukkit.getPlayer(Sqtp.tphRequests.get(target.getUniqueId()));

                target.sendMessage(ChatColor.DARK_RED + "The teleport request has been denied");
                player.sendMessage(ChatColor.DARK_RED + "The teleport request has been denied");

                Sqtp.tphRequests.remove(target.getUniqueId());

            }else{

                target.sendMessage(ChatColor.RED+"You do not have any pending requests");

            }

        }else{

            System.out.println("[sqtp] Only players can use this command");

        }

        return true;

    }

}