package swe.project;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

public class Registration {

    String FirstName;
    String LastName;
    String UserName;
    String Password;
    String ConfirmPassword;
    String Email;
    String Country;
    String MobileNum;
    String Gender;
    String DateOfBirth;
    String GUIName;
    String status;
public static int count=2;
    public Registration() {
        count+=1;
    }

    public Registration(String status,String FirstName, String LastName, String Password, String Email, String MobileNum, String Gender, String Country) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Password = Password;
        this.Email = Email;
        this.MobileNum = MobileNum;
        this.Gender = Gender;
        this.status=status;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setMobileNum(String MobileNum) {
        this.MobileNum = MobileNum;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getMobileNum() {
        return MobileNum;
    }

    public String getGender() {
        return Gender;
    }

    public String getCountry() {
        return Country;
    }

    public void CheckInfo() {
        System.out.println("" + FirstName + " " + LastName);
        if (FirstName.equals("") || LastName.equals("") || Password.equals("")
                || Email.equals("")) {
            System.out.println("Can you make sure you fill all the fields ? ");
        } else if (Password.length() < 4) {
            System.out.println("That password is not long enough! Please try again!");
        }

    }

    public void add(String FirstName, String LastName, String Password, String Email, String MobileNum, String Gender, String Country,String s) {//        int n=Integer.parseInt(MobileNum);
        String url = "jdbc:mysql://localhost:3306/checkin";
        String user = "root";
        String password = "";

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
            int code1 = (int) (Math.random()*99999);
            JOptionPane.showMessageDialog(null,"your password retrive code is " +code1);
            Stmt = Con.createStatement();
            
           String sql="INSERT INTO user(Fname,Lname,Email,Country,Gender,Password,Phone,Code,ID,Status) VALUES ('"+ FirstName +"','"+LastName
                    + "','" + Email + "','" + Country + "','" + Gender + "','" + Password +"','" +MobileNum + "','" + code1 +"','"+count+"','"+s+"')";
            Stmt.executeUpdate(sql);
           count+=1;
            /////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        
    }

    public static void main(String[] args) {

    }

}
