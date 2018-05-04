package swe.project;

public class Notification {

    String Date;
    String Type;
    boolean Seen;
    int ID;
    
      public Notification(String Date, String Type, boolean Seen, int ID) {
        this.Date = Date;
        this.Type = Type;
        this.Seen = Seen;
        this.ID = ID;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setSeen(boolean Seen) {
        this.Seen = Seen;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDate() {
        return Date;
    }

    public String getType() {
        return Type;
    }

    public boolean isSeen() {
        return Seen;
    }

    public int getID() {
        return ID;
    }
    
      

}
