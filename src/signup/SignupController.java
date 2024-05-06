/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import scenechanger.switchScene;


/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class SignupController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ///int v=10;
    switchScene a= new switchScene();

    
    @FXML
    private JFXTextField emailid;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField contactno;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField nidnumber;

    @FXML
    private JFXButton signup;
    @FXML
    private JFXTextField userid;
    @FXML
    private JFXDatePicker dob;

    
   ///String Address= address.getText();
    ///String Contactno= contactno.getText();
    ///String Emailid= emailid.getText();
    ///String Password= password.getText();
    //String Nidno= nidnumber.getText();
    ///String Userid= userid.getText();
    @FXML
    private JFXTextField pin;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXButton browse;
    @FXML
    private ImageView img;
    @FXML
    private AnchorPane signupanchorpane;
    
    private Image image;
    public static File file;
    
   
    
    
   
    
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
    } 
    
////    private boolean validateEmail(String Emailid){
////        Pattern p= Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
////        Matcher m= p.matcher(Emailid);
////        if(m.find()&& m.group().equals(Emailid))
////            return true;
////        else{
////              
////                Alert alert= new Alert(Alert.AlertType.WARNING);
////                alert.setTitle("Validate Email");
////                alert.setHeaderText(null);
////                alert.setContentText("Please Enter a valid Email");
////                alert.showAndWait();
////                
////                
////                return false;
////        
////        
////        
////        }
////    
////    
//    
//    }
    
    @FXML
    void back_to_login_page(ActionEvent event) throws IOException { 
           /// Parent root=FXMLLoader.load(getClass().getResource("/signin/Signin.fxml"));
            ///Scene scene1= new Scene(root);
            ///Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
            //window.setScene(scene1);
            ///window.show();
            System.out.println("login_page");
            
            a.chngScene(event, "signin", "Signin");
            
         
        }
    
    public boolean iffileexists(){
        if(file==null){
        
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please Insert a pic");
            alert.showAndWait();
            
            return false;
        
        
        
        }
        
        return true;
    
    
    
    
    
    }
    
    
    @FXML
    void signup(ActionEvent event) throws IOException, SQLException {
        
      //  Period diff= Period.between(dob.getValue(),LocalDate.now());
      //  System.out.println(diff.getYears()+"years");
        if(fname.getText().isEmpty()&emailid.getText().isEmpty() & password.getText().isEmpty() & contactno.getText().isEmpty()& address.getText().isEmpty() & nidnumber.getText().isEmpty() & userid.getText().isEmpty() & dob.getValue()==null ){
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Signup!!!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up the form");
            alert.showAndWait();

        
        }

        else if(Signupmodel.nameempty(fname.getText())&Signupmodel.validateEmail(emailid.getText()) & Signupmodel.Contactno(contactno.getText()) & Signupmodel.Passwordvalidation(password.getText())& Signupmodel.validateDob(dob.getValue()) &!Signupmodel.ifUseridexists(userid.getText())&Signupmodel.pinempty(pin.getText())&iffileexists()){
            Signupmodel.insert(fname.getText(),emailid.getText(), userid.getText(), password.getText(), contactno.getText() , address.getText(), nidnumber.getText(), dob.getValue(),Integer.parseInt(pin.getText()),file);
            
           // Parent root=FXMLLoader.load(getClass().getResource("/signin/Signin.fxml"));
          //  Scene scene1= new Scene(root);
          //  Stage window= (Stage) ((Node)event.getSource()).getScene().getWindow();
           // window.setScene(scene1);
            //window.show();
            a.chngScene(event, "signin", "Signin");
    }  
        
    
}

    @FXML
    private void browse(ActionEvent event) {
            
            FileChooser fileChooser= new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png","*.jpg","*.gif")
            
            );
            fileChooser.setTitle("Choose Image");
            Stage stage=(Stage) signupanchorpane.getScene().getWindow();
            
             file= fileChooser.showOpenDialog(stage);
            
            if(file!=null){
                System.out.println("not null");
               image=new Image(file.toURI().toString());
               
               
               img.setImage(image);
            
            
            }
            
    }
}
