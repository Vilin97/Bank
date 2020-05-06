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
class Name {
    String fname;
    String lname;

    public Name(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
        
    }
    
    public static Name createName(String fn, String ln){
        Name rt;
        fn = fn.trim();
        ln = ln.trim();
        if(!fn.isEmpty() && !ln.isEmpty()){
            
            rt = new Name(fn,ln);
        }else{
            throw new IllegalArgumentException("Does not fit  name requirements");
        }
        return rt;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }


    @Override
    public String toString() {
        return this.fname + " " + this.lname;
    }


    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fname", fname);
        jsonObject.put("lname", lname);
        return jsonObject;
    }

    public static Name fromJSON(JSONObject jsonObject){
        String fname = (String) jsonObject.get("fname");
        String lname = (String) jsonObject.get("lname");
        return new Name(fname, lname);
    }
}
