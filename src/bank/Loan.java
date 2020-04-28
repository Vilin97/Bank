package bank;

import java.util.Currency;

public class Loan extends Account {
    public Loan(String name, Transactions transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency);
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new CannotTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNegativeInterestBehavior(this);
    }

    public Loan(String name, Currency currency) {
        this(name,new Transactions(), 0, currency);
    }

    public Loan(PendingLoan pendingLoan){
        // get a loan from the pending loan
        this(pendingLoan.name, pendingLoan.transactions, pendingLoan.balance, pendingLoan.currency);
    }
}
