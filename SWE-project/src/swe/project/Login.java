package swe.project;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class Login {

    public  String Password;
    public  String Email;
    // DBControl DB; 
    public static int ID;
     String  Code;
    public Login()
    {
        
    }
    public int getID(){
        return ID;
    } 
    public Login(String Email, String Password) {
        this.Password = Password;
        this.Email = Email;
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
            String SQL = "SELECT * FROM User WHERE Email ='" + Email + "' AND Password = '" + Password + "'";
            RS = Stmt.executeQuery(SQL);
            if(checkInfo(Email,Password)){
            while (RS.next()) {
                 this.ID=RS.getInt("ID");
            }
            }

            
            //////////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }

    }
    public Login(String Email,String pass,String Code){
        this.Email=Email;
        this.Code=Code;
        this.Password=pass;
           this.Password = Password;
        this.Email = Email;
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
            String SQL = "SELECT * FROM User WHERE Email ='" + Email + "' AND Code = '" +Code+ "'";
            RS = Stmt.executeQuery(SQL);
            if(forgetpass(Email,pass,Code)){
            while (RS.next()) {
                 this.ID=RS.getInt(1);
            }
            }
        
            
            //////////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }
    public boolean forgetpass(String mail,String pass,String Code)
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
            String SQL = "UPDATE user SET password ='" + pass + "' where email = '" + mail + "'  AND code = '" + Code + "'";
             Stmt.executeUpdate(SQL);
                

            
            //////////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }

    
    return true;
    }
    public boolean checkInfo(String Email, String Password) {
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
            String SQL = "SELECT * FROM User WHERE Email ='" + Email + "' AND Password = '" + Password + "'";
            RS = Stmt.executeQuery(SQL);
            
            while (RS.next()) {
                Check = true;
                 
            }
           

            
            //////////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }

        return Check;
    }

}
