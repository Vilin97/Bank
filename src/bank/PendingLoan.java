package bank;

import java.util.Currency;

public class PendingLoan extends Loan {
    private SavingsAccount savingsAccount;
    // class representing a pending (not yet approved loan)
    public PendingLoan(String name, Transactions transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency);
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CannotDepositBehavior(this);
        this.transferBehavior = new CannotTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);
    }

    public PendingLoan(String name, Currency currency) {
        this(name,new Transactions(), 0, currency);
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
