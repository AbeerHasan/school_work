package swe.project;

import java.util.ArrayList;

public class tastes {
    ArrayList <String> fooditem=new ArrayList<>();
    String stylecuisine;
    String environmentasp;
    UserAction action;
    String ID;

    public tastes(String stylecuisine, String environmentasp, UserAction action, String ID) {
        this.stylecuisine = stylecuisine;
        this.environmentasp = environmentasp;
        this.action = action;
        this.ID = ID;
    }

    public ArrayList<String> getFooditem() {
        return fooditem;
    }

    public void setFooditem(ArrayList<String> fooditem) {
        this.fooditem = fooditem;
    }

    public String getStylecuisine() {
        return stylecuisine;
    }

    public void setStylecuisine(String stylecuisine) {
        this.stylecuisine = stylecuisine;
    }

    public String getEnvironmentasp() {
        return environmentasp;
    }

    public void setEnvironmentasp(String environmentasp) {
        this.environmentasp = environmentasp;
    }

    public UserAction getAction() {
        return action;
    }

    public void setAction(UserAction action) {
        this.action = action;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public void gettastes()
    {
        getFooditem();
        getStylecuisine();
        getEnvironmentasp();
        getAction();
        getID();
    }
    public void removetastes( int x)
    {
        
        ArrayList <tastes> s=new ArrayList<>();
        s.remove(x);
    }
    /*public void makeaction()
    {
        
    }*/
}

