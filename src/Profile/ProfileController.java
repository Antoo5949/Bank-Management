/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import signin.SigninController;
import signin.SigninModel;
import signup.Signupmodel;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField emailid;

    @FXML
    private JFXTextField contactno;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField nidnumber;

    @FXML
    private JFXTextField userid;

    @FXML
    private JFXButton edit;
    @FXML
    private JFXButton update;
    @FXML
    private JFXTextField accountno;

    public ProfileController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setthings(String asd) throws SQLException {
        userid.setText(asd);
        ProfileModel.getit(asd);
        ///ProfileModel.getaccno(Integer.parseInt(accountno.getText()));
        ProfileModel.setprofile(asd);
        
        emailid.setText(ProfileModel.emailid);
        contactno.setText(ProfileModel.contactno);
        address.setText(ProfileModel.address);
        nidnumber.setText(ProfileModel.nidnumber);
        accountno.setText(Integer.toString(ProfileModel.accountno));
        userid.setEditable(false);
        emailid.setEditable(false);
        contactno.setEditable(false);
        address.setEditable(false);
        nidnumber.setEditable(false);
        accountno.setEditable(false);
    }

    @FXML
    void editAction(ActionEvent event) {
        userid.setEditable(true);
        emailid.setEditable(true);
        contactno.setEditable(true);
        address.setEditable(true);
        nidnumber.setEditable(true);
        update.setVisible(true);
        update.setDisable(false);
        edit.setDisable(true);

    }

    @FXML
    void updateAction(ActionEvent event) throws SQLException {
        if (emailid.getText().isEmpty() & contactno.getText().isEmpty() & address.getText().isEmpty() & nidnumber.getText().isEmpty() & userid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Signup!!!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up the form");
            alert.showAndWait();

        } else if (Signupmodel.validateEmail(emailid.getText()) & Signupmodel.Contactno(contactno.getText()) & !Signupmodel.ifUseridexists(userid.getText())) {
            System.out.println(accountno.getText());
            ProfileModel.updateprofile(accountno.getText(),userid.getText(), emailid.getText(), contactno.getText(), address.getText(), nidnumber.getText());
            update.setVisible(false);
            update.setDisable(true);
            edit.setDisable(false);

        }

    }
}
