package bank;

import java.util.Currency;

public class ManagerAccount extends Account {
    public ManagerAccount(String name, Transactions<Transaction> transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency, Bank.getManager());
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new UncheckedTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);
    }

    public ManagerAccount(String name, Currency currency) {
        this(name, new Transactions<>(), 0, currency);
    }
    public ManagerAccount(Currency currency) {
        this("Manager's account", currency);
    }
}
