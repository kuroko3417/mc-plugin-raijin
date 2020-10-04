package kuroko3417.raijin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class Group {
    private String name;
    private List<Target> targetList;
    
    public Group(String name){
        this.name = name;
        this.targetList = new ArrayList<>();
    }
    
    public void add(Target target){
        if(this.exist(target)){
            throw new RuntimeException("Target already exists");
        }
        this.targetList.add(target);
    }
    
    public void adds(Target[] target){
        for(Target tmpTarget : target){
            try{
                this.add(tmpTarget);
            } catch (Exception e){
                getLogger().info(e.getMessage());
            }
        }
    }
    
    public void remove(Target target){
        if(!this.exist(target)){
            throw new RuntimeException("Target does not exist");
        }
        this.targetList.remove(target);
    }
    
    public void removes(Target[] target){
        for(Target tmpTarget : target){
            try{
                this.remove(tmpTarget);
            } catch (Exception e){
                getLogger().info(e.getMessage());
            }
        }
    }
    
    public void clear(){
        this.targetList.clear();
    }
    
    private boolean exist(Target target){
        if(this.size() == 0){
            return false;
        }
        for(Target tmpTarget : this.targetList){
            if(!tmpTarget.getUniqueId().equals(target.getUniqueId())){
                continue;
            }
            return true;
        }
        return false;
    }
    
    
    public boolean existNameId(String name){
        List<String> nameList = this.getAllTargetNameList();
        return nameList.contains(name);
    }
    
    public boolean existUniqueId(String uniqueId){
        List<String> uniqueIdList = this.getAllTargetUniqueIdList();
        return uniqueIdList.contains(uniqueId);
    }
    
    public int size(){
        return this.targetList.size();
    }
    
    public String getName(){
        return this.name;
    }
    
    public Target find(Target target){
        for(Target tmpTarget : this.targetList){
            if(!tmpTarget.getUniqueId().equals(target.getUniqueId())){
                continue;
            }
            return tmpTarget;
        }
        return null;
    }
    
    public Target findByName(String name){
        for(Target tmpTarget : this.targetList){
            if(!tmpTarget.getName().equals(name)){
                continue;
            }
            return tmpTarget;
        }
        return null;
    }
    
    public Target findByUniqueId(String uniqueId){
        for(Target tmpTarget : this.targetList){
            if(!tmpTarget.getUniqueId().equals(uniqueId)){
                continue;
            }
            return tmpTarget;
        }
        return null;
    }
    
    public List<Target> findList(Target[] targets){
        List<Target> targetList = new ArrayList<>();
        List<Target> tmpTargetList = Arrays.asList(targets);
        for(Target target : this.targetList){
            if(tmpTargetList.size() == 0){
                break;
            }
            for(Target tmpTarget : tmpTargetList){
                if(!target.getUniqueId().equals(tmpTarget.getUniqueId())){
                    continue;
                }
                targetList.add(tmpTarget);
                tmpTargetList.remove(tmpTarget);
            }
        }
        return targetList;
    }
    
    public Target[] finds(Target[] targets){
        List<Target> targetList = this.findList(targets);
        return targetList.toArray(new Target[targetList.size()]);
    }
    
    public List<Target> findByNameList(String[] names){
        List<Target> targetList = new ArrayList<>();
        List<String> nameList = Arrays.asList(names);
        for(Target target : this.targetList){
            if(nameList.size() == 0){
                break;
            }
            for(String name : nameList){
                if(!target.getName().equals(name)){
                    continue;
                }
                targetList.add(target);
                nameList.remove(name);
            }
        }
        return targetList;
    }
    
    public Target[] findByNames(String[] names){
        List<Target> targetList = this.findByNameList(names);
        return targetList.toArray(new Target[targetList.size()]);
    }
    
    public List<Target> findByUniqueIdList(String[] uniqueIds){
        List<Target> targetList = new ArrayList<>();
        List<String> uniqueIdList = Arrays.asList(uniqueIds);
        for(Target target : this.targetList){
            if(uniqueIdList.size() == 0){
                break;
            }
            for(String uniqueId : uniqueIdList){
                if(!target.getUniqueId().equals(uniqueId)){
                    continue;
                }
                targetList.add(target);
                uniqueIdList.remove(uniqueId);
            }
        }
        return targetList;
    }
    
    public Target[] findByUniqueIds(String[] uniqueIds){
        List<Target> targetList = this.findByUniqueIdList(uniqueIds);
        return targetList.toArray(new Target[targetList.size()]);
    }
    
    public List<Target> getAllTargetList(){
        List<Target> targetList = new ArrayList<>();
        for(Target target : this.targetList){
            targetList.add(target);
        }
        return targetList;
    }
    
    public Target[] getAllTargets(){
        List<Target> targetList = this.getAllTargetList();
        return targetList.toArray(new Target[targetList.size()]);
    }
    
    
    public List<String> getAllTargetNameList(){
        List<String> nameList = new ArrayList<>();
        for(Target target : this.targetList){
            nameList.add(target.getName());
        }
        return nameList;
    }
    
    public String[] getAllTargetNames(){
        List<String> nameList = this.getAllTargetNameList();
        return nameList.toArray(new String[nameList.size()]);
    }
    
    public List<String> getAllTargetUniqueIdList(){
        List<String> uniqueIdList = new ArrayList<>();
        for(Target target : this.targetList){
            uniqueIdList.add(target.getUniqueId());
        }
        return uniqueIdList;
    }
    
    public String[] getAllTargetUniqueIds(){
        List<String> uniqueIdList = this.getAllTargetUniqueIdList();
        return uniqueIdList.toArray(new String[uniqueIdList.size()]);
    }
}
