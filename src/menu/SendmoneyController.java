/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import databse.sqliteconnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import static menu.MenuController.balance2;
import scenechanger.switchScene;
import signup.Signupmodel;
import static signup.Signupmodel.userexist;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class SendmoneyController implements Initializable {

    /* transfer*/
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private JFXTextField accno;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField amount;
    @FXML
    private JFXButton transfer;
    @FXML
    private JFXButton search;
    
    @FXML
    private JFXPasswordField tpassword;
   
    @FXML
    private Label tcb;
  
     /* Transfer*/
    /* Database */
    public static PreparedStatement preparestaement = null;
    public static Connection conn = null;
    public static ResultSet rs = null;
    /*Database*/

 /*Scene Changer*/
    switchScene chngscene = new switchScene();
    /*Scene Changer*/

 /* Mobilerecharge.fxml */
    @FXML
    private Label label;
    @FXML
    private AnchorPane ranchorpane;
    @FXML
    private JFXTextField rmobileNumber;
    @FXML
    private JFXTextField ramount;
    @FXML
    private JFXButton recharge;
     @FXML
    private JFXPasswordField rpassword;
    @FXML
    private Label rlabel;
  
    /* profile.fxml*/
    @FXML
    private Label plname;
    @FXML
    private Label pldob;
    @FXML
    private Label placcno;
    @FXML
    private Label pladdress;
    @FXML
    private TextField pname;
    @FXML
    private TextField pdob;
    @FXML
    private TextField paccountno;
    @FXML
    private TextField pmobileno;
    @FXML
    private TextField paddress;
    @FXML
    private TextField peamilid;
    @FXML
    private Label plemailid;
    @FXML
    private Button pedit;
    @FXML
    private Button psave;
    @FXML
    private Button pcancel;
    @FXML
    private TextField fulname;
    /* cashout*/
    @FXML
    private JFXTextField caccno;
    @FXML
    private JFXTextField cagent;
    @FXML
    private JFXTextField ccontact;
    @FXML
    private JFXButton csearch;
    @FXML
    private JFXButton cashout;
    @FXML
    private JFXTextField camount;
    @FXML
    private JFXPasswordField cpassword;
    @FXML
    private Label coulabel;
  
  

    /*Pin*/
    private TextField pmbalance;
    @FXML
    private TextField pinbox;
    /*Pin*/
    @FXML
    private TextField cpinbox;
    @FXML
    private Button piconfirmbtn;
    @FXML
    private Tab history;
    @FXML
    private JFXButton hrecharge;
    @FXML
    private JFXButton htransfer;
    public Image image;
    @FXML
    private ImageView imgvw;

    @FXML
    private Label plname1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab4;
    @FXML
    private JFXButton htcout;

   

    /* Payment*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //
        /* Transfer*/
        name.setEditable(false);
        transfer.setVisible(false);
        name.setVisible(false);
        amount.setVisible(false);
        label.setVisible(false);
        try {
            MenuController.getbalance(MenuController.afm);
        } catch (SQLException ex) {
            Logger.getLogger(SendmoneyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tcb.setText(" Current Balance "+MenuController.balance+" Taka");
        tpassword.setVisible(false);
        /*Recharge */
        recharge.setDisable(true);
        rlabel.setText("Current Balance "+MenuController.balance+" Taka.");
        //rpassword.setVisible(false);
        /*Profile*/
        try {
            setthings(MenuController.afm);
        } catch (SQLException ex) {
            Logger.getLogger(SendmoneyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Profile*/
        pname.setText(MenuController.afm);
        pname.setEditable(false);
        peamilid.setEditable(false);
        pmobileno.setEditable(false);
        paddress.setEditable(false);
        paccountno.setEditable(false);
        pdob.setEditable(false);
        psave.setVisible(false);
        pcancel.setVisible(false);
        /*Cashout*/

        cagent.setVisible(false);
        ccontact.setVisible(false);
        cashout.setVisible(false);
        camount.setVisible(false);
        cpassword.setVisible(false);
        coulabel.setText("Current Balance "+MenuController.balance);

        /*Pin*/
        piconfirmbtn.setVisible(false);

    }

    @FXML
    private void search(ActionEvent event) throws SQLException {
        conn = sqliteconnection.Connector();

        String query = "select * from customers where AccountNumber=?";
        try {
            preparestaement = conn.prepareStatement(query);
            preparestaement.setString(1, accno.getText());

            rs = preparestaement.executeQuery();
            if (rs.next()) {
                //balance= rs.getInt("Balance");
                name.setText(rs.getString("Username"));
                name.setEditable(false);

                transfer.setVisible(true);
                transfer.setDisable(true);
                name.setVisible(true);
                amount.setText("");
                amount.setVisible(true);
                label.setVisible(true);
                tpassword.setText("");
                tpassword.setVisible(true);

            } else {
                name.setVisible(false);
                amount.setVisible(false);
                transfer.setVisible(false);
                label.setText("");
                label.setVisible(false);
                tpassword.setText("");
                tpassword.setVisible(false);
                 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please Insert a valid AccountNumber");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            preparestaement.close();
            rs.close();
            conn.close();

        }
    }

    @FXML
    private void transfer(ActionEvent event) throws SQLException, IOException {
        if (  ifpinexist(tpassword.getText())) {
           if(Integer.parseInt(amount.getText()) >= 500){
            MenuController.getbalance(MenuController.afm);
            int prevbalnce = MenuController.balance;
            int tbalance = MenuController.balance - Integer.parseInt(amount.getText());
            if (tbalance < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Insufficeint balance."
                        + " Your  balance is " + MenuController.balance);
                alert.showAndWait();

            } else {
                MenuController.balance = tbalance;
                MenuController.getbalance(Integer.parseInt(accno.getText()));
                MenuController.balance2 = MenuController.balance2 + Integer.parseInt(amount.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText(amount.getText() + " will be transfered to " + name.getText() + ".\n" + "Your new balance will be " + MenuController.balance + " Taka.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    tcb.setText("Current Balance "+MenuController.balance+" Taka");
                    rlabel.setText("Current Balance "+MenuController.balance+" Taka");
                     coulabel.setText("Current Balance "+MenuController.balance);
                    try {
                        conn = sqliteconnection.Connector();
                        String query = "UPDATE   customers set Balance=?  Where Username=? ";
                        preparestaement = conn.prepareStatement(query);
                        preparestaement.setString(1, Integer.toString(tbalance));
                        preparestaement.setString(2, MenuController.afm);

                        preparestaement.executeUpdate();

                        String query2 = "UPDATE   customers set Balance=?  Where AccountNumber =? ";
                        preparestaement = conn.prepareStatement(query2);
                        preparestaement.setInt(1, balance2);
                        preparestaement.setInt(2, Integer.parseInt(accno.getText()));
                        preparestaement.executeUpdate();

                        String query1 = "INSERT INTO transfer(Date,Name,Accno,Amount,PreviousBalance,Sender) VALUES(?,?,?,?,?,?)";
                        preparestaement = conn.prepareStatement(query1);
                        preparestaement.setString(1, String.valueOf(LocalDate.now()));
                        preparestaement.setString(2, name.getText());
                        preparestaement.setString(3, accno.getText());
                        preparestaement.setString(4, amount.getText());
                        preparestaement.setInt(5, prevbalnce);
                        preparestaement.setString(6, MenuController.afm);
                        preparestaement.executeUpdate();

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());

                    } finally {

                        preparestaement.close();
                        conn.close();

                    }

                    accno.setText("");
                    name.setText("");
                    name.setVisible(false);
                    amount.setText("");
                    amount.setVisible(false);
                    label.setVisible(false);
                    transfer.setVisible(false);
                    
                    
                    tpassword.setText("");
                    tpassword.setVisible(false);

                }
                // anchorpane.getScene().getWindow().hide();
                //  chngscene.chngScene(event, "menu", "menu");

            }
        } else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("You can transfer Minimum of 500 Taka");
            alert.showAndWait();

           
           } }
    }

    @FXML
    private void disabletranfer(KeyEvent event) {
        boolean disablebtn = (amount.getText().isEmpty() || amount.getText().trim().isEmpty()||tpassword.getText().isEmpty() || tpassword.getText().trim().isEmpty()  );
        transfer.setDisable(disablebtn);
    }

    private boolean checkramount() {
        if (Integer.parseInt(ramount.getText()) < 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Amount of Money");
            alert.showAndWait();
            return false;

        }
        return true;
    }

    /* Mobilerecharge.fxml */
    @FXML
    private void recharge(ActionEvent event) throws SQLException, IOException {
        if (ifpinexist(rpassword.getText())) {
           
            if(Signupmodel.Contactno(rmobileNumber.getText()) & checkramount()){
            MenuController.getbalance(MenuController.afm);
            int prevbalnce = MenuController.balance;
            int rbalance = MenuController.balance - Integer.parseInt(ramount.getText());
            if (rbalance < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Insufficeint balance"
                        + " Your  balance is " + MenuController.balance);
                alert.showAndWait();

            } else {
                MenuController.balance = rbalance;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText(ramount.getText() + " will be recharged "
                        + " Your new balance is " + MenuController.balance);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                     rlabel.setText("Current Balance "+MenuController.balance+" Taka.");
                     tcb.setText("Current Balance "+MenuController.balance+" Taka.");
                     coulabel.setText("Current Balance "+MenuController.balance);
                    try {
                        conn = sqliteconnection.Connector();
                        String query = "UPDATE   customers set Balance=?  Where Username=? ";
                        preparestaement = conn.prepareStatement(query);
                        preparestaement.setString(1, Integer.toString(rbalance));
                        preparestaement.setString(2, MenuController.afm);
                        preparestaement.executeUpdate();

                        String query1 = "INSERT INTO Recharge(Date,MobileNumber,Amount,PreviousBalance,Sender) VALUES(?,?,?,?,?)";
                        preparestaement = conn.prepareStatement(query1);
                        preparestaement.setString(1, String.valueOf(LocalDate.now()));
                        preparestaement.setString(2, rmobileNumber.getText());
                        preparestaement.setString(3, ramount.getText());
                        preparestaement.setString(4, Integer.toString(prevbalnce));
                        preparestaement.setString(5, MenuController.afm);
                        preparestaement.executeUpdate();

                    } catch (SQLException e) {
                        System.out.println(e.getMessage());

                    } finally {
                        preparestaement.close();
                        conn.close();

                    }
                    // ranchorpane.getScene().getWindow().hide();
                    //chngscene.chngScene(event, "menu", "menu");
                    System.out.println(LocalDate.now());
                    rmobileNumber.setText("");
                    ramount.setText("");
                    rpassword.setText("");
                   // rpassword.setVisible();

                } }
            }
        }
    }

    /* public boolean checkAmount() {
        if (ramount.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Amount of Money");
            alert.showAndWait();
            return false;

        }
        return true;

    
}
     */
    @FXML
    private void asd(KeyEvent event) {
        boolean disablebtn = (rmobileNumber.getText().isEmpty() || rmobileNumber.getText().trim().isEmpty()) || (ramount.getText().isEmpty() || ramount.getText().trim().isEmpty()|| rpassword.getText().isEmpty() ||rpassword.getText().trim().isEmpty() );
        recharge.setDisable(disablebtn);

    }

    public void setthings(String asd) throws SQLException {

        /// Profile.ProfileModel.getit(asd);
        ///ProfileModel.getaccno(Integer.parseInt(accountno.getText()));
        ProfileModel.setprofile(asd);

        peamilid.setText(ProfileModel.emailid);
        pmobileno.setText(ProfileModel.contactno);
        paddress.setText(ProfileModel.address);
        pdob.setText(ProfileModel.dob);
        fulname.setText(ProfileModel.Fullname);
        paccountno.setText(Integer.toString(ProfileModel.accountno));

        conn = sqliteconnection.Connector();
        String query1 = "SELECT * from customers WHERE Username=?";
        try {
            preparestaement = conn.prepareStatement(query1);
            preparestaement.setString(1, MenuController.afm);
            rs = preparestaement.executeQuery();

            InputStream is = rs.getBinaryStream("Image");
            OutputStream os = new FileOutputStream(new File("photos.png"));
            byte[] content = new byte[1024];
            int size = 0;

            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);

            }
            os.close();
            is.close();
            image = new Image("file:photos.png", 100, 150, true, true);

            imgvw.setImage(image);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SendmoneyController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SendmoneyController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            preparestaement.close();
            conn.close();

        }

    }

    @FXML
    private void pedit(ActionEvent event) {

        peamilid.setEditable(true);
        pmobileno.setEditable(true);
        paddress.setEditable(true);

        psave.setVisible(true);
        psave.setDisable(false);
        pedit.setDisable(true);
        pcancel.setVisible(true);
        pcancel.setDisable(false);

    }

    @FXML
    private void psave(ActionEvent event) throws SQLException {
        if (peamilid.getText().isEmpty() & pmobileno.getText().isEmpty() & paddress.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Signup!!!");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up the form");
            alert.showAndWait();

        } else {

            if (Signupmodel.validateEmail(peamilid.getText()) & Signupmodel.Contactno(pmobileno.getText())) {
                System.out.println(paccountno.getText());
                ProfileModel.updateprofile(peamilid.getText(), pmobileno.getText(), paddress.getText());
                ///psave.setVisible(true);
                psave.setDisable(true);
                pedit.setDisable(false);
                pcancel.setVisible(false);
                // pcancel.setDisable(true);

            }
        }

    }

    @FXML
    private void pcancel(ActionEvent event) throws SQLException {
        setthings(MenuController.afm);
        pedit.setVisible(true);
        pedit.setDisable(false);
        psave.setDisable(true);
        psave.setVisible(true);
        pcancel.setVisible(false);

    }

    @FXML
    private void csearch(ActionEvent event) throws SQLException {
        conn = sqliteconnection.Connector();

        String query = "select * from customers where AccountNumber=?";
        try {
            preparestaement = conn.prepareStatement(query);
            preparestaement.setString(1, caccno.getText());

            rs = preparestaement.executeQuery();
            if (rs.next()) {
                //balance= rs.getInt("Balance");
                cagent.setText(rs.getString("Username"));
                cagent.setVisible(true);
                cagent.setEditable(false);
                ccontact.setVisible(true);
                ccontact.setEditable(false);
                System.out.println(rs.getString("Contactno"));

                ccontact.setText(rs.getString("ContactNo"));
                camount.setVisible(true);
                cashout.setVisible(true);
                cashout.setDisable(true);
                cpassword.setVisible(true);

            } else {
                caccno.setText("");
                cagent.setText("");
                cagent.setVisible(false);
                ccontact.setVisible(false);
                ccontact.setText("");
                cashout.setVisible(false);
                camount.setText("");
                camount.setVisible(false);
                cpassword.setText("");
                cpassword.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Please Insert a valid AccountNumber");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            preparestaement.close();
            rs.close();
            conn.close();

        }
    }

    @FXML
    private void cout(ActionEvent event) throws SQLException {
        
        if (ifpinexist(cpassword.getText())) {
            if(Integer.parseInt(camount.getText()) >= 500){
            MenuController.getbalance(MenuController.afm);
            /// MenuController.balance = MenuController.balance - Integer.parseInt(camount.getText());
            int prevbalnce = MenuController.balance;
            int tbalance = MenuController.balance - Integer.parseInt(camount.getText());
            if (tbalance < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("Insufficeint balance."
                        + " Your  balance is " + MenuController.balance);
                alert.showAndWait();

            } else {
                MenuController.balance = tbalance;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText(camount.getText() + " will be cashed out." + "\nYour new balance will be " + MenuController.balance + "Taka");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                     rlabel.setText("Current Balance "+MenuController.balance+" Taka.");
                     tcb.setText("Current Balance "+MenuController.balance+" Taka.");
                     coulabel.setText("Current Balance "+MenuController.balance);
                   
                    try {
                        conn = sqliteconnection.Connector();
                        String query = "UPDATE   customers set Balance=?  Where Username=? ";
                        preparestaement = conn.prepareStatement(query);
                        preparestaement.setString(1, Integer.toString(MenuController.balance));
                        preparestaement.setString(2, MenuController.afm);

                        preparestaement.executeUpdate();

                        String query1 = "INSERT INTO Cashout(Date,Agentname,Mobileno,Amount,PreviousBalance,Sender) VALUES(?,?,?,?,?,?)";
                        preparestaement = conn.prepareStatement(query1);
                        preparestaement.setString(1, String.valueOf(LocalDate.now()));
                        preparestaement.setString(2, cagent.getText());
                        preparestaement.setString(3, ccontact.getText());
                        preparestaement.setString(4, camount.getText());
                        preparestaement.setInt(5, prevbalnce);
                        preparestaement.setString(6, MenuController.afm);
                        preparestaement.executeUpdate();
                        /*
                    String query2 = "UPDATE   customers set Balance=?  Where AccountNumber =? ";
                    preparestaement = conn.prepareStatement(query2);
                    preparestaement.setInt(1, balance2);
                    preparestaement.setInt(2, Integer.parseInt(accno.getText()));
                    preparestaement.executeUpdate();
                         */
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());

                    } finally {

                        preparestaement.close();
                        conn.close();

                    }

                    caccno.setText("");
                    cagent.setText("");
                    cagent.setVisible(false);
                    ccontact.setVisible(false);
                    ccontact.setText("");
                    cashout.setVisible(false);
                    camount.setText("");
                    camount.setVisible(false);
                    cpassword.setText("");
                    cpassword.setVisible(false);
                    // anchorpane.getScene().getWindow().hide();
                    //  chngscene.chngScene(event, "menu", "menu");
                }
            }
        }else{
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("You can Cashout Minimum of 500 Taka");
            alert.showAndWait();

            
            
            }} 
    }

    @FXML
    private void discout(KeyEvent event) {
        boolean disablebtn = (camount.getText().isEmpty() || camount.getText().trim().isEmpty());
        cashout.setDisable(disablebtn);
    }

    /*Pin*/
    public static boolean ifpinexists(String pin) throws SQLException {
        conn = sqliteconnection.Connector();

        String query1 = "SELECT * from customers WHERE Pin=?";
        try {
            // PreparedStatement userexist = null;
            userexist = conn.prepareStatement(query1);
            userexist.setString(1, pin);
            rs = userexist.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("Pin already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Create a new pin");
                alert.setHeaderText(null);
                alert.setContentText("You can not use your old pin");
                alert.showAndWait();
                return true;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            userexist.close();
            rs.close();

        }
        return false;

    }

    public static boolean ifpinexist(String pin) throws SQLException {
        conn = sqliteconnection.Connector();

        String query1 = "SELECT * from customers WHERE Pin=?";
        try {
            // PreparedStatement userexist = null;
            userexist = conn.prepareStatement(query1);
            userexist.setString(1, pin);
            rs = userexist.executeQuery();
            if (rs.isBeforeFirst()) {

                return true;

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wrong Pin");
                alert.setHeaderText(null);
                alert.setContentText("Please Insert the correct Pin");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            userexist.close();
            rs.close();

        }
        return false;

    }

    public boolean ifpinnoteqal() {
        if (!pinbox.getText().equals(cpinbox.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirm Your Pin");
            alert.setHeaderText(null);
            alert.setContentText("Please Confirm your Pin");
            alert.showAndWait();
            return true;

        } else {
            return false;
        }

    }

    @FXML
    private void pinconfirm(ActionEvent event) throws SQLException {
        if (!ifpinexists(pinbox.getText()) & !ifpinnoteqal()) {
            conn = sqliteconnection.Connector();
            try {

                String query = "UPDATE   customers set Pin=?  Where Username=? ";
                preparestaement = conn.prepareStatement(query);
                preparestaement.setInt(1, Integer.parseInt(cpinbox.getText()));
                preparestaement.setString(2, MenuController.afm);
                preparestaement.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pin changed");
                alert.setHeaderText(null);
                alert.setContentText("Pin Changed succesfully");
                alert.showAndWait();

            } catch (SQLException e) {
                System.out.println(e.getMessage());

            } finally {
                preparestaement.close();
                conn.close();

            }

        }
    }

    @FXML
    public void dispin(KeyEvent event) {
        boolean disablebtn = (pinbox.getText().isEmpty() || pinbox.getText().trim().isEmpty() || cpinbox.getText().isEmpty() || cpinbox.getText().trim().isEmpty());
        piconfirmbtn.setVisible(true);
        piconfirmbtn.setDisable(disablebtn);
    }

    /*Pin*/
    @FXML
    private void hrecharge(ActionEvent event) throws IOException {
        chngscene.chngScene(event, "menu", "MobileRecharge");
    }

    @FXML
    private void htransfer(ActionEvent event) throws IOException {
        chngscene.chngScene(event, "menu", "Transfer");

    }

    @FXML
    private void tab2(Event event) {
        accno.setText("");
        name.setText("");
        name.setVisible(false);
        amount.setText("");
        amount.setVisible(false);
        label.setVisible(false);
        transfer.setVisible(false);
        
        
        tpassword.setText("");
        tpassword.setVisible(false);
        // recharge.setVisible(true);

    }

    @FXML
    private void tab4(Event event) {
        caccno.setText("");
        cagent.setText("");
        cagent.setVisible(false);
        ccontact.setVisible(false);
        ccontact.setText("");
        cashout.setVisible(false);
        camount.setText("");
        camount.setVisible(false);
        cpassword.setText("");
        cpassword.setVisible(false);
    }

    @FXML
    private void htcout(ActionEvent event) throws IOException {
        chngscene.chngScene(event, "menu", "Hcout");
    }

    @FXML
    private void rpassword(ActionEvent event) {
    }

    @FXML
    private void tpassword(ActionEvent event) {
    }

    @FXML
    private void cpassword(ActionEvent event) {
    }

}
