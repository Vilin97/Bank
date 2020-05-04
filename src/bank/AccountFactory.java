package bank;

import java.util.Currency;

public class AccountFactory {
    public static<A extends Account> A getAccount(String type, String name, String c, Customer user){
        // if type not recognized, returns a checking account
        // does not sanitize input!
        Currency currency = Currency.getInstance(c);
        A account;
        if (type.equals("savings")) account = (A) new SavingsAccount(name, currency, user);
//        else if (type.equals("securities")) account = (A) new SecuritiesAccount(name, currency);
        else if(type.equals("loan")) account = (A) new PendingLoan(name, currency, user);
        else account = (A) new CheckingAccount(name, currency, user);
        return account;
    }
}