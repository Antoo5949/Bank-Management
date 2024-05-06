/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signup;

import databse.sqliteconnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;

/**
 *
 * @author Antoo5949
 */
public class Signupmodel {

    ///Alert.AlertType a= Error;
    public static PreparedStatement userexists = null;
    public static ResultSet rss = null;
     public static PreparedStatement  preparedstaement = null;
       public static PreparedStatement userexist = null;
      public static  ResultSet rs = null;
       public static Connection conn = sqliteconnection.Connector();
        public static FileInputStream fis;

    public static void insert(String name, String emailid, String userid, String password, String contactno, String address, String nidno, LocalDate dateofb, int pin,File files) throws SQLException, FileNotFoundException {
       
       
        String query2 = "INSERT INTO customers(Name,Emailid,Username, Password,Contactno,Address,NidNum,Dob,Pin,Image) VALUES(?,?,?,?,?,?,?,?,?,?)";
        String query1 = "SELECT * from customers WHERE Emailid=?";

        try {
            userexist = conn.prepareStatement(query1);
            userexist.setString(1, emailid);
            rs = userexist.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User already exists!");
                alert.setHeaderText(null);
                alert.setContentText("You can not use this email");
                alert.showAndWait();
            } else {
                preparedstaement = conn.prepareStatement(query2);
                preparedstaement.setString(1, name);
                preparedstaement.setString(2, emailid);
                preparedstaement.setString(3, userid);
                preparedstaement.setString(4, password);
                preparedstaement.setString(5, contactno);
                preparedstaement.setString(6, address);
                preparedstaement.setString(7, nidno);
                preparedstaement.setString(8, String.valueOf(dateofb));
                preparedstaement.setInt(9, pin);
                
                fis= new FileInputStream(files);
                preparedstaement.setBinaryStream(10, (InputStream)fis, (int)files.length());
                

                preparedstaement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thank You");
                alert.setHeaderText(null);
                alert.setContentText("Thamk you for Signing Up");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            System.out.println(e);

        } finally {
            if (userexist != null) {
                try {
                    userexist.close();
                    rs.close();
                    conn.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }

            }
            if (preparedstaement != null) {

                try {
                    preparedstaement.close();
                    conn.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }

            }
        }

    }

    public static boolean validateEmail(String Emailid) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(Emailid);
        /// System.out.println(m.group());
        if (m.find() && m.group().equals(Emailid)) {

            return true;

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid Email");
            alert.showAndWait();

            return false;

        }

    }

    public static boolean Contactno(String contactno) {
        Pattern p = Pattern.compile("(01)[0-9]{9}");
        Matcher m = p.matcher(contactno);
        /// System.out.println(m.group());
        if (m.find() && m.group().equals(contactno)) {

            return true;

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Contact Number");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid Contact Number");
            alert.showAndWait();

            return false;

        }

    }

    public static boolean Passwordvalidation(String password) {
        Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{6,20})");
        Matcher m = p.matcher(password);
        /// System.out.println(m.group());
        if (m.find() && m.group().equals(password)) {

            return true;

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Password");
            alert.setHeaderText(null);
            alert.setContentText("Your password must contain  contain 8 characters and at least one number, one letter and one unique character such as !#$%&?  ");
            alert.showAndWait();

            return false;

        }

    }

    public static boolean validateDob(LocalDate date) {
        try {
            Period diff = Period.between(date, LocalDate.now());
            if (diff.getYears() >= 18) {
                return true;
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid age");
                alert.setHeaderText(null);
                alert.setContentText("You must be at least 18 years old to sign up");
                alert.showAndWait();

                return false;

            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Age");
            alert.setHeaderText(null);
            alert.setContentText("Please fill up The date of Birth");
            alert.showAndWait();
        }
        return false;

    }

    public static boolean ifUseridexists(String userid) throws SQLException {
         conn = sqliteconnection.Connector();
         
        String query1 = "SELECT * from customers WHERE Username=?";
        try {
           // PreparedStatement userexist = null;
            userexist = conn.prepareStatement(query1);
            userexist.setString(1, userid);
            rs = userexist.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("UserId already exists!");
                alert.setHeaderText(null);
                alert.setContentText("You can not use this UserName");
                alert.showAndWait();
                return true;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            userexist.close();
            rs.close();
        
        
        }
        return false;

    }

    public static boolean ifaccnodexists(int accno) throws SQLException {
         conn = sqliteconnection.Connector();

        String query1 = "SELECT * from customers WHERE AccountNumber=?";
        try {

            userexists = conn.prepareStatement(query1);
            userexists.setInt(1, accno);
            rss = userexists.executeQuery();
            if (rss.isBeforeFirst()) {

                return true;
                //userexist.close();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            userexists.close();
            rss.close();
            conn.close();

        }
        return false;

    }

    public static boolean nameempty(String name) {

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Name feild is Empty");
            alert.setHeaderText(null);
            alert.setContentText("Please Insert your name");
            alert.showAndWait();
            return false;

        }
        return true;

    }
    
     public static boolean pinempty(String pin) {

        if (pin.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pin feild is Empty");
            alert.setHeaderText(null);
            alert.setContentText("Please Insert your Pin");
            alert.showAndWait();
            return false;

        }
        return true;

    }
}
