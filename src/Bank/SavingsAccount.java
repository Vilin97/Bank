package Bank;

import java.util.Currency;

public class SavingsAccount extends Account {
    public SavingsAccount(String name, Transactions transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency);
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
    }

    public SavingsAccount(String name, Currency currency) {
        super(name, currency);
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
    }
}
