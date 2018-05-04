package swe.project;

public class tip {
    String tip;
    String id;
    UserAction action;

    public tip(String tip, String id, UserAction action) {
        this.tip = tip;
        this.id = id;
        this.action = action;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserAction getAction() {
        return action;
    }

    public void setAction(UserAction action) {
        this.action = action;
    }
    
    /*public void makeaction()
    {
        
    }*/
    
}
