package kuroko3417.raijin.DeathState;

import java.util.ArrayList;
import java.util.List;

public class DeathList {
    private List<DeathType> deathPlayerList;
    
    public DeathList(){
        this.deathPlayerList = new ArrayList<>();
    }
    
    public void add(DeathType deathType){
        this.deathPlayerList.add(deathType);
    }
    
    public void remove(DeathType deathType){
        this.deathPlayerList.remove(deathType);
    }
    
    public DeathType find(String uniqueId){
        if(this.deathPlayerList.size() == 0){
            return null;
        }
        for(DeathType deathType : this.deathPlayerList){
            if(!deathType.getUniqueId().equals(uniqueId)){
                continue;
            }
            return deathType;
        }
        return null;
    }
}
