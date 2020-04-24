package Bank;

import java.util.Currency;

public class CreditAccount extends Account {
    public CreditAccount(String name, Transactions transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency);
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new CheckedTransferBehavior(this);
    }

    public CreditAccount(String name, Currency currency) {
        this(name,new Transactions(), 0, currency);
    }
}
