package bank;

import org.json.simple.JSONObject;

import static bank.Name.createName;
import static bank.Password.createPassword;
import static bank.UName.createUName;

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

    public Credentials(String name, String uname, String pword) {
        this(new Name(name,""), new UName(uname), new Password(pword) );
    }

    public Credentials (String fname, String lname, String uname, String pword) {
        this(new Name(fname,lname), new UName(uname), new Password(pword) );
    }

    public static Credentials createCredentials(String fn, String ln, String un, String pw){
        Credentials rt;
        Name name = null;
        UName uname = null;
        Password pword = null;
        boolean gname = true;
        boolean guname = true;
        boolean gpword = true;
        
        try{
            name = createName(fn,ln);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            gname = false;
        }
        
        try{
            uname = createUName(un);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            guname = false;
        }
        
        try{
            pword = createPassword(pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            gpword = false;
        }
        
        if( gname && guname && gpword){
            rt = new Credentials(name,uname,pword);
        }else{
            throw new IllegalArgumentException("not correct inputs");
        } 
        return rt;
    }

    public Name getName() {
        return name;
    }

    public Password getPword() {
        return pword;
    }

    public Password getPassword() {
        return pword;
    }

    public UName getUname() {
        return uname;
    }

    public boolean login(String uname,String pword){
        boolean rt = false;
        String tempp = this.pword.getPword();
        String tempu = this.uname.getUname();
        System.out.println("this uname: "+ tempu+ " this pword: "+tempp);
         System.out.println("tried uname: "+ uname+ " tried pword: "+pword);



        if(tempu.equals(uname) && tempp.equals(pword)){
            rt = true;
            System.out.println("here");
        }
        return rt;
    }


    @Override
    public String toString() {
        return "name=" + name +
                ", uname=" + uname;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setUname(UName uname) {
        this.uname = uname;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name.toJSON());
        jsonObject.put("uname", uname.toJSON());
        jsonObject.put("pword", pword.toJSON());
        return jsonObject;
    }

    public static Credentials fromJSON(JSONObject jsonObject){
        Name name = Name.fromJSON((JSONObject) jsonObject.get("name"));
        UName uname = UName.fromJSON((JSONObject) jsonObject.get("uname"));
        Password pword = Password.fromJSON((JSONObject) jsonObject.get("pword"));
        return new Credentials(name, uname, pword);
    }

}
