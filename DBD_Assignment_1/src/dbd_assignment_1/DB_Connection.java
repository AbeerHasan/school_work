/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbd_assignment_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class DB_Connection {
    public static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/HealthKitchen";
    private static final String user = "root";
    private static final String pass = "1234";

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement stat = connection.prepareStatement(sql);
            System.out.println("Failed to create the database connection."); 
            
            return stat;
            
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return null;
    }    
}
