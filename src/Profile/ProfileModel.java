/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile;

import com.jfoenix.controls.JFXTextField;
import databse.sqliteconnection;
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

    public static String getit(String some) {
        userid = some;
        return userid;

    }

    public static int getaccno(int accno) {
        accountno = accno;
        return accountno;

    }

    public static void setprofile(String userids) throws SQLException {
        Connection conn = sqliteconnection.Connector();
        PreparedStatement preparedstaement = null;

        ResultSet rs = null;

        String query1 = "SELECT * from customers WHERE Username=?";

        try {

            preparedstaement = conn.prepareStatement(query1);
            preparedstaement.setString(1, userids);
            rs = preparedstaement.executeQuery();
            while (rs.next()) {
                emailid = rs.getString("Emailid");
                contactno = rs.getString("Contactno");
                address = rs.getString("Address");
                nidnumber = rs.getString("Nidnum");
                accountno = rs.getInt("AccountNumber");

            }

        } catch (SQLException e) {

        } finally {
            rs.close();
            preparedstaement.close();

        }

    }

    public static void updateprofile(String accnos, String Userids, String Emailid, String Contactno, String Address, String Nidno) throws SQLException {
        Connection conn = sqliteconnection.Connector();
        PreparedStatement preparedstaement = null;

        //ResultSet rs = null;

        String query1 = "Update  customers set Username=?, Emailid=?,Contactno=?,Address=?,Nidnum=? WHERE AccountNumber=?";

        try {

            preparedstaement = conn.prepareStatement(query1);
            preparedstaement.setString(1, Userids);
            preparedstaement.setString(2, Emailid);
            preparedstaement.setString(3, Contactno);
            preparedstaement.setString(4, Address);
            preparedstaement.setString(5, Nidno);
            preparedstaement.setString(6, accnos);

            int i=preparedstaement.executeUpdate();
            System.out.println(i);
            // while(rs.next()){
            ///emailid=rs.getString("Emailid");
            /// contactno=rs.getString("Contactno");
            // address=rs.getString("Address");
            /// nidnumber=rs.getString("Nidnum");

            //}
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
            preparedstaement.close();
            conn.close();

        }

    }

}
