package bank;

import java.time.temporal.ChronoUnit;
import java.util.Currency;

public class PendingLoan extends Loan {
    private SavingsAccount savingsAccount;
    // class representing a pending (not yet approved loan)
    public PendingLoan(String name, Transactions transactions, double balance, Currency currency, Customer user) {
        super(name, transactions, balance, currency, user);
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CannotDepositBehavior(this);
        this.transferBehavior = new CannotTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);
    }

    public PendingLoan(String name, Currency currency, Customer user) {
        this(name,new Transactions(), 0, currency, user);
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
