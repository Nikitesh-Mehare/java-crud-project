
package crudproject;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionProvider {
    
    
    
    static Connection getConnection()
    {
        
        Connection con = null;
        
        try
        {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        String url = "jdbc:mysql://localhost:3306/jdbcdb";
        String username = "root";
        String password = "root";
        
        con = DriverManager.getConnection(url, username, password);
        
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return con;
    }
}
