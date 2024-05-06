/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signin;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import databse.sqliteconnection ;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import scenechanger.switchScene;

/**`
 *
 * @author Antoo5949
 */
public class SigninModel {
  public  Connection conn;
  public Connection conn2;
    switchScene a= new switchScene();
    
    

    public SigninModel() {
         conn= sqliteconnection.Connector();
         conn2=sqliteconnection.rConnector();
        if(conn==null) {
            System.out.println("Connection is not Succesfull");
            System.exit(1);
        }
        if(conn2==null) {
            System.out.println("Connection is not Succesfull");
            System.exit(1);
        }
    }
    
    public boolean isDbConnected() {
        try{
          return  !conn.isClosed( );
    
        }
        catch(SQLException e){
            printStackTrace();
            return false;
        }
    }
    
    public  boolean isSignup(String user, String password) throws SQLException{
        PreparedStatement preparedstatement=null;
        ResultSet resultset=null;
        String query="select * from customers where Username=? and Password=? ";
        try{
        preparedstatement=conn.prepareStatement(query);
        preparedstatement.setString(1, user);
        preparedstatement.setString(2, password);
        
        
        
        resultset= preparedstatement.executeQuery();
        if(resultset.next()){
            return true;
        }
        else
            return false;
        
        }
        catch(SQLException e){
            return false;
        }
        finally {
            preparedstatement.close();
            resultset.close();
        }
          
    }
    
public void invalidLoggin(){
    Alert alert= new Alert(Alert.AlertType.INFORMATION);
     alert.setTitle("Invalid Email or Password");
     alert.setHeaderText(null);
     alert.setContentText("Please insert correct credentials");
     alert.showAndWait();
}    
    
}
