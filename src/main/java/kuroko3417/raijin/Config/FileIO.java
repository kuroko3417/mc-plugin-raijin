package kuroko3417.raijin.Config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kuroko3417.raijin.Setting;
import kuroko3417.raijin.TargetLists;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class FileIO {

    private final String CONFIG_FILE_PATH       = "./plugins/config/";
    private final String SETTING_FILE_NAME      = "setting.json";
    private final String TARGET_LISTS_FILE_NAME = "target_lists.json";
    private final String WHITE_LIST_FILE_NAME   = "whitelist.json";

    public FileIO(){
        File file = new File(this.CONFIG_FILE_PATH);

        if(!file.exists()){
            file.mkdir();
        }
        Setting setting         = new Setting();
        TargetLists targetLists = new TargetLists();

        String settinFilePath      = "";
        String targetListsFilePath = "";
        try {
            settinFilePath      = this.getFilePath(setting);
            targetListsFilePath = this.getFilePath(targetLists);
        } catch (Exception e){
            e.printStackTrace();
        }

        File settingfile     = new File(settinFilePath);
        File targetListsFile = new File(targetListsFilePath);

        if(!settingfile.exists()){
            this.Save(new Setting());
        }

        if(!targetListsFile.exists()){
            this.Save(new TargetLists());
        }
    }

    public Marker Load(Marker configClass){
        try {
            String filePath = this.getFilePath(configClass);
            return this.getClassInstance(configClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Save(Marker configClass){
        Gson gson = new GsonBuilder().create();
        try {
            String filePath = this.getFilePath(configClass);
            Writer writer = new FileWriter(filePath);
            gson.toJson(configClass, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Marker getClassInstance(Marker configClass) throws LoginException{
        if(configClass instanceof Setting){
            return new Setting();
        }
        if(configClass instanceof TargetLists){
            return new TargetLists();
        }
        throw new LoginException("The logic is incorrect");
    }

    private String getFilePath(Marker configClass) throws LoginException{
        if(configClass instanceof Setting){
            return this.CONFIG_FILE_PATH + this.SETTING_FILE_NAME;
        }
        if(configClass instanceof TargetLists){
            return this.CONFIG_FILE_PATH + this.TARGET_LISTS_FILE_NAME;
        }
        throw new LoginException("The logic is incorrect");
    }

}
