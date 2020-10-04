package kuroko3417.raijin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import kuroko3417.raijin.Config.FileIO;
import kuroko3417.raijin.DeathState.DeathList;
import kuroko3417.raijin.DeathState.DeathType;
import kuroko3417.raijin.DeathState.Observer;
import kuroko3417.raijin.DeathState.Purgatory;

import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class MainListener implements org.bukkit.event.Listener {
    
    private Raijin raijin;
    public FileIO fileIo;
    public Setting setting;
    public DeathList deathList;
    public TargetLists targetLists;
    public ObserverList observerList;
    public PurgatoryList purgatoryList;
    
    public MainListener(Raijin raijin){
        this.raijin        = raijin;
        this.fileIo        = raijin.fileIo;
        this.setting       = raijin.setting;
        this.deathList     = raijin.deathList;
        this.targetLists   = raijin.targetLists;
        this.observerList  = raijin.observerList;
        this.purgatoryList = raijin.purgatoryList;
    }
    
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        
        Player joinPlayer     = event.getPlayer();
        String joinPlayerUUID = joinPlayer.getUniqueId().toString();

        Boolean obsPlayer = this.observerList.existUniqueId(joinPlayerUUID);
        Boolean purPlayer = this.purgatoryList.existUniqueId(joinPlayerUUID);
        int power = this.setting.getLightningPower();

        if(obsPlayer || purPlayer){
            if(obsPlayer && !purPlayer){
                this.deathList.add(new Observer(joinPlayer));
            }
            if(!obsPlayer && purPlayer){
                this.deathList.add(new Purgatory(joinPlayer));
            }
    
            // NOTE: join後はしばらくダメージを受けないので、遅延して確殺する。
            Bukkit.getScheduler().scheduleSyncDelayedTask(this.raijin, new Runnable() {
                public void run() {
                    joinPlayer.getWorld().strikeLightning(joinPlayer.getLocation());
                    joinPlayer.getWorld().createExplosion(joinPlayer.getLocation(), power);
                    // NOTE:落雷、爆発で倒せなかったときのために確殺するダメージを与える
                    joinPlayer.damage(9999);
                }
                // NOTE: 検証した結果、下記の遅延値が最適っぽい。
            }, 70L);
            try{
                this.observerList.remove(this.observerList.findByUniqueId(joinPlayerUUID));
            } catch (Exception e){
                getLogger().info("Failed to exclude player from obs. UUID: " + joinPlayerUUID);
            }
        }
    }
    
    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent event){
        Player respawnPlayer     = event.getPlayer();
        String respawnPlayerUUID = respawnPlayer.getUniqueId().toString();
    
        Boolean purPlayer = this.purgatoryList.existUniqueId(respawnPlayerUUID);
        int power = this.setting.getLightningPower();
        if(purPlayer){
            this.deathList.add(new Purgatory(respawnPlayer));
            // NOTE: リスポーン後はしばらくダメージを受けないので、遅延して確殺する。
            Bukkit.getScheduler().scheduleSyncDelayedTask(this.raijin, new Runnable() {
                public void run() {
                    respawnPlayer.getWorld().strikeLightning(respawnPlayer.getLocation());
                    respawnPlayer.getWorld().createExplosion(respawnPlayer.getLocation(), power);
                    // NOTE:落雷、爆発で倒せなかったときのために確殺するダメージを与える
                    respawnPlayer.damage(9999);
                }
                // NOTE: 検証した結果、下記の遅延値が最適っぽい。
            }, 70L);
        }
    }
    

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        DeathType deathType = this.deathList.find(player.getUniqueId().toString());
        if(deathType == null){
            return;
        }
        event.setDeathMessage(deathType.deathMessage());
        this.deathList.remove(deathType);
    }
}
