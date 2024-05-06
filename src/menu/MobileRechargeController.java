/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import scenechanger.switchScene;
import databse.sqliteconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class MobileRechargeController implements Initializable {

    @FXML
    private TableView<RechargeHistory> mtableview;
    @FXML
    private TableColumn<RechargeHistory, String> tbdate;
    @FXML
    private TableColumn<RechargeHistory, String> tbmobile;
    @FXML
    private TableColumn<RechargeHistory, Integer> tbamount;
    @FXML
    private TableColumn<RechargeHistory, Integer> tbprev;
    @FXML
    private JFXButton tbback;
    public switchScene chngscene = new switchScene();
    public static Connection conn = null;
    public static ResultSet rs = null;
     public static PreparedStatement preparestaement = null;
    
    ObservableList<RechargeHistory> oblist = FXCollections.observableArrayList(); 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        try {
            conn= sqliteconnection.Connector();
            String query="SELECT * from Recharge WHERE Sender=? ";
            preparestaement=conn.prepareStatement(query);
            preparestaement.setString(1, MenuController.afm);
            rs=preparestaement.executeQuery();
            while(rs.next()){
                oblist.add(new RechargeHistory(rs.getString("Date"),rs.getString("MobileNumber"),rs.getInt("Amount"),rs.getInt("PreviousBalance")));
            }
            
        
        
        
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        finally{
            try {
                rs.close();
                preparestaement.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MobileRechargeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        tbdate.setCellValueFactory(new PropertyValueFactory<RechargeHistory,String>("date") );
        tbmobile.setCellValueFactory(new PropertyValueFactory<RechargeHistory,String>("Mobileno") );
        tbamount.setCellValueFactory(new PropertyValueFactory<RechargeHistory,Integer>("amount") );
        tbprev.setCellValueFactory(new PropertyValueFactory<RechargeHistory,Integer>("previousbalance") );
        
        mtableview.setItems(oblist);
    }    

    @FXML
    private void tbback(ActionEvent event) throws IOException {
            chngscene.chngScene(event, "menu", "Remon");
    }
    
}
