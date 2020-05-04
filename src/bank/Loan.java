package bank;

import java.util.Currency;

public class Loan extends Account {
    public Loan(String name, Transactions transactions, double balance, Currency currency, Customer customer) {
        super(name, transactions, balance, currency, customer);
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new CannotTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNegativeInterestBehavior(this);
    }

    public Loan(String name, Currency currency, Customer customer) {
        this(name,new Transactions(), 0, currency, customer);
    }

    public Loan(PendingLoan pendingLoan){
        // get a loan from the pending loan
        this(pendingLoan.name, pendingLoan.transactions, 0, pendingLoan.currency, (Customer) pendingLoan.getUser());
        forceTransfer(pendingLoan.getSavingsAccount(), -pendingLoan.getBalance());
    }

    private void forceTransfer(Account o, double amount){
        // force transfer to account
        transferBehavior.forceTransfer(o,amount);
    }
}
