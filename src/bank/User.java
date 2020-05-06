package bank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author adamstreich
 */
public abstract class User implements Permisions {
    private Credentials cred;
    private int ID;
    private static int nextID = 0;

    public User(Credentials cred, int ID) {
        this.cred = cred;
        this.ID = ID;
    }

    public User(Credentials cd){
        this.cred = cd;
        ID = nextID;
        nextID += 1;
    }

    public User(String fname, String lname, String uname, String pword){
        this(new Credentials(fname,lname,uname,pword));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
    
    public Credentials getCreds(){
        return this.cred;
    }

    @Override
    public String toString() {
        return cred.toString();
    }

    public String getName() {
        return this.cred.getName().toString();
    }

    public int getID() {
        return this.ID;
    }

    public String getUName() {
        return String.valueOf(this.cred.getUname());
    }

    public void setCred(Name name, UName uname, int userID) {
        this.cred.setName(name);
        this.cred.setUname(uname);
        this.ID = userID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public JSONObject toJSON(){
        JSONObject userObject = new JSONObject();
        userObject.put("ID", getID());
        userObject.put("cred", getCreds().toJSON());
        String type;
        if (this instanceof Customer) type = "Customer";
        else type = "Manager";
        userObject.put("type",type);
        return userObject;
    }

    public static User fromJSON(JSONObject jsonObject){
        String type = (String) jsonObject.get("type");
        if (type.equals("Customer")) return Customer.fromJSON(jsonObject);
        else return Manager.fromJSON(jsonObject);
    }

}
