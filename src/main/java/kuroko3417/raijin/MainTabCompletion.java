package kuroko3417.raijin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import kuroko3417.raijin.Config.FileIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;


public class MainTabCompletion implements TabCompleter {
    
    public FileIO fileIo;
    public Setting setting;
    public TargetLists targetLists;
    public ObserverList observerList;
    public PurgatoryList purgatoryList;
    
    public MainTabCompletion(Raijin raijin){
        this.fileIo        = raijin.fileIo;
        this.setting       = raijin.setting;
        this.targetLists   = raijin.targetLists;
        this.observerList  = raijin.observerList;
        this.purgatoryList = raijin.purgatoryList;
    }
    
    @Override
    public List<String> onTabComplete(
            CommandSender sender, Command command, String alias, String[] args) {
        List<String> complements = new ArrayList<>();
        
        int length = args.length;
        if(length == 1){
            // メインコマンドの候補を表示
            complements.add("ts");
            complements.add("rs");
            complements.add("pur");
            complements.add("obs");
            complements.add("target");
            complements.add("targets");
            complements.add("power");
            complements.add("death");
            return complements;
        }
        
        if(2 <= length){
            String mainCommand = args[0];
            switch (mainCommand){
                case "pur":
                    return this.purComplements(args);
                case "obs":
                    return this.obsComplements(args);
                case "ts":
                    // 候補を表示しない
                    return complements;
                case "rs":
                    return this.rsComplements(args);
                case "target":
                    return this.targetComplements(args);
                case "targets":
                    return this.targetsComplements(args);
                case "power":
                    return this.powerComplements(args);
                case "death":
                    return this.deathComplements(args);
                default:
                    // 候補を表示しない
                    return complements;
            }
        }
        return complements;
    }
    
    private List<String>purComplements(String[] args){
        List<String> complements = new ArrayList<>();
        int length = args.length;
        if(length == 2){
            complements.add("add");
            complements.add("remove");
            complements.add("list");
            return complements;
        }
    
        if(3 <= length){
            String subCommand = args[1];
            // 複数指定されたプレイヤー名を取得
            String[] argPlayers = Arrays.copyOfRange(args, 2, args.length - 1);
            List<String> argPlayerList = Arrays.asList(argPlayers);
            // 設定済みのプレイヤー一覧を取得
            List<String> setTargetList = this.purgatoryList.getAllTargetNameList();
            // 引数で指定されたプレイヤー一覧を取得
            List<String> whiteListTargets =
                    this.offlinePlayerToNameList(SearchWhiteListPlayer.all());
            
            switch (subCommand){
                case "add":
                    whiteListTargets.removeAll(setTargetList);
                    whiteListTargets.removeAll(argPlayerList);
                    return whiteListTargets;
                case "remove":
                    setTargetList.removeAll(argPlayerList);
                    return setTargetList;
                default:
                    return complements;
            }
        }
        return complements;
    }
    
    private List<String>obsComplements(String[] args){
        List<String> complements = new ArrayList<>();
        int length = args.length;
        if(length == 2){
            complements.add("add");
            complements.add("remove");
            complements.add("list");
            return complements;
        }
    
        if(3 <= length){
            String subCommand = args[1];
            // 複数指定されたプレイヤー名を取得
            String[] argPlayers = Arrays.copyOfRange(args, 2, args.length - 1);
            List<String> argPlayerList = Arrays.asList(argPlayers);
            // 設定済みのプレイヤー一覧を取得
            List<String> setTargetList = this.observerList.getAllTargetNameList();
            // 引数で指定されたプレイヤー一覧を取得
            List<String> whiteListTargets =
                    this.offlinePlayerToNameList(SearchWhiteListPlayer.all());
        
            switch (subCommand){
                case "add":
                    whiteListTargets.removeAll(setTargetList);
                    whiteListTargets.removeAll(argPlayerList);
                    return whiteListTargets;
                case "remove":
                    setTargetList.removeAll(argPlayerList);
                    return setTargetList;
                default:
                    return complements;
            }
        }
        return complements;
    }
    
    private List<String>rsComplements(String[] args){
        String[] argPlayers = Arrays.copyOfRange(args, 1, args.length - 1);
        List<String> argPlayerList = Arrays.asList(argPlayers);
        List<String> onlinePlayerNames = this.getOnlinePlayerNames();
        List<String> groupNames  = this.targetLists.getListNameList();
        onlinePlayerNames.addAll(groupNames);
        onlinePlayerNames.removeAll(argPlayerList);
        return onlinePlayerNames;
    }
    
    private List<String>targetComplements(String[] args){
        List<String> complements = new ArrayList<>();
        int length = args.length;
        if(length == 2){
            complements.add("add");
            complements.add("remove");
            complements.add("list");
            return complements;
        }
        
        if(length == 3){
            String subCommand = args[1];
            switch (subCommand){
                case "add":
                case "remove":
                case "list":
                    // 設定グループ名を表示
                    complements = this.targetLists.getListNameList();
                default:
                    return complements;
            }
        }
        
        if(4 <= length){
            String subCommand = args[1];
            String groupName = args[2];

            List<String> listNameList = this.targetLists.getListNameList();

            String[] argPlayers = Arrays.copyOfRange(args, 3, args.length - 1);
            List<String> argPlayerList = Arrays.asList(argPlayers);
            TargetList targetList = this.targetLists.find(groupName);

            Boolean groupExists = false;
            List<String> targetNameList = new ArrayList<>();
            if(targetList != null){
                groupExists = true;
                targetNameList = targetList.getAllTargetNameList();
            }
    
            switch (subCommand){
                case "add":
                    List<String> allTargetNames =
                            offlinePlayerToNameList(SearchWhiteListPlayer.all());
                    if(groupExists){
                        allTargetNames.removeAll(targetNameList);
                    }
                    allTargetNames.removeAll(argPlayerList);
                    return allTargetNames;
                case "remove":
                    if(!groupExists){
                        return complements;
                    }
                    targetNameList.removeAll(argPlayerList);
                    return targetNameList;
                default:
                    return complements;
            }
        }
        return complements;
    }
    
    private List<String>targetsComplements(String[] args){
        List<String> complements = new ArrayList<>();
        int length = args.length;
        if(length == 2) {
            complements.add("delete");
            complements.add("clear");
            complements.add("list");
            return complements;
        }
    
        if(length == 3){
            String subCommand = args[1];
            switch (subCommand){
                case "delete":
                case "clear":
                    // 設定グループ名を表示
                    complements = this.targetLists.getListNameList();
                case "list":
                default:
                    return complements;
            }
        }
        
        
        return complements;
    }
    
    private List<String>powerComplements(String[] args){
        List<String> complements = new ArrayList<>();
        int length = args.length;
        if(length == 2) {
            complements.add("0~1000");
            complements.add("reset");
            return complements;
        }
        return complements;
    }
    
    private List<String>deathComplements(String[] args){
        List<String> complements = new ArrayList<>();
        int length = args.length;
        if(length == 2) {
            complements.add("true");
            complements.add("false");
            complements.add("reset");
            return complements;
        }
        return complements;
    }
    
    private List<String> getOnlinePlayerNames(){
        List<String> complements = new ArrayList<>();
        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            complements.add(player.getName());
        }
        return complements;
    }







    private List<String> offlinePlayerToNameList(OfflinePlayer[] offlinePlayers){
        List<String> nameList = new ArrayList<>();
        for (OfflinePlayer offlinePlayer : offlinePlayers){
            nameList.add(offlinePlayer.getName());
        }
        return nameList;
    }
    
    
    private List<String>addWhitelistPlayerNames(){
        List<String> complements = new ArrayList<>();
    
        for (OfflinePlayer player : Bukkit.getServer().getWhitelistedPlayers()){
            complements.add(player.getName());
        }
        return complements;
    }
}
