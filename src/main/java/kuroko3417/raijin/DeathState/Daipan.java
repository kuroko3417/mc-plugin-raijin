package kuroko3417.raijin.DeathState;

import org.bukkit.entity.Player;

public class Daipan extends DeathType{
    public Daipan(Player player){
        super(player);
    }
    
    @Override
    public String deathMessage() {
        return this.player.getName() + "はkono_aに台パンされて死亡した";
    }
}
