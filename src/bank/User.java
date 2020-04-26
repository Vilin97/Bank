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
public abstract class User implements Permisions {
    private Credentials cred;
    private int ID;
    private static int nextID = 0;
    
    public User(Credentials cd){
        this.cred = cd;
        ID = nextID;
        nextID += 1;
    }
    
}
