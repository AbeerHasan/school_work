
package swe.project;

import java.util.ArrayList;

public class Conversation {
    
    ArrayList<String> User = new ArrayList<String>();
    String Message;
    String Time;
    boolean Seen;
    int ID;

    public Conversation(String Message, String Time, boolean Seen, int ID) {
        this.Message = Message;
        this.Time = Time;
        this.Seen = Seen;
        this.ID = ID;
    }

    public void setUser(ArrayList<String> User) {
        this.User = User;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public void setSeen(boolean Seen) {
        this.Seen = Seen;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<String> getUser() {
        return User;
    }

    public String getMessage() {
        return Message;
    }

    public String getTime() {
        return Time;
    }

    public boolean isSeen() {
        return Seen;
    }

    public int getID() {
        return ID;
    }
    
    
    
}
