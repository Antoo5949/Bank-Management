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
public class Transferhistory {
    
    private String dates;
    private String Names;
    private int Accnos;
    private int Amounts;
    private int PreviousBalances;

    public Transferhistory(String dates, String Names, int Accnos, int Amounts, int PreviousBalances) {
        this.dates = dates;
        this.Names = Names;
        this.Accnos = Accnos;
        this.Amounts = Amounts;
        this.PreviousBalances = PreviousBalances;
    }

    public String getDates() {
        return dates;
    }

    public String getNames() {
        return Names;
    }

    public int getAccnos() {
        return Accnos;
    }

    public int getAmounts() {
        return Amounts;
    }

    public int getPreviousBalances() {
        return PreviousBalances;
    }

    
   

   

    
    
    
}
