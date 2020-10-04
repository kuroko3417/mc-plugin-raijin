package kuroko3417.raijin;

import kuroko3417.raijin.Config.Marker;

public class Setting implements Marker {

    private final int LIGHTNING_POWER_MAX                = 1000;
    private final int LIGHTNING_POWER_MIN                = 0;
    private final int LIGHTNING_POWER_DEFAULT            = 0;
    private final boolean LIGHTNING_STRIKE_DEATH_DEFAULT = true;

    private int lightningPower;
    private boolean lightningStrikeDeath;

    public Setting(){
        this.resetLightningPower();;
        this.resetLightningStrikeDeath();
    }

    public void setLightningPower(int power){
        if(power < LIGHTNING_POWER_MIN){
            throw new IllegalArgumentException("The specified value cannot be set");
        }
        if(this.LIGHTNING_POWER_MAX < power){
            throw new IllegalArgumentException("The specified value cannot be set");
        }
        this.lightningPower = power;
    }

    public int getLightningPower(){
        return this.lightningPower;
    }
    
    public void resetLightningPower(){
        this.setLightningPower(this.LIGHTNING_POWER_DEFAULT);
    }
    
    
    
    public void setLightningStrikeDeath(boolean state){
        this.lightningStrikeDeath = state;
    }
    
    public boolean getLightningStrikeDeath(){
        return this.lightningStrikeDeath;
    }
    
    public void resetLightningStrikeDeath(){
        this.lightningStrikeDeath = this.LIGHTNING_STRIKE_DEATH_DEFAULT;
    }
    
}
