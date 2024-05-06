/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databse;

import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author Antoo5949
 */
public class sqliteconnection {
    
    public static Connection Connector() {
        try{
        
                Class.forName("org.sqlite.JDBC");
                Connection connection= DriverManager.getConnection("jdbc:sqlite:signup.sqlite");
                return connection;    
        }
        
        catch(Exception e){
        
            return null;
            
        }
        
        
    
    }
    
     public static Connection rConnector() {
        try{
        
                Class.forName("org.sqlite.JDBC");
                Connection connection= DriverManager.getConnection("jdbc:sqlite:recharge.sqlite");
                return connection;    
        }
        
        catch(Exception e){
        
            return null;
            
        }
    


}
     
     
}
    

