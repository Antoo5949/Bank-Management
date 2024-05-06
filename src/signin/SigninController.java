package signin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scenechanger.switchScene;
import menu.MenuController;

public class SigninController {

    public SigninModel signmodel = new SigninModel();
    switchScene a = new switchScene();

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private   JFXTextField username_button;

    @FXML
    private   JFXPasswordField password_button;
   
    @FXML
    private   JFXButton sign_in;
    @FXML
    private JFXButton signup;
    @FXML
    private Button exit;
    @FXML
    private JFXButton fpassword;
    
  

   
    
     

   
    
    
   // public String fds= getUsername_button().getText();

    public  JFXTextField getUsername_button() {
        return username_button;
    }
    
   
    
    ///String abc= username_button.getText();
    @FXML
    void Signin(ActionEvent event) throws IOException, SQLException {
       
       
        System.out.println(getUsername_button().getText());
        /// System.out.println("Signed in");
        if (signmodel.isSignup(username_button.getText(), password_button.getText())) {
            System.out.println("Menu Page");
            ///Parent asd = FXMLLoader.load(getClass().getResource("/menu/menu.fxml"));
           /// Scene scene2 = new Scene(asd);
           /// Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
           /// window.setScene(scene2);
           /// window.show();
           MenuController.getit(username_button.getText());
           Parent root = FXMLLoader.load(getClass().getResource("/signin/afg.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage= new Stage();
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.resizableProperty().setValue(false);
        stage.show();
        anchorpane.getScene().getWindow().hide();
           ///a.chngScene0(event,"signin","afg");
        } else {
            System.out.println("Invalid password or username");
            signmodel.invalidLoggin();
        }
    }
    /// String username= username_button.getText();

    @FXML
    public   void disable_signinbtn() {
        String   username = username_button.getText();
        String password = password_button.getText();
        boolean disablebtn = (username.isEmpty() || username.trim().isEmpty()) || (password.isEmpty() || password.trim().isEmpty());
        sign_in.setDisable(disablebtn);
    }
    
    @FXML
    void signup(ActionEvent event) throws IOException {
        Parent asd = FXMLLoader.load(getClass().getResource("/signup/Signup.fxml"));
        Scene scene2 = new Scene(asd);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }

    public void initialize() {
        sign_in.setDisable(true);
        if (signmodel.isDbConnected()) {
            System.out.println("Databse connected");
        } else {
            System.out.println("Database is not connected");
        }

    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void fpassword(ActionEvent event) throws IOException {
        a.chngScene(event, "signin", "fpassword");
    }
    

}
