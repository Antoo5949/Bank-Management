/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import Profile.*;
import com.jfoenix.controls.JFXTextField;
import databse.sqliteconnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 *
 * @author Antoo5949
 */
public class ProfileModel {

    public static String userid = "";
    public static String emailid;

    public static String contactno;

    public static String address;

    public static String nidnumber;

    public static int accountno;
    public static String Fullname;
    public static String dob;
     public static PreparedStatement preparestaement = null;
    public static Connection conn = null;
    public static ResultSet rs = null;
    public static Blob blob;

    public static String getit(String some) {
        userid = some;
        return userid;

    }

    public static int getaccno(int accno) {
        accountno = accno;
        return accountno;

    }

    public static void setprofile(String userids) throws SQLException {
         conn = sqliteconnection.Connector();
         

       

        String query1 = "SELECT * from customers WHERE Username=?";

        try {

            preparestaement = conn.prepareStatement(query1);
            preparestaement.setString(1, userids);
            rs = preparestaement.executeQuery();
            while (rs.next()) {
                emailid = rs.getString("Emailid");
                contactno = rs.getString("Contactno");
                address = rs.getString("Address");
                nidnumber = rs.getString("Nidnum");
                accountno = rs.getInt("AccountNumber");
                dob=rs.getString("Dob");
                //blob=rs.getBlob("Image");
                Fullname=rs.getString("Name");

            }

        } catch (SQLException e) {

        } finally {
            rs.close();
            preparestaement.close();
            conn.close();

        }

    }

    public static void updateprofile(  String Emailid, String Contactno, String Address) throws SQLException {
         conn = sqliteconnection.Connector();
        

        //ResultSet rs = null;

        String query1 = "Update  customers set  Emailid=?,Contactno=?,Address=? WHERE AccountNumber=?";

        try {

            preparestaement = conn.prepareStatement(query1);
            
            preparestaement.setString(1, Emailid);
            preparestaement.setString(2, Contactno);
            preparestaement.setString(3, Address);
            preparestaement.setString(4, Integer.toString(accountno));
            
            
            int i=preparestaement.executeUpdate();
            System.out.println(i);
            
            if(i==1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText(null);
            alert.setContentText("Update Successful");
            alert.showAndWait();
            }
        } catch (SQLException e) {

        } finally {
            //rs.close();
            preparestaement.close();
            conn.close();

        }

    }
    

}
