package kuroko3417.raijin;

import kuroko3417.raijin.Config.FileIO;
import kuroko3417.raijin.DeathState.DeathList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Raijin extends JavaPlugin {
    
    public FileIO fileIo;
    public Setting setting;
    public DeathList deathList;
    public TargetLists targetLists;
    public ObserverList observerList;
    public PurgatoryList purgatoryList;
    
    @Override
    public void onEnable() {
        getLogger().info("Enabled Raijin Plugin");
    
        this.fileIo        = new FileIO();
        this.setting       = new Setting();
        this.deathList     = new DeathList();
        this.targetLists   = new TargetLists();
        this.observerList  = new ObserverList();
        this.purgatoryList = new PurgatoryList();
    
        getServer().getPluginManager().registerEvents(
                new MainListener(this), this);
        getCommand("ri").setTabCompleter(
                new MainTabCompletion(this));
        getCommand("ri").setExecutor(
                new MainCommandExecutor(this));
    }

    @Override
    public void onDisable() {
            getLogger().info("Disable Raijin Plugin");
    }
}
