/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenechanger;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Profile.ProfileController;
import java.sql.SQLException;
import javafx.stage.StageStyle;
import signin.SigninController;




/**
 *
 * @author Antoo5949
 */
public class switchScene {
   ///  SigninController d= new SigninController();
    public switchScene(){
    
    
    
    }
    
   
   
    public   void chngScene(ActionEvent event,String packagename,String fxmls) throws IOException{
        
        Parent root=FXMLLoader.load(getClass().getResource("/"+packagename+"/"+fxmls+".fxml"));
         Scene scene1= new Scene(root);
        Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();  
               
     }
    
    public   void chngScene0(ActionEvent event,String packagename,String fxmls) throws IOException{
        
        Parent root=FXMLLoader.load(getClass().getResource("/"+packagename+"/"+fxmls+".fxml"));
         Scene scene1= new Scene(root);
        Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
       /// window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene1);
        window.show();  
               
     }
    public    void chngScene1(ActionEvent event) throws IOException, SQLException{
        try {
           
         ///Parent root=FXMLLoader.load(getClass().getResource("/"+packagename+"/"+fxmls+".fxml"));
         ///Scene scene1= new Scene(root);
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/Profile/Profile.fxml"));
         Parent asf= loader.load();
        ProfileController profilecontroller= (ProfileController)loader.getController();
       
        ///d.setAsh();
        Scene scene1= new Scene(asf);
        //System.out.println(SigninController.getUsername_button().getText());
         profilecontroller.setthings("asd");
        Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
         
        
       
        }
        catch(NullPointerException e){
          ///  System.out.println(e.getMessage());
        
        }
               
     }
}
