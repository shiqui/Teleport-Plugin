package me.shiqui.sqtp.commands;

import me.shiqui.sqtp.Sqtp;
import me.shiqui.sqtp.menusystem.menu.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainMenuCommand implements CommandExecutor {

    private final Sqtp plugin;
    public MainMenuCommand(Sqtp plugin){

        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            new MainMenu(Sqtp.getPlayerMenuUtility(p),plugin).open();

        }

        return true;

    }

}