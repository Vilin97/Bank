package bank;

import java.time.LocalDate;
import java.util.Currency;

import static bank.Credentials.createCredentials;

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
    private ManagerAccount account;

    public Manager(Credentials cd, ManagerAccount managerAccount) {
        super(cd);
        this.account = managerAccount;
    }

    public Manager(Credentials cd) {
        this(cd, new ManagerAccount(Currency.getInstance("USD")));
    }
    
    public static Manager createManager(String fn, String ln, String un, String pw){
        Manager rt;
        Credentials cr;
        try{
            cr = createCredentials(fn,ln,un,pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("Wrong credential specs");
        }
        rt = new Manager(cr);
        return rt;
    }

    @Override
    public boolean sudoUser() {
        return true;
    }

    private void approveLoan(Customer customer, PendingLoan pendingLoan){
        // approve the pending loan
        if (account.getBalanceUSD() >= pendingLoan.getBalanceUSD()){
            customer.getLoans().add(new Loan(pendingLoan));
            customer.getPendingLoans().remove(pendingLoan);
            account.changeBalanceBy(-pendingLoan.getBalance());
        }
    }

    private void disapproveLoan(Customer customer, PendingLoan pendingLoan){
        // disapprove the pending loan
        customer.getPendingLoans().remove(pendingLoan);
    }

    public void receiveMoney(double amount){
        if (amount > 0){
            account.changeBalanceBy(amount);
        }
    }

    public ManagerAccount getAccount() {
        return account;
    }

    public void setAccount(ManagerAccount account) {
        this.account = account;
    }
}
