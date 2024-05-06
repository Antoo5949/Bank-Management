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
import scenechanger.switchScene;
import databse.sqliteconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.cell.PropertyValueFactory;
import static menu.MobileRechargeController.conn;
import static menu.MobileRechargeController.preparestaement;
import static menu.MobileRechargeController.rs;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class HcoutController implements Initializable {

    @FXML
    private TableView<couthistory> trtable;
    @FXML
    private TableColumn<couthistory, String> codate;
    @FXML
    private TableColumn<couthistory, String> coname;
    @FXML
    private TableColumn<couthistory, String> comobile;
    @FXML
    private TableColumn<couthistory, Integer> tramount;
    
   
    @FXML
    private JFXButton cback;
    
    public switchScene chngscene = new switchScene();
    
    public static Connection conn = null;
    public static ResultSet rs = null;
    public static PreparedStatement preparestaement = null;
    
    
    ObservableList<couthistory> oblist = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<couthistory, Integer> ctrbalance;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
          try {
            conn= sqliteconnection.Connector();
            String query="SELECT * from Cashout WHERE Sender=? ";
            preparestaement=conn.prepareStatement(query);
            preparestaement.setString(1, MenuController.afm);
            rs=preparestaement.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt("PreviousBalance"));
                oblist.add(new couthistory (rs.getString("Date"),rs.getString("Agentname"),rs.getString("Mobileno"),rs.getInt("Amount"),rs.getInt("PreviousBalance")));
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
        
        
        codate.setCellValueFactory(new PropertyValueFactory<couthistory,String>("date") );
        coname.setCellValueFactory(new PropertyValueFactory<couthistory,String>("name") );
        comobile.setCellValueFactory(new PropertyValueFactory<couthistory,String>("Mobileno") );
        tramount.setCellValueFactory(new PropertyValueFactory<couthistory,Integer>("amount") );
        ctrbalance.setCellValueFactory(new PropertyValueFactory<couthistory,Integer>("previousbalance") );
        
        trtable.setItems(oblist);
    }    

    @FXML
    private void cback(ActionEvent event) throws IOException {
        chngscene.chngScene(event, "menu", "Remon");
    }
    
}
