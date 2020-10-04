package kuroko3417.raijin.DeathState;

import org.bukkit.entity.Player;

public class Purgatory extends DeathType{
    public Purgatory(Player player){
        super(player);
    }
    
    @Override
    public String deathMessage() {
        return this.player.getName() + "は煉獄されて死亡した";
    }
}
