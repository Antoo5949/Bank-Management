/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signin;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import databse.sqliteconnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import menu.MenuController;
import signup.Signupmodel;
import scenechanger.switchScene;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class FpasswordController implements Initializable {

    @FXML
    private TextField fpemail;
    @FXML
    private TextField fpnpass;
    @FXML
    private TextField fpcpass;
    @FXML
    private JFXButton passchange;
    @FXML
    private JFXButton passearch;
    /*database*/
    public static PreparedStatement preparestaement = null;
    public static Connection conn = null;
    public static ResultSet rs = null;

    /*database*/
    switchScene chngscene = new switchScene();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        fpnpass.setVisible(false);
        fpcpass.setVisible(false);
        passchange.setDisable(false);
        passchange.setVisible(false);
    }

    public boolean ifpinnoteqal() {
        if (!fpnpass.getText().equals(fpcpass.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirm Your Password");
            alert.setHeaderText(null);
            alert.setContentText("Please Confirm your Password");
            alert.showAndWait();
            return true;

        } else {
            return false;
        }

    }

    @FXML
    private void passchange(ActionEvent event) throws SQLException, IOException {
        if (Signupmodel.Passwordvalidation(fpnpass.getText()) & !ifpinnoteqal()) {
            conn = sqliteconnection.Connector();
            try {

                String query = "UPDATE   customers set Password=?  Where Emailid=? ";
                preparestaement = conn.prepareStatement(query);
                System.out.println(fpnpass.getText());
                preparestaement.setString(1, fpnpass.getText());
                preparestaement.setString(2, fpemail.getText());
                preparestaement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Password changed");
                alert.setHeaderText(null);
                alert.setContentText("Pass Changed succesfully");
                alert.showAndWait();
                chngscene.chngScene(event, "signin", "Signin");

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            } finally {
                preparestaement.close();
                conn.close();

            }

        }

    }

    @FXML
    private void dischnge(KeyEvent event) {
        boolean disablebtn = (fpnpass.getText().isEmpty() || fpnpass.getText().trim().isEmpty() || fpcpass.getText().trim().isEmpty() || fpcpass.getText().isEmpty());
        passchange.setDisable(disablebtn);
    }

    @FXML
    private void passearch(ActionEvent event) throws SQLException {
        conn = sqliteconnection.Connector();

        String query = "select * from customers where Emailid=?";
        try {
            preparestaement = conn.prepareStatement(query);
            preparestaement.setString(1, fpemail.getText());

            rs = preparestaement.executeQuery();
            if (rs.next()) {
                //balance= rs.getInt("Balance");
                fpnpass.setVisible(true);
                fpcpass.setVisible(true);
                passchange.setDisable(true);
                passchange.setVisible(true);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please Insert a valid Emailid");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            rs.close();
            preparestaement.close();
            conn.close();

        }
    }

}
