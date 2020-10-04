package kuroko3417.raijin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class SearchWhiteListPlayer {

    // コマンドの引数を渡す想定なので引数はString型で検索できるように実装
    
    public static OfflinePlayer byName(String name){
        for(OfflinePlayer offlinePlayer : getServer().getWhitelistedPlayers()){
            if(!offlinePlayer.getName().equals(name)){
                continue;
            }
            return offlinePlayer;
        }
        return null;
    }
    
    public static OfflinePlayer byUniqueId(String uniqueId){
        for(OfflinePlayer offlinePlayer : getServer().getWhitelistedPlayers()){
            if(!offlinePlayer.getUniqueId().equals(uniqueId)){
                continue;
            }
            return offlinePlayer;
        }
        return null;
    }
    
    // List<String>、String[]どちらでも検索できるようにオーバーロード
    
    public static OfflinePlayer[] byNames(List<String> nameList){
        int searchCount = nameList.size();
        List<OfflinePlayer> offlinePlayers = new ArrayList<>();
        for(OfflinePlayer offlinePlayer : getServer().getWhitelistedPlayers()){
            if(searchCount == 0){
                break;
            }
            String offlinePlayerName = offlinePlayer.getName();
            if(!nameList.contains(offlinePlayerName)){
                continue;
            }
            offlinePlayers.add(offlinePlayer);
        }
        return offlinePlayers.toArray(new OfflinePlayer[nameList.size()]);
    }
    
    public static OfflinePlayer[] byNames(String[] names){
        List<String> nameList = Arrays.asList(names);
        return SearchWhiteListPlayer.byNames(nameList);
    }
    
    public static OfflinePlayer[] byUniqueIds(List<String> uniqueIdList){
        int searchCount = uniqueIdList.size();
        List<OfflinePlayer> offlinePlayers = new ArrayList<>();
        for(OfflinePlayer offlinePlayer : getServer().getWhitelistedPlayers()){
            if(searchCount == 0){
                break;
            }
            String offlinePlayerUniqueId = offlinePlayer.getUniqueId().toString();
            if(!uniqueIdList.contains(offlinePlayerUniqueId)){
                continue;
            }
            offlinePlayers.add(offlinePlayer);
            searchCount--;
        }
        return offlinePlayers.toArray(new OfflinePlayer[uniqueIdList.size()]);
    }
    
    public static OfflinePlayer[] byUniqueIds(String[] uniqueIds){
        List<String> uniqueIdList = Arrays.asList(uniqueIds);
        return SearchWhiteListPlayer.byUniqueIds(uniqueIdList);
    }
    
    public static OfflinePlayer[] all(){
        List<OfflinePlayer> offlinePlayer = new ArrayList<>();
        for (OfflinePlayer player : Bukkit.getServer().getWhitelistedPlayers()){
            offlinePlayer.add(player);
        }
        return offlinePlayer.toArray(new OfflinePlayer[offlinePlayer.size()]);
    }
    
}
