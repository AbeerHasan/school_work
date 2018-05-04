package swe.project;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeSupport;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static swe.project.Registration.count;

public class place {
    String type;
    String name;
    int noofcheckins;
    int latitude;
    int longitude;
    int rate;
    ArrayList <tip> t=new ArrayList<>();
    ArrayList <tastes> tas=new ArrayList<>();
    int id;
	private static int count =0;
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public place(String type, String name, int noofcheckins, int latitude, int longitude, int rate, int id) {
        this.type = type;
        this.name = name;
        this.noofcheckins = noofcheckins;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rate = rate;
        this.id = id;
        count+=1;
    }
    public place() {
        type = "no type";
        name = "no name ";
        noofcheckins =0;
        latitude = 0;
        longitude = 0;
        rate =0;
        id = 0;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoofcheckins() {
        return noofcheckins;
    }

    public void setNoofcheckins(int noofcheckins) {
        this.noofcheckins = noofcheckins;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int no_ofcheck_ins,int no_of_likes) {
        int r=(int)(no_ofcheck_ins+no_of_likes)/2;
        rate =r ;
    }

    public ArrayList<tip> getT() {
        return t;
    }

    public void setT(ArrayList<tip> t) {
        this.t = t;
    }

    public ArrayList<tastes> getTas() {
        return tas;
    }

    public void setTas(ArrayList<tastes> tas) {
        this.tas = tas;
    }

    public int getId() {
        return id;
    }

    public void setId() {
		id =count;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
    public void getplaceinfo(String s) 
    {
        setName(s);
        getType();
        getNoofcheckins();
        getLatitude();
        getLongitude();
    }
    
    public void addcheckins()
    {
        ArrayList <Integer> a=new ArrayList<>();
        noofcheckins++;
        a.add(noofcheckins);
        System.out.println(a.get(noofcheckins));
    }
    public void addTip(String statement,String place_name){
           String url = "jdbc:mysql://localhost:3306/checkin";
        String user = "root";
        String password = "";
        
        PreparedStatement ps=null;
        Connection Con = null;
        Statement Stmt = null;
        ResultSet RS = null;
        boolean t=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Con = DriverManager.getConnection(url, user, password);

            DatabaseMetaData DBMetaData = Con.getMetaData();

            String EngineName = DBMetaData.getDatabaseProductName();
            String EngineVer = DBMetaData.getDatabaseProductVersion();

            System.out.println("DB Name " + EngineName);
            System.out.println("DB Version " + EngineVer);
           
           
            Stmt = Con.createStatement();
            
            String SQL = "SELECT ID,Name,Rate,Type,NofCheckins,No_of_likes FROM place where place.Name='"+place_name+"'";          
            ps=Con.prepareStatement(SQL);
            RS = ps.executeQuery();
            int ID=0;
            if(RS.first()){
                ID=RS.getInt(1);
            
            String sql="INSERT INTO tip(Recomend,place_ID) VALUES ('"+statement+"','"+ID+"')";
            Stmt.executeUpdate(sql);
            }
            /////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
    }
      public void addTastes(String fooditem,String environmentasp,String place_name){
           String url = "jdbc:mysql://localhost:3306/checkin";
        String user = "root";
        String password = "";

        Connection Con = null;
        Statement Stmt = null;
        ResultSet RS = null;
         PreparedStatement ps=null;
        boolean t=false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Con = DriverManager.getConnection(url, user, password);

            DatabaseMetaData DBMetaData = Con.getMetaData();

            String EngineName = DBMetaData.getDatabaseProductName();
            String EngineVer = DBMetaData.getDatabaseProductVersion();

            System.out.println("DB Name " + EngineName);
            System.out.println("DB Version " + EngineVer);
          
            Stmt = Con.createStatement();
            
            String SQL = "SELECT ID,Name,Rate,Type,NofCheckins,No_of_likes FROM place where place.Name='"+place_name+"'";          
            ps=Con.prepareStatement(SQL);
            RS = ps.executeQuery();
            int ID=0;
            if(RS.first()){
                ID=RS.getInt(1);
            
            String sql="INSERT INTO tastes(fooditem,environmentasp,place_ID) VALUES ('"+fooditem+"','"+environmentasp+"','"+ID+"')";
            Stmt.executeUpdate(sql);
            }
            /////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
    }
   /* public void makeaction()
    {
        
    }
    public void shownewoffer()
    {
        
    }*/
}

