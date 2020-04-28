package bank;

import java.util.Currency;

public class SavingsAccount extends Account {
    public SavingsAccount(String name, Transactions transactions, double balance, Currency currency, Customer customer) {
        super(name, transactions, balance, currency, customer);
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new UncheckedTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthInterestBehavior(this);
    }

    public SavingsAccount(String name, Currency currency, Customer customer) {
        this(name,new Transactions(), 0, currency, customer);
    }

    @Override
    public String toString() {
        return "Savings Account";
    }
}
