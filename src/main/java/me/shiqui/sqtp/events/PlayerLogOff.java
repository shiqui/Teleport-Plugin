package me.shiqui.sqtp.events;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerLogOff implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){

        Player player = event.getPlayer();

        if(Sqtp.tptRequests.containsKey(player.getUniqueId())){

            Player source = Bukkit.getPlayer(Sqtp.tptRequests.get(player.getUniqueId()));
            Sqtp.tptRequests.remove(player.getUniqueId());
            source.sendMessage(ChatColor.WHITE+player.getDisplayName()+ChatColor.RED+" logged off, teleport request cancelled");


        }

        if(Sqtp.tphRequests.containsKey(player.getUniqueId())){

            Player source = Bukkit.getPlayer(Sqtp.tphRequests.get(player.getUniqueId()));
            Sqtp.tphRequests.remove(player.getUniqueId());
            source.sendMessage(ChatColor.WHITE+player.getDisplayName()+ChatColor.RED+" logged off, teleport request cancelled");

        }

        if(Sqtp.tptRequests.containsValue(player.getUniqueId())){

            for ( UUID key : Sqtp.tptRequests.keySet() ) {

                Player target = Bukkit.getPlayer(key);
                Sqtp.tptRequests.remove(target.getUniqueId());
                target.sendMessage(ChatColor.WHITE+player.getDisplayName()+ChatColor.RED+" logged off, teleport request cancelled");

            }

        }

        if(Sqtp.tphRequests.containsValue(player.getUniqueId())){

            for ( UUID key : Sqtp.tphRequests.keySet() ) {

                Player target = Bukkit.getPlayer(key);
                Sqtp.tphRequests.remove(target.getUniqueId());
                target.sendMessage(ChatColor.WHITE+player.getDisplayName()+ChatColor.RED+" logged off, teleport request cancelled");

            }

        }


    }

}
