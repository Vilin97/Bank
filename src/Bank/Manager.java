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
public class Manager extends User {
    
    public Manager(Credentials cd) {
        super(cd);
    }

    @Override
    public boolean sudoUser() {
        return true;
    }
    
}
