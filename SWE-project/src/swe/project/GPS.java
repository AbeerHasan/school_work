package swe.project;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
 
public class GPS {

	/////////////////////////////////////////////////////////////////////////////
    private int latitude ;
	private int longitude ;
	
	/////////////////////////////////////////////////////////////////////////////
	public GPS(){
		latitude=0;
		longitude=0;
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
	///////////////////////////////////////////////////////////////////////////////
/*	public ArrayList <place> Get_location(){
		int lng=0,lt=0;
		ArrayList <place>nearby_Places=new ArrayList<place>();
		
		lt=getLatitude();
		lng=getLongitude();
		for (int i=0;i<PlacesList.size();i++){
		     if((lt-PlacesList.get(i).getLatitude()<=1)&&lng-PlacesList.get(i).getLongitude()<=1){
		    	 nearby_Places.add(PlacesList.get(i));
		     }
		}
		return nearby_Places;
		/*String url = "jdbc:mysql://localhost:3306/checkin";
	    String user = "root";
	    String password = "123456";

	    Connection Con =null;
	   // Statement Stmt = null;
	    ResultSet RS = null;

	    try
	    {
	                Class.forName("com.mysql.jdbc.Driver");
	                Con = DriverManager.getConnection(url, user, password);
	                
	                DatabaseMetaData DBMetaData = Con.getMetaData();
	                String EngineName=DBMetaData.getDatabaseProductName();
	                String EngineVer=DBMetaData.getDatabaseProductVersion();
	                
	                Statement st = Con.createStatement();           
	                st.executeUpdate("INSERT INTO `user` (`ID`, `Fname`, `Lname`, `Email`, `Country`, `Gender`, `Password`, `Phone`, `Status`, `SpCode`, `Premium`)"+" VALUES(1, 'Abeer', 'Saber', 'bero558@hotmail.com', 'Egypt', 'female',123456,11247, 'online', 5698746, 0");
	                
	                st.close();
	                 RS.close();
	                 Con.close();
	    }
	    catch(Exception cnfe)
	    {
	    System.err.println("Exception: " + cnfe);
	    }*/
	//}
   // Show_Map()

}
