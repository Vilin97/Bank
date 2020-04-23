package Bank;

import static Bank.Name.createName;
import static Bank.Password.createPassword;
import static Bank.UName.createUName;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamstreich
 */
public class Credentials {

   
    private Name name;
    private UName uname;
    private Password pword;
    

    public Credentials(Name name, UName uname, Password pword) {
        this.name = name;
        this.uname = uname;
        this.pword = pword;
        
    }
    
    public static Credentials createCredentials(String fn, String ln, String un, String pw){
        Credentials rt;
        Name name = null;
        UName uname = null;
        Password pword = null;
        
        try{
            name = createName(fn,ln);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        
        try{
            uname = createUName(un);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        
        try{
            pword = createPassword(pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        rt = new Credentials(name,uname,pword);
        return rt;
    }

    public Name getName() {
        return name;
    }

    public Password getPword() {
        return pword;
    }

    public UName getUname() {
        return uname;
    }
    
    
}
