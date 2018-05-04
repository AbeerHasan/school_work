package swe.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class LocationControl {
    
 public static ArrayList  <Check_in> checkinList=new ArrayList  <Check_in>();
///////////////////////////////////////////////////////////////
	private  GPS gps ;
    private place Place ;
    private String GUIName ;
    private Check_in checkin;
//    private  DBcontrol DB ;
    ////////////////////////////////////////////////////////////
    /*String url = "jdbc:mysql://localhost:3306/checkin";
    String user = "root";
    String password = "123456";

    Connection Con =null;
    Statement Stmt = null;
    ResultSet RS = null;

    try
    {
                Class.forName("com.mysql.jdbc.Driver");
                Con = DriverManager.getConnection(url, user, password);
                
                DatabaseMetaData DBMetaData = Con.getMetaData();
                String EngineName=DBMetaData.getDatabaseProductName();
                String EngineVer=DBMetaData.getDatabaseProductVersion();
                            Stmt.close();
                 RS.close();
                 Con.close();
    }
    catch(Exception cnfe)
    {
    System.err.println("Exception: " + cnfe);
    }*/
	////////////////////////////////////////////////////////////
    public GPS getGps() {
		return gps;
	}
	public void setGps(GPS gps) {
		this.gps = gps;
	}
	public String getGUIName() {
		return GUIName;
	}
	public void setGUIName(String gUIName) {
		GUIName = gUIName;
	}
    
    /*///////////////////////////////////////////////////////////
    public void show_nearby(){
    	 ArrayList <place> Places=new ArrayList<place>();
    	Places=gps.Get_location();
    	for(int i=0;i<Places.size();i++){
    		System.out.println(Places.get(i));
    	}
    	
    }

    public void Check_In(){
    	place p=new place();
    	p=gps.Get_location().get(0);
    	checkin.set_DAte_Place(p);
    	checkin.setID();
    	checkin.Show_check_In();
    	checkinList.add(checkin);
    }*/
    //ShowRoad()
}
