package bank;

import static bank.Credentials.createCredentials;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamstreich
 */
public class Customer extends User {
    private ArrayList<String> savingsAcounts; // not sure what the class name is fo this but needs to be changed
    private ArrayList<CheckingAccount> checkingAcounts;
    private ArrayList<SecuritiesAccount> securitiesAcounts;
    //need a loans array
    
    public Customer(Credentials cd) {
        super(cd);
        this.savingsAcounts = new ArrayList<String>(); 
        this.checkingAcounts = new ArrayList<CheckingAccount>();
        this.securitiesAcounts = new ArrayList<SecuritiesAccount>();
        
    }

    public static Customer createCustomer(String fn, String ln, String un, String pw){
        Customer rt;
        Credentials cr;
        try{
            cr = createCredentials(fn,ln,un,pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("Wrong credential specs");
        }
        rt = new Customer(cr);
        return rt;
    }
    
    @Override
    public boolean sudoUser() {
        return false;
    }
    
}
