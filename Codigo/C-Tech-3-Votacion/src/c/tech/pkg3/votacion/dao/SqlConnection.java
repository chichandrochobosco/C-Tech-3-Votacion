/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c.tech.pkg3.votacion.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlConnection {
   private static Connection con = null;


    static
    {
        String url = "jdbc:mysql://localhost:3306/db_votacion?serverTimezone=UTC";
        String user = "root";
        String pass = "12345";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }          
    }
   public static Connection getConnection()
    {
        return con;
    }    
}
