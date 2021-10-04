package me.shiqui.sqtp.commands;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class Tpa implements CommandExecutor{

    private final Sqtp plugin;
    public Tpa(Sqtp plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player target = (Player)sender;

            if(Sqtp.tptRequests.containsKey(target.getUniqueId())){

                Player player = Bukkit.getPlayer(Sqtp.tptRequests.get(target.getUniqueId()));

                player.sendMessage(ChatColor.GREEN+"You're teleporting to "+ChatColor.WHITE+target.getDisplayName());
                target.sendMessage(ChatColor.WHITE+player.getDisplayName()+ChatColor.GREEN+" is teleporting to you");

                plugin.teleport(player,target.getLocation(),"Teleporting to"+target.getDisplayName());
                Sqtp.tptCoolDown.put(player.getUniqueId(),System.currentTimeMillis());
                Sqtp.tptRequests.remove(target.getUniqueId());

            }else if(Sqtp.tphRequests.containsKey(target.getUniqueId())){

                Player player = Bukkit.getPlayer(Sqtp.tphRequests.get(target.getUniqueId()));

                target.sendMessage(ChatColor.GREEN+"You're teleporting to "+ChatColor.WHITE+player.getDisplayName());
                player.sendMessage(ChatColor.WHITE+target.getDisplayName()+ChatColor.GREEN+" is teleporting to you");

                plugin.teleport(target,player.getLocation(),"Teleporting to"+player.getDisplayName());
                Sqtp.tphCoolDown.put(player.getUniqueId(),System.currentTimeMillis());
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