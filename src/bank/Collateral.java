/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author adamstreich
 */
public class Collateral {
    private String name;
    private double worth;

    public Collateral(String name, double worth) {
        this.name = name;
        this.worth = worth;
    }

    public String getName() {
        return name;
    }

    public double getWorth() {
        return worth;
    }
    
    
  
}
