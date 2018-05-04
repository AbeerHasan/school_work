/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe.project;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static swe.project.Registration.count;

/**
 *
 * @author Zozza
 */
public class Premuim {
  //  private Brand brand;
    private place Place;
    //private DBControl dbcontrol;
    Scanner input = new Scanner(System.in);
public static int count=3;
    public Premuim() {
        count+=1;
    }
    

    /*public Premuim(Brand brand, Place place, DBControl dbcontrol) {
        this.brand = brand;
        this.place = place;
        this.dbcontrol = dbcontrol;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setDbcontrol(DBControl dbcontrol) {
        this.dbcontrol = dbcontrol;
    }
*/
    public void setPlace(place p) {
        this.Place = p;
    }

    /*public Brand getBrand() {
        return brand;
    }

    public DBControl getDbcontrol() {
        return dbcontrol;
    }

    public Place getPlace() {
        return place;
    }
    */
    
    public void AddNewplace(String Name,String Type,String latitude,String longitude)
    {
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
            String sql="INSERT INTO place(ID,Name,Type,NofCheckins,latitude,longitude) VALUES ('"+count+"','"+Name+ "','" +Type+"','"+1+"','"+latitude+"','"+longitude+"')";
            Stmt.executeUpdate(sql);
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
        //add place fldatabase
    }
   /* public void CreateBrand(String name)
    {
        String name;
        int id;
        System.out.println("Enter brand name");
        brand.setname( name=input.next());
        System.out.println("Enter Brand ID");
        brand.setid( id=input.next());
        //add Brand fldatabase 
    }
    
    public void SetplaceInfo()
    {
        String name,type;
        int id;
        System.out.println("Enter Place type");
        place.settype( type=input.next());
        System.out.println("Enter Place name");
        place.setname( name=input.next());
        System.out.println("Enter Place ID");
        place.setid( id=input.next());
        //add place fldatabase
        
    
    }*/
    
}
