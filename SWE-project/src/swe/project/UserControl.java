package swe.project;
//////////////////////////////////////////
import gui.Home;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import net.proteanit.sql.DbUtils;
///////////////////////////////////////////////////////
public class UserControl {
    	public ArrayList <place>PlacesList=new ArrayList<place>();
        
    private User user;
    private Login login;
    private String GUIName;
    public UserControl() {
    }
    public UserControl(User user, Login login, String GUIName) {
        this.user = user;
        this.login = login;
        this.GUIName = GUIName;
        //this.dbcontrol = dbcontrol;
    }

 /*   public void setDbcontrol(DBControl dbcontrol) {
        this.dbcontrol = dbcontrol;
    }
*/
    public void setGUIName(String GUIName) {
        this.GUIName = GUIName;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setUser(User user) {
        this.user = user;
    }

   /* public DBControl getDbcontrol() {
        return dbcontrol;
    }*/
    public String getGUIName() {
        return GUIName;
    }

    public Login getLogin() {
        return login;
    }

    public User getUser() {
        return user;
    }
    
    
    public void GetplaceInfo(String name)
    {
        //hna msh 3rfa elmafrood a3ml a aget info
        User u=new User();
        boolean found=false;
        for(int i=0; i<PlacesList.size() ;i++)
        {
            if(PlacesList.get(i).getName()==name)
            {
                found=true;
                System.out.println("Place name"+PlacesList.get(i).getName());
                System.out.println("Place Type"+PlacesList.get(i).getType());
                System.out.println("Place ID"+PlacesList.get(i).getId());
            }
        }
        if(!found)
        {
            System.out.println("this place is not in placelist");
        }
    }
    
    public void Searchplace(String PlaceName)
    {
        User u=new User();
        boolean found=false;
        System.out.println("Enter place name ");
        String name;
        name="cairo festivale";
        for(int i=0; i<PlacesList.size() ;i++)
        {
            if(PlacesList.get(i).getName()==name)
            {
                found=true;
                System.out.println("Place in found in placelist");
            }
        }
        if(!found)
        {
            System.out.println("this place is not in placelist");
        }
    }    
    
    public void Saveplace(place Place) //aw mmkn bdl ma ab3t place flparameter a3ml cin ll7agat w a3ml add flarray list
    {
        User u= new User();
        u.placeList.add(Place);
        //w a3ml add fldatabase
    }
    
    public boolean AddFriend(String ID)
    {
        boolean Check = false;
        String url = "jdbc:mysql://localhost:3306/checkin";
        String user = "root";
        String password = "";

        Connection Con = null;
        Statement Stmt = null;
        ResultSet RS = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Con = DriverManager.getConnection(url, user, password);

            DatabaseMetaData DBMetaData = Con.getMetaData();

            String EngineName = DBMetaData.getDatabaseProductName();
            String EngineVer = DBMetaData.getDatabaseProductVersion();

            System.out.println("DB Name " + EngineName);
            System.out.println("DB Version " + EngineVer);

            Stmt = Con.createStatement();
            String SQL = "select * from user";
            RS = Stmt.executeQuery(SQL);
                

            
            //////////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }

        return false;
    }

    public void DeleteFriend(String mail)
    {
                        
        User u= new User();
        boolean found=false;
        
        for (int i=0 ;i<u.FriendList.size() ;i++)
        {
          if(u.FriendList.get(i).getEmail()==mail)
          {
            found =true;
            System.out.println("User Name"+ u.getUserName());
            System.out.println("User First Name" + u.getFirstName());       
            System.out.println("User Last Name"+ u.getLastName());
            System.out.println("User Email"+ u.getEmail());
            System.out.println("User Password"+ u.getPassWord());
            System.out.println("User Country"+ u.getCountry());
            System.out.println("User Gender"+ u.getGender());
            System.out.println("User ID"+ u.getUserID());
            System.out.println("User Mobile Phone"+ u.getMobilePhone());
            System.out.println("User Status"+ u.getStatus());
            u.FriendList.remove(i);
          } 
        }
       //le msh mwgoud b2a
        if(!found){
         System.out.println("This user is not in your friend list or email in incorrect");
        }
    }    
    
    public void ShowSuggested()
    {
    }
    
 /*   public ResultSet ShowByRate()
    {
        String url = "jdbc:mysql://localhost:3306/checkin";
        String user = "root";
        String password = "";

        Connection Con = null;
        Statement Stmt = null;
        ResultSet RS = null;
        PreparedStatement ps=null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Con = DriverManager.getConnection(url, user, password);

            DatabaseMetaData DBMetaData = Con.getMetaData();

            String EngineName = DBMetaData.getDatabaseProductName();
            String EngineVer = DBMetaData.getDatabaseProductVersion();

            System.out.println("DB Name " + EngineName);
            System.out.println("DB Version " + EngineVer);
            Stmt = Con.createStatement();
            
            String SQL = "SELECT * FROM place ORDER BY Rate";          
            ps=Con.prepareStatement(SQL);
            RS = ps.executeQuery();
            place_sort.setModel(DbUtils.resultSetToTableModel(RS));
            RS.close();
           
            //////////////////////////////////////////////
            Stmt.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
       
       }
    
    public void SuggestPlace()
    {
    }
    
    public void Notification()
    {
    }
    
    public void Conversation()
    {
    }
}
*/
}