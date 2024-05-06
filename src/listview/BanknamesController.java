/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listview;

import com.jfoenix.controls.JFXListView;
import databse.sqliteconnection;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import menu.MenuController;
import static menu.MenuController.accno;
import static menu.MenuController.afm;
import static menu.MenuController.balance;
import static menu.MenuController.balance2;
import static menu.MenuController.checkinput;
import static menu.MenuController.conn;
import static menu.MenuController.getbalance;
import static menu.MenuController.getrecepent;
import static menu.MenuController.inputvalidation;
import static menu.MenuController.preparestaement;
import static menu.MenuController.recipent;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class BanknamesController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXListView<String> menu;

    public static PreparedStatement preparestaement = null;
    public static Connection conn = null;
    public static ResultSet rs = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> a = new ArrayList<>();
        a.add("AB bank");
        a.add("AB bank");

        menu.setItems(FXCollections.observableArrayList(a));
        
       menu.setOnMouseClicked(event ->{
            try {
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
            
        
        } catch(NullPointerException e){
        
        
        
        }
        finally {
                try {
                    preparestaement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BanknamesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BanknamesController.class.getName()).log(Level.SEVERE, null, ex);
                }

        }

       
       });
    }

   
    
    }



