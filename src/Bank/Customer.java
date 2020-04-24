package Bank;

import static Bank.Credentials.createCredentials;
import java.util.ArrayList;

//A customer class must have the following functions:
//        Create checking account
//        Create savings account
//        Create securities account
//        Close an account (where does the money go?)
//        Request loan
//        Deposit cash to an account
//        Withdraw money from a checking account
//        View transactions for an account (for a time period)
//        View balance in an account
//        Buy stock
//        Sell stock
//        Play on the stock market (repeatedly buy and sell stocks)
//        See realized profit from the stock market
//        See unrealized profit from the stock market

/**
 *
 * @author adamstreich
 */
public class Customer extends User {
    private Accounts<SavingsAccount> savingsAccounts; // not sure what the class name is fo this but needs to be changed
    private Accounts<CheckingAccount> checkingAccounts;
    private Accounts<SecuritiesAccount> securitiesAccounts;
    //need a loans array
    
    public Customer(Credentials cd) {
        super(cd);
        this.savingsAccounts = new Accounts<SavingsAccount>();
        this.checkingAccounts = new Accounts<CheckingAccount>();
        this.securitiesAccounts = new Accounts<SecuritiesAccount>();
        
    }

    public static Customer createCustomer(String fn, String ln, String un, String pw){
        Customer rt;
        Credentials cr;
        try{
            cr = createCredentials(fn,ln,un,pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("Wrong credential specs");
        }
        rt = new Customer(cr);
        return rt;
    }

    private void openCheckingAccount(String name, String currency) {
        checkingAccounts.add(AccountFactory.getAccount("checking", name, currency));
    }

    private void openSavingsAccount(String name, String currency) {
        checkingAccounts.add(AccountFactory.getAccount("savings", name, currency));
    }

    private void openSecuritiesAccount(String name, String currency, SavingsAccount account, double amount) {
        // open a securities account and transfer amount from account

        checkingAccounts.add(AccountFactory.getAccount("savings", name, currency));
    }
    
    @Override
    public boolean sudoUser() {
        return false;
    }
    
}
