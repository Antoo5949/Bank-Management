/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signin;

import com.jfoenix.controls.JFXProgressBar;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scenechanger.switchScene;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class AfgController implements Initializable {

    @FXML
    private JFXProgressBar progressbar;
    @FXML
    private Label Loading;
    private Label percent;
    BigDecimal progress = new BigDecimal(String.format("%.2f", 0.0));
    switchScene a = new switchScene();
    private Stage stage;
      @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label welcome;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcome.setText("Welcome "+menu.MenuController.afm);
        new Splashscreen().start(); 
    }

    public void increaseProgress() throws IOException {

        while (progress.doubleValue() != 1) {
            progress = new BigDecimal(String.format("%.2f", progress.doubleValue() + 0.1));
            //System.out.println(progress.doubleValue());
            progressbar.setProgress(progress.doubleValue());
            percent.setText(Integer.toString((int) Math.round(progress.doubleValue() * 100)) + "%");
        }
        if (progress.doubleValue() == 1) {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/menu/Remon.fxml"));
            Parent root;

            try {
                root = (Parent) loader.load();

                this.stage.setScene(new Scene(root));

            } catch (IOException e) {
                printStackTrace();

            }

        }

    }

    class Splashscreen extends Thread {

        @Override
        public void run() {

            try {
                Thread.sleep(2000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root=null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/menu/Remon.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(AfgController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(root);        
                Scene scene = new Scene(root);
                Stage stage= new Stage();

                stage.setScene(scene);
               /// stage.initStyle(StageStyle.UNDECORATED);
                stage.resizableProperty().setValue(false);
                stage.show();
                
                anchorpane.getScene().getWindow().hide();
                
                    }
                });
               
                
            } catch (InterruptedException ex) {
                Logger.getLogger(AfgController.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
    }
}
