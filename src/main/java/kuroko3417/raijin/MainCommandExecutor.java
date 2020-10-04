package kuroko3417.raijin;
import kuroko3417.raijin.DeathState.Daipan;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import kuroko3417.raijin.Config.FileIO;
import kuroko3417.raijin.DeathState.DeathList;
import kuroko3417.raijin.DeathState.LightningStrike;


import java.util.*;

import static org.bukkit.Bukkit.*;


public class MainCommandExecutor implements CommandExecutor {

    public Raijin raijin;
//    public FileIO fileIo;
    public Setting setting;
    public DeathList deathList;
    public TargetLists targetLists;
    public ObserverList observerList;
    public PurgatoryList purgatoryList;

    public MainCommandExecutor(Raijin raijin) {
        this.raijin        = raijin;
//        this.fileIo        = raijin.fileIo;
        this.setting       = raijin.setting;
        this.deathList     = raijin.deathList;
        this.targetLists   = raijin.targetLists;
        this.observerList  = raijin.observerList;
        this.purgatoryList = raijin.purgatoryList;
    }
    
    @Override
    public boolean onCommand(
            CommandSender sender, Command cmd, String commandLabel, String[] args) {
        int length = args.length;

        if(length == 1){
            String mainCommand = args[0];
            String message     = "";
            switch (mainCommand){
                case "power":
                    message =
                            "現在の設定は" +
                            String.valueOf(this.setting.getLightningPower()) +
                            "です";
                    sender.sendMessage(message);
                    return true;
    
                case "death":
                    message =
                            "現在の設定は" +
                            String.valueOf(this.setting.getLightningStrikeDeath()) +
                            "です";
                    sender.sendMessage(message);
                    return true;
                case "ts":
                    return this.tsCommand(sender, args);
    
                default:
                    sender.sendMessage("コマンドの指定が不正です");
                    return false;
            }
        }

        if(2 <= length){
            String mainCommand = args[0];
  
            Boolean result = false;
            switch (mainCommand){
                case "pur":
                    result = this.purCommand(sender, args);
                    break;
                case "obs":
                    result = this.obsCommand(sender, args);
                    break;
                case "rs":
                    result = this.rsCommand(sender, args);
                    break;
                case "target":
                    result = this.targetCommand(sender, args);
                    break;
                case "targets":
                    result = this.targetsCommand(sender, args);
                    break;
                case "death":
                    result = this.deathCommand(sender, args);
                    break;
                case "easteregg":
                    result = this.easterEggCommand(sender, args);
                    break;
                case "power":
                    result = this.powerCommand(sender, args);
                    break;
                default:
                    result = false;
                    break;
            }
            if(!result){
                sender.sendMessage("コマンドの指定が不正です");
            }
//            this.saveSetting();
//            this.saveTargetList();
            return result;
        }
        sender.sendMessage("コマンドの指定が不正です");
        return false;
    }
    
    private boolean purCommand(CommandSender sender, String[] args){
        int length = args.length;
    
        if(length == 2){
            String subCommand = args[1];
            switch (subCommand){
                case "list":
                    String[] purTargetNames = this.purgatoryList.getAllTargetNames();
                    String players = purTargetNames.length != 0 ?
                            String.join(", ", purTargetNames):
                            "設定されていません";
                    sender.sendMessage("設定済みのプレイヤー一覧: " + players);
                    return true;
                default:
                    return false;
            }
        }
    
        if(3 <= length){
            String subCommand = args[1];
    
            // 複数指定されたプレイヤー名を取得
            String[] argPlayers = Arrays.copyOfRange(args, 2, args.length);
            List<String> argPlayerList = Arrays.asList(argPlayers);
    
            switch (subCommand){
                case "add":
                    for(String playerName : argPlayerList){
                        if(this.purgatoryList.existNameId(playerName)){
                            sender.sendMessage(playerName + "はすでに登録済みです");
                            continue;
                        }
                        this.purgatoryList.add(TargetGenerator.fromName(playerName));
                    }
                    sender.sendMessage("プレイヤーを追加しました: " + String.join(", ", argPlayerList));
                    return true;
                case "remove":
                    for(String playerName : argPlayerList){
                        if(!this.purgatoryList.existNameId(playerName)){
                            sender.sendMessage(playerName + "は登録されていません");
                            continue;
                        }
                        Target removeTarget = this.purgatoryList.findByName(playerName);
                        this.purgatoryList.remove(removeTarget);
                    }
                    sender.sendMessage("プレイヤーを除外しました: " + String.join(", ", argPlayerList));
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
    
    private boolean obsCommand(CommandSender sender, String[] args){
        int length = args.length;
        
        if(length == 2){
            String subCommand = args[1];
            switch (subCommand){
                case "list":
                    String[] obsTargetNames = this.observerList.getAllTargetNames();
                    String players = obsTargetNames.length != 0 ?
                            String.join(", ", obsTargetNames):
                            "設定されていません";
                    sender.sendMessage("設定済みのプレイヤー一覧: " + players);
                    return true;
                default:
                    return false;
            }
        }
    
        if(3 <= length){
            String subCommand = args[1];
        
            // 複数指定されたプレイヤー名を取得
            String[] argPlayers = Arrays.copyOfRange(args, 2, args.length);
            List<String> argPlayerList = Arrays.asList(argPlayers);
        
            switch (subCommand){
                case "add":
                    for(String playerName : argPlayerList){
                        if(this.observerList.existNameId(playerName)){
                            sender.sendMessage(playerName + "はすでに登録済みです");
                            continue;
                        }
                        this.observerList.add(TargetGenerator.fromName(playerName));
                    }
                    sender.sendMessage("プレイヤーを追加しました: " + String.join(", ", argPlayerList));
                    return true;
                case "remove":
                    for(String playerName : argPlayerList){
                        if(!this.observerList.existNameId(playerName)){
                            sender.sendMessage(playerName + "は登録されていません");
                            continue;
                        }
                        Target removeTarget = this.observerList.findByName(playerName);
                        this.observerList.remove(removeTarget);
                    }
                    sender.sendMessage("プレイヤーを除外しました: " + String.join(", ", argPlayerList));
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
    
    /**
     * ネタ用のコマンド
     * /ri tsで雷神が現れて0~10回の落雷を落としてランダムにプレイヤーをキルするネタ
     * イースターエッグを有効にすると
     * /ri tsでkono_aが台パンをしてランダムにプレイヤーをキルするネタ
     */
    private boolean tsCommand(CommandSender sender, String[] args){
        int length = args.length;
        if(length != 1){
            return false;
        }
        String commandExePlayerName = sender.getName();
    
        boolean easterEggMode = this.setting.getEasterEggState();
        
        if(!easterEggMode){
            getServer().dispatchCommand(getServer().getConsoleSender(), "weather thunder");
        }
    
        int targetNumber = this.targetNumber();

        Bukkit.getScheduler().scheduleSyncDelayedTask(this.raijin, new Runnable() {
            public void run() {
                if(easterEggMode){
                    // 引用元:https://twitter.com/konoa0120/status/1312506117092638721
                    getServer().broadcastMessage("<kono_a> うおんうおんぶんぶんぶんぶんぶん");
                } else {
                    getServer().broadcastMessage("<?????> 誰だ...我の眠りをさまたげる者は...");
                }
            }
        }, 160L);
    
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.raijin, new Runnable() {
            public void run() {
                if(easterEggMode){
                    // 引用元:https://twitter.com/konoa0120/status/1286969039693070337
                    getServer().broadcastMessage("<kono_a> 最近全然台パンしてないなー");
                } else {
                    getServer().broadcastMessage("<?????> 人間風情が我の邪魔をするな...");
                }
            }
        }, 300L);
    
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.raijin, new Runnable() {
            public void run() {
                if(easterEggMode){
                    // 言葉捏造
                    getServer().broadcastMessage("<kuno_a> 久しぶりに台パンするか");
                } else {
                    getServer().broadcastMessage("<?????> 汚らわしい人間どもめ。我の苦しみと憎しみを知るがいい...");
                }
            }
        }, 460L);
    
        int power = this.setting.getLightningPower();
        
        boolean deathState = this.setting.getLightningStrikeDeath();
        
        DeathList tmpDeathList = this.deathList;
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.raijin, new Runnable() {
            public void run() {
                Player[] players = getServer().getOnlinePlayers().toArray(new Player[0]);
            
                int indexLimit = players.length;
                Random rand = new Random();
                
                List<Player> exclusionList = new ArrayList<>();
            
                for(int i = 0; i < targetNumber; i++){
                    int targetIndex = rand.nextInt(indexLimit);
                    Player targetPlayer = players[targetIndex];

                    if(targetPlayer.equals(commandExePlayerName)){
                        // 自分を除外
                        continue;
                    }
                    
                    if(exclusionList.contains(targetPlayer)){
                        // すでに処理が実行されたプレイヤーを除外
                        continue;
                    }
    
                    exclusionList.add(targetPlayer);
                    
                    if(easterEggMode){
                        tmpDeathList.add(new Daipan(targetPlayer));
//                        // イースターエッグがtrueのときは確殺のオプション無視で対象を必ず殺す
//                        // 通常のri tsコマンドよりイースターエッグを有効にしたri tsのほうが強い（要するにkono_aの台パンが最強）
                        targetPlayer.getWorld().createExplosion(targetPlayer.getLocation(), power);
                        targetPlayer.damage(9999);
                    } else {
                        // イースターエッグがfalseのときは/ri rsと同じ挙動にする
                        targetPlayer.getWorld().strikeLightning(targetPlayer.getLocation());
                        targetPlayer.getWorld().createExplosion(targetPlayer.getLocation(), power);
                        if(deathState){
                            // NOTE:落雷、爆発で倒せなかったときのために確殺するダメージを与える
                            targetPlayer.damage(9999);
                        }
                    }
                }
            }
        }, 560L);
    
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.raijin, new Runnable() {
            public void run() {
                if(!easterEggMode){
                    getServer().dispatchCommand(getServer().getConsoleSender(), "weather clear");
                }
            }
        }, 660L);
    
        return true;
    }
    
    private boolean rsCommand(CommandSender sender, String[] args){
        int length = args.length;
        
        if(2 <= length){
            String[] argPlayers = Arrays.copyOfRange(args, 1, args.length);
            List<String> targetOrListNames = Arrays.asList(argPlayers);
    
            Player targetPlayer;
            for(String targetOrListName : targetOrListNames){
                targetPlayer = getServer().getPlayerExact(targetOrListName);
                if(targetPlayer != null){
                    sender.sendMessage(targetOrListName + "に雷を落とした");
                    this.deathList.add(new LightningStrike(targetPlayer));
                    this.lightningStrike(targetPlayer);
                    continue;
                }
                TargetList targetList = this.targetLists.find(targetOrListName);
                if(targetList == null){
                    sender.sendMessage("指定したターゲットが見つかりません");
                    return true;
                }
                String[] targetNames = targetList.getAllTargetNames();
                sender.sendMessage(targetOrListName + "に雷を落とした");
                for(String targetName : targetNames){
                    targetPlayer = getServer().getPlayerExact(targetName);
                    if(targetPlayer == null){
                        sender.sendMessage("指定したターゲットが見つかりません");
                        continue;
                    }
                    this.deathList.add(new LightningStrike(targetPlayer));
                    this.lightningStrike(targetPlayer);
                }
            }
            return true;
        }
        return false;
    }
    
    private boolean targetCommand(CommandSender sender, String[] args){
        int length = args.length;
        String subCommand  = "";
        String groupName   = "";

        if(length == 3){
            subCommand  = args[1];
            groupName   = args[2];
    
    
            switch (subCommand){
                case "list":
                    TargetList targetList = this.targetLists.find(groupName);
                    if(targetList == null){
                        
                        // 指定したグループ名が存在しない
                        sender.sendMessage("指定したtargetListは存在しません");
                        return true;
                    }
                    List<String> targetNames = targetList.getAllTargetNameList();
                    String players = targetNames.size() != 0 ?
                            String.join(", ", targetNames):
                            "設定されていません";
                    sender.sendMessage("設定済みのプレイヤー一覧: " + players);
                    return true;
                default:
                    return false;
            }
        }
        
        if(4 <= length){
            subCommand  = args[1];
            groupName   = args[2];
            String[] argPlayers = Arrays.copyOfRange(args, 3, args.length);
            List<String> argPlayerList = Arrays.asList(argPlayers);
    
            TargetList targetList = this.targetLists.find(groupName);
            switch (subCommand){
                case "add":
                    if(targetList == null){
                        targetList = new TargetList(groupName);
                        this.targetLists.add(targetList);
                    }
                    targetList.adds(TargetGenerator.fromNames(argPlayerList));
                    return true;
                case "remove":
                    if(targetList == null){
                        sender.sendMessage("指定したtargetListは存在しません");
                        return true;
                    }
                   
                    for(String playerName : argPlayerList){
                        targetList.remove(targetList.findByName(playerName));
                    }
                    sender.sendMessage(
                            "targetListからプレイヤーを除外しました: " +
                            String.join(", ", argPlayers));
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
    
    private boolean targetsCommand(CommandSender sender, String[] args){
        int length = args.length;
        String subCommand  = "";
        String groupName   = "";
    
        if(length == 2){
            subCommand  = args[1];
    
            switch (subCommand){
                case "list":
                    String[] listNames = this.targetLists.getListNames();
                    sender.sendMessage("targetList一覧: " + String.join(", ", listNames));
                    return true;
                default:
                    return false;
            }
        }
        if(length == 3){
            subCommand  = args[1];
            groupName   = args[2];
        
            TargetList targetList = this.targetLists.find(groupName);
            switch (subCommand){
                case "clear":
                    if(targetList == null){
                        sender.sendMessage("指定したtargetListは存在しません");
                        return true;
                    }
                    targetList.clear();
                    sender.sendMessage("設定したプレイヤーをすべて除外しました");
                    return true;
                case "delete":
                    targetList = this.targetLists.find(groupName);
                    if(targetList == null){
                        sender.sendMessage("指定したtargetListは存在しません");
                        return true;
                    }
                    this.targetLists.remove(targetList);
                    sender.sendMessage("targetListを削除しました: " + targetList.getName());
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
    
    private boolean deathCommand(CommandSender sender, String[] args){
        int length = args.length;
    
        if(length == 2) {
            String mainCommand = args[0];
            String option = args[1];
            if(option.equals("reset")){
                this.setting.resetLightningStrikeDeath();
                sender.sendMessage("設定を初期化しました");
                return true;
            }
    
            if(option.equals("true")) {
                this.setting.setLightningStrikeDeath(Boolean.valueOf(option));
                sender.sendMessage("設定をtrueに変更しました");
                return true;
            }
    
            if(option.equals("false")){
                this.setting.setLightningStrikeDeath(Boolean.valueOf(option));
                sender.sendMessage("設定をfalseに変更しました");
                return true;
            }
        }
        return false;
    }
    
    private boolean easterEggCommand(CommandSender sender, String[] args){
        int length = args.length;
        
        if(length == 2) {
            String mainCommand = args[0];
            String option = args[1];
            if(option.equals("reset")){
                this.setting.resetEasterEggState();
                sender.sendMessage("設定を初期化しました");
                return true;
            }
            
            if(option.equals("true")) {
                this.setting.setEasterEggState(Boolean.valueOf(option));
                sender.sendMessage("設定をtrueに変更しました");
                return true;
            }
            
            if(option.equals("false")){
                this.setting.setEasterEggState(Boolean.valueOf(option));
                sender.sendMessage("設定をfalseに変更しました");
                return true;
            }
        }
        return false;
    }
    
    private boolean powerCommand(CommandSender sender, String[] args){
        int length = args.length;
    
        if(length == 2) {
            String mainCommand = args[0];
            String option      = args[1];
    
            if(option.equals("reset")){
                this.setting.resetLightningPower();
                sender.sendMessage("設定を初期化しました");
                return true;
            }
    
            int power = Integer.parseInt(option);
    
            try {
                this.setting.setLightningPower(power);
                sender.sendMessage("設定を" + power+"に変更しました");
                return true;
            } catch (Exception e){
                getLogger().info(e.getMessage());
                return false;
            }
        }
        return false;
    }
    
    
    private void lightningStrike(Player player){
        player.getWorld().strikeLightning(player.getLocation());
        player.getWorld().createExplosion(player.getLocation(), this.setting.getLightningPower());
        if(this.setting.getLightningStrikeDeath()){
            // NOTE:落雷、爆発で倒せなかったときのために確殺するダメージを与える
            player.damage(9999);
        }
    }
    
    // NOTE: 設定の保存機能は今の所必要ないので実装しない
    
//    private void saveSetting(){
//        this.fileIo.Save(this.setting);
//    }
//
//    private void saveTargetList(){
//        this.fileIo.Save(this.targetLists);
//    }
    
    private int targetNumber(){
        return (int)(Math.random() * 10);
    }
    
    private int targetIndex(int limit){
        Random rand = new Random();
        return rand.nextInt(limit);
    }
    
}
