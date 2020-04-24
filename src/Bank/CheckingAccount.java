package Bank;

import java.util.Currency;

public class CheckingAccount extends Account {
    public CheckingAccount(String name, Transactions transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency);
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
    }

    public CheckingAccount(String name, Currency currency) {
        super(name, currency);
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "name='" + name + '\'' +
                ", transactions=" + transactions +
                ", balance=" + balance +
                ", currency=" + currency +
                '}';
    }
}
