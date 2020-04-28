package bank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamstreich
 */
class Password {
    String pword;

    public Password(String pw) {
        this.pword = pw;
        
        
    }

    public String getPword() {
        return pword;
    }
    
    public static Password createPassword(String pw){
        Password rt;
        if(pw.length()>0 && pw.length()<9 && (-1 == pw.indexOf(' '))){
            rt = new Password(pw);
        }else{
            throw new IllegalArgumentException("Does not fit password requirements");
        }
        return rt;
    }

    
    
    
}
