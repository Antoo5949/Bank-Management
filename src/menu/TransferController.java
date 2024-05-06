/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import scenechanger.switchScene;
import databse.sqliteconnection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Antoo5949
 */
public class TransferController implements Initializable {
     @FXML
    private TableView<Transferhistory> trtable;
    @FXML
    private TableColumn<Transferhistory, String> trdate;
    @FXML
    private TableColumn<Transferhistory, String> trname;
    @FXML
    private TableColumn<Transferhistory, Integer> traccno;
    @FXML
    private TableColumn<Transferhistory, Integer> tramount;
    @FXML
    private TableColumn<Transferhistory, Integer> trbalance;
    @FXML
    private JFXButton trback;
    public switchScene chngscene = new switchScene();
    @FXML
    private AnchorPane trpane;
    
   public static Connection conn=null;
   public static PreparedStatement preparestaement=null;
   public static ResultSet rs=null;
      ObservableList<Transferhistory> oblistq = FXCollections.observableArrayList(); 
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
         try {
            conn= sqliteconnection.Connector();
            String query="SELECT * from transfer where Sender=? ";
            preparestaement=conn.prepareStatement(query);
            preparestaement.setString(1, MenuController.afm);
            rs=preparestaement.executeQuery();
            while(rs.next()){
             oblistq.add(new Transferhistory(rs.getString("Date"), rs.getString("Name"), rs.getInt("Accno"), rs.getInt("Amount"), rs.getInt("PreviousBalance")));  
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
        
        
        trdate.setCellValueFactory(new PropertyValueFactory<Transferhistory,String>("dates") );
        trname.setCellValueFactory(new PropertyValueFactory<Transferhistory,String>("Names") );
        traccno.setCellValueFactory(new PropertyValueFactory<Transferhistory,Integer>("Accnos") );
        tramount.setCellValueFactory(new PropertyValueFactory<Transferhistory,Integer>("Amounts") );
        trbalance.setCellValueFactory(new PropertyValueFactory<Transferhistory,Integer>("PreviousBalances") );
        
        trtable.setItems(oblistq);
        
    }    

    @FXML
    private void trback(ActionEvent event) throws IOException {
        chngscene.chngScene(event, "menu", "Remon");
        
    }
    
}
