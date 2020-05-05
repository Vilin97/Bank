package bank;

import java.time.temporal.ChronoUnit;
import java.util.Currency;

public class PendingLoan extends Loan {
    private SavingsAccount savingsAccount;
    // class representing a pending (not yet approved) loan
    public PendingLoan(String name, Transactions<Transaction> transactions, double balance, Currency currency, Customer user, SavingsAccount savingsAccount, Collateral collateral) {
        super(name, transactions, balance, currency, user, collateral);
        this.savingsAccount = savingsAccount;
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CannotDepositBehavior(this);
        this.transferBehavior = new CannotTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);
    }

    public PendingLoan(String name, double balance, Currency currency, Customer user, SavingsAccount savingsAccount, Collateral collateral) {
        this(name,new Transactions<Transaction>(), balance, currency, user, savingsAccount, collateral);
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

}
