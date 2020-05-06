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
class UName {
    String uname;


    public UName(String name) {
        this.uname = name;
        
    }
    

    public String getUname() {
        return uname;
    }
    
    public static UName createUName(String nm){
        System.out.println("Uname constructor: "+nm);
        UName rt;
        nm = nm.trim();
        
        if(!nm.isEmpty()){
            rt = new UName(nm);
        }else{
            throw new IllegalArgumentException("Does not fit username requirements");
        }
        return rt;
    }
    
    public String toString(){
        return this.uname;
    }
}
