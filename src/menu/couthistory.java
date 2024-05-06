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
public class couthistory {
    
    private String date;
    private String name;
    private String Mobileno;
    private int amount;
    private int previousbalance;

    public couthistory(String date, String name, String Mobileno, int amount, int previousbalance) {
        this.date = date;
        this.name = name;
        this.Mobileno = Mobileno;
        this.amount = amount;
        this.previousbalance = previousbalance;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
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

    
