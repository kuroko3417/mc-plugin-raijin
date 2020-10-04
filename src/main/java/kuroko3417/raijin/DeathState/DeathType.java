package kuroko3417.raijin.DeathState;

import org.bukkit.entity.Player;

abstract public class DeathType{
    protected Player player;
    
    public DeathType(Player player){
        this.player = player;
    }
    
    final public String getName() {
        return this.player.getName();
    }
    
    final public String getUniqueId() {
        return this.player.getUniqueId().toString();
    }
    
   abstract  public String deathMessage();
}
