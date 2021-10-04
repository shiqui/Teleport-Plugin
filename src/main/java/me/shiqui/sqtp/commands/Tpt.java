package me.shiqui.sqtp.commands;

import me.shiqui.sqtp.Sqtp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpt implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player player = (Player)sender;

            if(args.length == 1){

                Player target = Bukkit.getPlayer(args[0]);

                if(target == null){

                    //offline or non existed players
                    player.sendMessage(ChatColor.RED + "This player is offline or does not exist, please enter a valid player name");

                }else if(target == player){

                    //tp to self
                    player.sendMessage(ChatColor.RED + "You cannot teleport to yourself");

                }else{

                    if(!Sqtp.tptCoolDown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - Sqtp.tptCoolDown.get(player.getUniqueId()) > 60000) {

                        if(Sqtp.tptRequests.containsKey(target.getUniqueId())||Sqtp.tphRequests.containsKey(target.getUniqueId())){

                            //request for this target already exists, either tpt or tph
                            player.sendMessage(ChatColor.WHITE + args[0] + ChatColor.RED + " already has a pending teleport request, please try again later");

                        }else if(Sqtp.tptRequests.containsValue(player.getUniqueId()) || Sqtp.tphRequests.containsValue(player.getUniqueId())){

                            //player has another request
                            player.sendMessage(ChatColor.RED+"You can only make one request at a time");

                        }else{

                            //all conditions reached
                            //create new request
                            Sqtp.tptRequests.put(target.getUniqueId(), player.getUniqueId());
                            player.sendMessage(ChatColor.YELLOW + "You have requested to teleport to " + ChatColor.WHITE + args[0]);
                            target.sendMessage(ChatColor.WHITE + player.getDisplayName() + ChatColor.YELLOW + " has requested to teleport to you");

                        }

                    }else{

                        //teleport on cooldown
                        player.sendMessage(ChatColor.RED + "Your teleport is on cooldown, please wait 60 seconds between teleports");

                    }

                }

            }else{

                //invalid format
                player.sendMessage(ChatColor.RED + "Invalid format");
                player.sendMessage(ChatColor.RED + "To teleport to a player, use /tpt <player>");

            }

        }

        return true;

    }

}
