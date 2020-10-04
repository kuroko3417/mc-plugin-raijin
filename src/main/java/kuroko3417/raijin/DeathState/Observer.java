package kuroko3417.raijin.DeathState;

import org.bukkit.entity.Player;

public class Observer extends DeathType{
    public Observer(Player player){
        super(player);
    }
    
    @Override
    public String deathMessage() {
        return this.player.getName() + "は天誅によって死亡した。";
    }
}
