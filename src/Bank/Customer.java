package Bank;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamstreich
 */
public class Customer extends User {
    
    public Customer(Credentials cd) {
        super(cd);
    }

    @Override
    public boolean sudoUser() {
        return false;
    }
    
}
