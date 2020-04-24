package bank;

import static bank.Credentials.createCredentials;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamstreich
 */
public class Manager extends User {
    //should create a manager account to hold his money
    
    public Manager(Credentials cd) {
        super(cd);
    }
    
    public static Manager createManager(String fn, String ln, String un, String pw){
        Manager rt;
        Credentials cr;
        try{
            cr = createCredentials(fn,ln,un,pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("Wrong credential specs");
        }
        rt = new Manager(cr);
        return rt;
    }

    @Override
    public boolean sudoUser() {
        return true;
    }
    
}
