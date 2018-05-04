package swe.project;
/////////////////////////////////////////////////////
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//////////////////////////////////////////////////////////////
public class DBcontrol {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement stat;
/////////////////////////////////////////////////////////////////////////    
    public DBcontrol(){}
   
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialnetwork","root","");
            return conn;
            
        }catch(Exception ex){
            System.out.println("Erroe: " + ex);
        }
        return null;
    }
    public void openConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkin","root","");
            st = con.createStatement();
            
            
        }catch(Exception ex){
            System.out.println("Erroe: " + ex);
        }
    }
    
    public ResultSet getResultset(String s) throws SQLException{
        
        rs = st.executeQuery(s);
        return rs;
    }
     public ResultSet execupdate(String s) throws SQLException{
        
        st.executeUpdate(s);
        return rs;
    }
    public void excutePreparedStatement(String s) throws SQLException{
        stat = con.prepareStatement(s);
        stat.execute(s);
    }
    
    public void excuteStat(String s) throws SQLException{
        st.execute(s);
    }
    
    
    public void closeConnection() throws SQLException{
        con.close();
    }
}
