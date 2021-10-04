package me.shiqui.sqtp;

import java.util.HashMap;
import java.util.UUID;

import me.shiqui.sqtp.commands.*;
import me.shiqui.sqtp.events.MenuListener;
import me.shiqui.sqtp.events.PlayerLogOff;
import me.shiqui.sqtp.menusystem.PlayerMenuUtility;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Sqtp extends JavaPlugin {

    //<player, home location>
    private HashMap<UUID, Location> homes;
    private Files files;

    private static HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    //<player, cooldown>
    //<target, player>, player to target when tpa
    public static HashMap<UUID,Long> tptCoolDown = new HashMap<UUID,Long>();
    public static HashMap<UUID,UUID> tptRequests = new HashMap<UUID,UUID>();

    //<player, cooldown>
    //<target, player>, target to player when tpa
    public static HashMap<UUID,Long> tphCoolDown = new HashMap<UUID,Long>();
    public static HashMap<UUID,UUID> tphRequests = new HashMap<UUID,UUID>();


    @Override
    public void onEnable() {

        System.out.println("[sqtp] Initializing Files...");
        this.homes = new HashMap<>();
        this.files = new Files(this);
        this.files.init();

        System.out.println("[sqtp] Loading Commands...");
        getCommand( "tpt").setExecutor(new Tpt());
        getCommand( "tph").setExecutor(new Tph());
        getCommand( "tpa").setExecutor(new Tpa(this));
        getCommand( "tpd").setExecutor(new Tpd());
        getCommand( "tpc").setExecutor(new Tpc());
        getCommand( "sethome").setExecutor(new SetHome(this));
        getCommand( "home").setExecutor(new Home(this));
        getCommand("menu").setExecutor(new MainMenuCommand(this));

        System.out.println("[sqtp] Loading Events...");
        getServer().getPluginManager().registerEvents(new PlayerLogOff(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        System.out.println("[sqtp] The Plugin Has Finished Loading");

    }

    @Override
    public void onDisable(){
        this.files.terminate();
    }


    public void addHome(UUID id, Location location){
        this.homes.put(id, location);
    }

    public Location getHome(UUID id){
        return this.homes.get(id);
    }

    public boolean hasHome(UUID id){
        return this.homes.containsKey(id);
    }

    public HashMap<UUID, Location> getHomes(){
        return homes;
    }

    public Files getFiles() {
        return files;
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p){
        PlayerMenuUtility playerMenuUtility;

        if(playerMenuUtilityMap.containsKey(p)){
            return playerMenuUtilityMap.get(p);
        }else{
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p,playerMenuUtility);

            return playerMenuUtility;
        }
    }


    public void teleport(Player player, Location target, String barMessage){

        BossBar bar = Bukkit.getServer().createBossBar(ChatColor.LIGHT_PURPLE+barMessage , BarColor.PURPLE, BarStyle.SOLID , BarFlag.PLAY_BOSS_MUSIC);
        bar.addPlayer(player);

        new BukkitRunnable() {
            float progress = 0;

            @Override
            public void run() {
                if(progress == 0) {
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 1, 1);
                } else if(progress >= 0.99) {
                    player.teleport(target);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                    bar.removePlayer(player);

                    cancel();
                }

                bar.setProgress(progress);
                player.getWorld().spawnParticle(Particle.DRAGON_BREATH,player.getLocation(),8);

                progress += 0.01;
            }
        }.runTaskTimer(this, 1, 1);
    }
}
