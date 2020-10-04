package kuroko3417.raijin;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class TargetGenerator {
    
    public static Target fromPlayer(Player player){
        String tmpName     = player.getName();
        String tmpUniqueId = player.getUniqueId().toString();
        return new Target(tmpName, tmpUniqueId);
    }
    
    public static Target fromPlayer(OfflinePlayer offlinePlayer){
        String tmpName     = offlinePlayer.getName();
        String tmpUniqueId = offlinePlayer.getUniqueId().toString();
        return new Target(tmpName, tmpUniqueId);
    }
    
    public static Target fromName(String name){
        OfflinePlayer offlinePlayer = SearchWhiteListPlayer.byName(name);
        if(offlinePlayer == null){
            throw new InvalidParameterException("The specified player does not exist");
        }
        return TargetGenerator.fromPlayer(offlinePlayer);
    }
    
    public static Target fromUniqueId(String uniqueId){
        OfflinePlayer offlinePlayer = SearchWhiteListPlayer.byUniqueId(uniqueId);
        if(offlinePlayer == null){
            throw new InvalidParameterException("The specified player does not exist");
        }
        return TargetGenerator.fromPlayer(offlinePlayer);
    }
    
    // List<>型で使う想定はないのでList<>型のオーバーロードメソッドは実装しない
    
    public static Target[] fromPlayers(Player[] players){
        List<Target> targets = new ArrayList<>();
        for(Player player : players){
            targets.add(TargetGenerator.fromPlayer(player));
        }
        return targets.toArray(new Target[targets.size()]);
    }
    
    public static Target[] fromPlayers(OfflinePlayer[] offlinePlayers){
        List<Target> targets = new ArrayList<>();
        for(OfflinePlayer offlinePlayer : offlinePlayers){
            targets.add(TargetGenerator.fromPlayer(offlinePlayer));
        }
        return targets.toArray(new Target[targets.size()]);
    }
    
    public static Target[] fromNames(List<String> nameList){
        List<Target> targets = new ArrayList<>();
        for(String name : nameList){
            OfflinePlayer offlinePlayer = SearchWhiteListPlayer.byName(name);
            try{
                targets.add(TargetGenerator.fromPlayer(offlinePlayer));
            } catch (Exception e){
                getLogger().info(e.getMessage());
                continue;
            }
        }
        return targets.toArray(new Target[targets.size()]);
    }
    
    public static Target[] fromNames(String[] names){
        List<String> nameList = Arrays.asList(names);
        return TargetGenerator.fromNames(names);
    }
    
    public static Target[] fromUniqueIds(List<String> uniqueIdList){
        List<Target> targets = new ArrayList<>();
        for(String uniqueId : uniqueIdList){
            OfflinePlayer offlinePlayer = SearchWhiteListPlayer.byUniqueId(uniqueId);
            try{
                targets.add(TargetGenerator.fromPlayer(offlinePlayer));
            } catch (Exception e){
                getLogger().info(e.getMessage());
                continue;
            }
        }
        return targets.toArray(new Target[targets.size()]);
    }
    
    public static Target[] fromUniqueIds(String[] uniqueIds){
        List<String> uniqueIdList = Arrays.asList(uniqueIds);
        return TargetGenerator.fromUniqueIds(uniqueIdList);
    }
}
