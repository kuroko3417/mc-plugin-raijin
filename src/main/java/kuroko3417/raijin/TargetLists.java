package kuroko3417.raijin;

import kuroko3417.raijin.Config.Marker;

import java.util.ArrayList;
import java.util.List;


public class TargetLists implements Marker{
    private List<TargetList> targetLists;

    public TargetLists(){
        this.targetLists = new ArrayList<>();
    }

    public void add(TargetList targetList){
        if(this.existsName(targetList.getName())){
            throw new RuntimeException("TargetList already exists.");
        }
        this.targetLists.add(targetList);
    }

    public void remove(TargetList targetList) {
        if(!this.existsName(targetList.getName())){
            throw new RuntimeException("TargetList does not exists.");
        }
        this.targetLists.remove(targetList);
    }
    
    public TargetList find(String targetListName){
        if(!this.existsName(targetListName)){
            return null;
        }
        for(TargetList targetList : this.targetLists){
            if(!targetList.getName().equals(targetListName)){
                continue;
            }
            return targetList;
        }
        return null;
    }
    
    public int size(){
        return this.targetLists.size();
    }
    
    public boolean existsName(String name){
        List<String> targetListNameList = this.getListNameList();
        return targetListNameList.contains(name);
    }
    
    public List<String> getListNameList(){
        List<String> nameList = new ArrayList<>();
        for (TargetList targetList : this.targetLists) {
            nameList.add(targetList.getName());
        }
        return nameList;
    }
    public String[] getListNames(){
        List<String> nameList = this.getListNameList();
        return nameList.toArray(new String[nameList.size()]);
    }
}
