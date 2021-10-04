package me.shiqui.sqtp.commands;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class Tpc implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {

            Player player = (Player)sender;

            if (Sqtp.tptRequests.containsValue(player.getUniqueId())){

                Player target = Bukkit.getPlayer(getKey(Sqtp.tptRequests,player.getUniqueId()));

                player.sendMessage(ChatColor.YELLOW+"You have cancelled your tpt request to "+ChatColor.WHITE+target.getName());
                target.sendMessage(ChatColor.WHITE+player.getName()+ChatColor.YELLOW+" has cancelled the tpt request");

                Sqtp.tptRequests.remove(target.getUniqueId());


            }else if(Sqtp.tphRequests.containsValue(player.getUniqueId())){

                Player target = Bukkit.getPlayer(getKey(Sqtp.tphRequests,player.getUniqueId()));

                player.sendMessage(ChatColor.YELLOW+"You have cancelled your tph request to "+ChatColor.WHITE+target.getName());
                target.sendMessage(ChatColor.WHITE+player.getName()+ChatColor.YELLOW+" has cancelled the tph request");

                Sqtp.tphRequests.remove(target.getUniqueId());

            }else{
                player.sendMessage(ChatColor.RED+"You haven't made any teleport request yet");
            }

        }


        return true;

    }

    public <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
