/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import Profile.ProfileController;
import com.jfoenix.controls.JFXButton;
import databse.sqliteconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import scenechanger.switchScene;
import signup.Signupmodel;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    switchScene a = new switchScene();
    @FXML
    private JFXButton profile;
    public static String afm = " ";
    @FXML
    private JFXButton sendmny;
    @FXML
    private JFXButton shwblnced;

    public static int balance = 0;
    public static int balance2 = 0;
    public static int checkinput = 0;
    public static int accno = 0;
    public static String recipent;
    public static PreparedStatement preparestaement = null;
    public static Connection conn = null;
    public static ResultSet rs = null;
    public static String mobileno;
    @FXML
    private JFXButton cshout;

    @FXML
    private JFXButton recharge;
     @FXML
    private JFXButton addmny;

      switchScene as = new switchScene();

    public static void getbalance(String userid) throws SQLException {
        conn = sqliteconnection.Connector();
        preparestaement = null;

        String query = "select * from customers where Username=?";
        try {
            preparestaement = conn.prepareStatement(query);
            preparestaement.setString(1, userid);

            rs = preparestaement.executeQuery();
            while (rs.next()) {
                //balance= rs.getInt("Balance");
                balance = rs.getInt("Balance");
                System.out.println(balance);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            preparestaement.close();
            rs.close();
            conn.close();

        }
        ;
    }

    public static void getbalance(int accno) throws SQLException {
        conn = sqliteconnection.Connector();

        String query = "select * from customers where AccountNumber=?";
        try {
            preparestaement = conn.prepareStatement(query);
            preparestaement.setString(1, Integer.toString(accno));

            rs = preparestaement.executeQuery();
            while (rs.next()) {
                //balance= rs.getInt("Balance");
                balance2 = rs.getInt("Balance");
                System.out.println(balance2);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            preparestaement.close();
            rs.close();
            conn.close();

        }
    }

    public static void getrecepent(int accno) throws SQLException {
        conn = sqliteconnection.Connector();

        String query = "select * from customers where AccountNumber=?";
        try {
            preparestaement = conn.prepareStatement(query);
            preparestaement.setString(1, Integer.toString(accno));

            rs = preparestaement.executeQuery();
            while (rs.next()) {
                //balance= rs.getInt("Balance");
                // balance2 = rs.getInt("Balance");
                recipent = rs.getString("Username");
                //System.out.println(balance);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

            preparestaement.close();
            rs.close();
            conn.close();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public static String getit(String some) {

        afm = some;
        return afm;

    }

    @FXML
    void profilebtn(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Profile/Profile.fxml"));
        Parent asf = loader.load();
        ProfileController profilecontroller = (ProfileController) loader.getController();

        ///d.setAsh();
        Scene scene1 = new Scene(asf);
        //System.out.println(SigninController.getUsername_button().getText());
        profilecontroller.setthings(afm);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    public static boolean inputvalidation() {
        if (checkinput < 1000) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("You have to Deposit or Withdraw minimum amount of 1000");
            alert.showAndWait();
            return false;

        }
        return true;

    }

    @FXML
    void shwblnce(ActionEvent event) throws SQLException {
        getbalance(afm);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText("You have " + balance + "Taka");
        alert.showAndWait();
    }

    @FXML
    void sendbtn(ActionEvent event) throws SQLException, IOException {
        a.chngScene(event, "menu", "sendmoney");
       /* try {
            getbalance(afm);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Your Current balance is " + balance);
            dialog.setTitle("Send Money");
            dialog.setContentText("Enter the Account Number: ");

            Optional<String> result = dialog.showAndWait();

            /// PreparedStatement preparedstaement2 = null;
            accno = Integer.parseInt(result.get());
            if (result.isPresent()) {

                TextInputDialog dialog2 = new TextInputDialog("0");
                dialog.setHeaderText("Your Current balance is " + balance);
                dialog.setTitle("Send Money");
                dialog.setContentText("Enter the Amount of Money: ");

                Optional<String> result2 = dialog.showAndWait();

                checkinput = Integer.parseInt(result.get());
                // balance2 = Integer.parseInt(result2.get());

                if (result2.isPresent() && inputvalidation()) {

                    getbalance(accno);
                    getrecepent(accno);
                    balance = balance - Integer.parseInt(result2.get());
                    balance2 = balance2 + Integer.parseInt(result2.get());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText(result2.get() + " will be transfered to Mr." + recipent
                            + " Your new balance is " + balance);
                    alert.showAndWait();
                    conn = sqliteconnection.Connector();
                    String query = "UPDATE   customers set Balance=?  Where Username=? ";
                    preparestaement = conn.prepareStatement(query);
                    preparestaement.setString(1, Integer.toString(balance));
                    preparestaement.setString(2, afm);

                    preparestaement.executeUpdate();

                    String query2 = "UPDATE   customers set Balance=?  Where AccountNumber =? ";
                    preparestaement = conn.prepareStatement(query2);
                    preparestaement.setInt(1, balance2);
                    preparestaement.setInt(2, accno);
                    preparestaement.executeUpdate();

                }

            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Amount of Money");
            alert.showAndWait();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }catch(NoSuchElementException e){
            
        
        }
        finally {
            preparestaement.close();
            conn.close();

        }*/

    }

    @FXML
    void mobileecharge(ActionEvent event) throws SQLException, IOException {
          
        as.chngScene(event,"menu" ,"new" );
       /* try {
            getbalance(afm);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Your Current balance is " + balance);
            dialog.setTitle("Mobile Recharge");
            dialog.setContentText("Enter the Mobile Number: ");

            Optional<String> result = dialog.showAndWait();

            /// PreparedStatement preparedstaement2 = null;
            mobileno = result.get();
            if (result.isPresent() & Signupmodel.Contactno(mobileno)) {

                TextInputDialog dialog2 = new TextInputDialog("0");
                dialog.setHeaderText("Your Current balance is " + balance);
                dialog.setTitle("Send Money");
                dialog.setContentText("Enter the Amount of Money: ");

                Optional<String> result2 = dialog.showAndWait();

                checkinput = Integer.parseInt(result.get());
                // balance2 = Integer.parseInt(result2.get());

                if (result2.isPresent()) {

                    balance = balance - Integer.parseInt(result2.get());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText(result2.get() + " will be recharged to " + result.get()
                            + " Your new balance is " + balance);
                    alert.showAndWait();
                    conn = sqliteconnection.Connector();
                    String query = "UPDATE   customers set Balance=?  Where Username=? ";
                    preparestaement = conn.prepareStatement(query);
                    preparestaement.setString(1, Integer.toString(balance));
                    preparestaement.setString(2, afm);

                    preparestaement.executeUpdate();

                }

            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Amount of Money");
            alert.showAndWait();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        catch(NoSuchElementException e){
            
        
        }finally {
            preparestaement.close();
            conn.close();

        
*/
    }

    @FXML
    void cshoutbtn(ActionEvent event) throws SQLException {
        try {
            getbalance(afm);

            TextInputDialog dialog = new TextInputDialog();
            dialog.setHeaderText("Your Current balance is " + balance);
            dialog.setTitle("Mobile Recharge");
            dialog.setContentText("Enter the Agents AccountNumber: ");

            Optional<String> result = dialog.showAndWait();
            accno = Integer.parseInt(result.get());

            /// PreparedStatement preparedstaement2 = null;
            if (result.isPresent() && Signupmodel.ifaccnodexists(accno)) {

                TextInputDialog dialog2 = new TextInputDialog("0");
                dialog.setHeaderText("Your Current balance is " + balance);
                dialog.setTitle("Cashout");
                dialog.setContentText("Enter the Amount of Money: ");

                Optional<String> result2 = dialog.showAndWait();

                checkinput = Integer.parseInt(result2.get());
                // balance2 = Integer.parseInt(result2.get());

                if (result2.isPresent() && inputvalidation()) {

                    balance = balance - Integer.parseInt(result2.get());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("");
                    alert.setHeaderText(null);
                    alert.setContentText(result2.get() + " tk  will be withdrawed  "
                            + " Your new balance is " + balance);
                    alert.showAndWait();
                    conn = sqliteconnection.Connector();
                    String query = "UPDATE   customers set Balance=?  Where Username=? ";
                    preparestaement = conn.prepareStatement(query);
                    preparestaement.setString(1, Integer.toString(balance));
                    preparestaement.setString(2, afm);

                    preparestaement.executeUpdate();

                }

            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter the Amount of Money");
            alert.showAndWait();

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } catch (NoSuchElementException e) {

        } finally {
            preparestaement.close();
            conn.close();

        }
    }
    
       @FXML
    void listbank(ActionEvent event) throws IOException {
        as.chngScene(event, "listview", "banknames");
    }


}
