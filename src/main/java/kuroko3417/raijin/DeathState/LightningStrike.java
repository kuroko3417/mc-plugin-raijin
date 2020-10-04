package kuroko3417.raijin.DeathState;

import org.bukkit.entity.Player;

public class LightningStrike extends DeathType{
    public LightningStrike(Player player){
        super(player);
    }
    
    @Override
    public String deathMessage() {
        return this.player.getName() + "は落雷によって死亡した";
    }
}
