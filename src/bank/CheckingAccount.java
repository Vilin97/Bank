package bank;

import java.util.Currency;

public class CheckingAccount extends Account {
    public CheckingAccount(String name, Transactions transactions, double balance, Currency currency, Customer customer) {
        super(name, transactions, balance, currency, customer);
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new UncheckedTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);
    }

    public CheckingAccount(String name, Currency currency, Customer customer) {
        this(name,new Transactions(), 0, currency, customer);
    }

    @Override
    public String toString() {
        return "Checking Account";
    }
}
