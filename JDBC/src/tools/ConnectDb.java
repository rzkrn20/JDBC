/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rzkrn20
 */
public class ConnectDb {
    private Connection con;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_hr?zeroDateTimeBehavior=convertToNull", "root", "");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error + "+ e.getMessage());
        }
        return con;
    }
}
