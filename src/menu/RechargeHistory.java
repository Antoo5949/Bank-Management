/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

/**
 *
 * @author Antoo5949
 */
public class RechargeHistory {
    private String date;
    private String Mobileno;
    private int amount;
    private int previousbalance;

    public RechargeHistory(String date, String Mobileno, int amount, int previousbalance) {
        this.date = date;
        this.Mobileno = Mobileno;
        this.amount = amount;
        this.previousbalance = previousbalance;
    }

    public String getDate() {
        return date;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public int getAmount() {
        return amount;
    }

    public int getPreviousbalance() {
        return previousbalance;
    }
 
    
    
}
