package bank;

import java.util.Currency;

public class ManagerAccount extends Account {
    public ManagerAccount(int ID, String name, Transactions<Transaction> transactions, double balance, Currency currency, WithdrawBehavior withdrawBehavior, DepositBehavior depositBehavior, TransferBehavior transferBehavior, EndOfMonthBehavior endOfMonthBehavior) {
        super(ID, name, transactions, balance, currency, withdrawBehavior, depositBehavior, transferBehavior, endOfMonthBehavior);
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new UncheckedTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);
    }

    public ManagerAccount(String name, Transactions transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency);
    }

    public ManagerAccount(String name, Currency currency) {
        super(name, currency);
    }
    public ManagerAccount(Currency currency) {
        super("Manager's account", currency);
    }
}
