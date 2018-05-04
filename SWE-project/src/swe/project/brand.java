/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe.project;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class brand {
    String name ;
    int ID;
    public brand(){
        
    }
    public static int count=1;
     public void addTastes(String fooditem,String environmentasp,String brand_name){
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
            
            String SQL = "SELECT ID FROM brand where brand.Name='"+brand_name+"'";          
            ps=Con.prepareStatement(SQL);
            RS = ps.executeQuery();
            int ID=0;
            if(RS.first()){
                ID=RS.getInt(1);
            
            String sql="INSERT INTO brand_tastes(Brand_ID,ID,fooditem,environmentasp) VALUES ("+ID+","+count+",'"+fooditem+"','"+environmentasp+"')";
            Stmt.executeUpdate(sql);
            }
            count+=1;
            /////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
    }
     public static int count2=1;
    public void addTip(String statement,String brand_name){
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
            
            String SQL = "SELECT ID FROM brand where brand.Name='"+brand_name+"'";          
            ps=Con.prepareStatement(SQL);
            RS = ps.executeQuery();
            int ID=0;
            if(RS.first()){
                ID=RS.getInt(1);
            
            String sql="INSERT INTO brand_tips(Brand_ID,ID,recommend) VALUES ("+ID+","+count2+",'"+statement+"')";
            Stmt.executeUpdate(sql);
            }
            count2+=1;
            /////////////////////////////////////////
            Stmt.close();
            RS.close();
            Con.close();
        } catch (Exception cnfe) {
            System.err.println("Exception: " + cnfe);
        }
    }
}
