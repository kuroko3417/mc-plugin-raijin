package kuroko3417.raijin;

public class Target {

    private String name;
    private String uuid;

    public Target(String name, String uuid){
        this.name = name;
        this.uuid = uuid;
    }

    public String getName(){
        return this.name;
    }

    public String getUniqueId(){
        return this.uuid;
    }
}
