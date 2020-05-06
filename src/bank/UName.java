package bank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.simple.JSONObject;

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
        //System.out.println("Uname constructor: "+nm);
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

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uname", uname);
        return jsonObject;
    }

    public static UName fromJSON(JSONObject jsonObject){
        String uname = (String) jsonObject.get("uname");
        return new UName(uname);
    }
}
