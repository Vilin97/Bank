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
    Credentials cred;
    //ID
    
    public User(Credentials cd){
        this.cred = cd;
    }
    
}
