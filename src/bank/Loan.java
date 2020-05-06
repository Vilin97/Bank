package bank;

import org.json.simple.JSONObject;

import java.util.Currency;

public class Loan extends Account {
    private Collateral collateral;
    public Loan(String name, Transactions<Transaction> transactions, double balance, Currency currency, Customer customer, Collateral collateral) {
        super(name, transactions, balance, currency, customer);
        this.collateral = collateral;
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new CannotTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNegativeInterestBehavior(this);
    }

    public Loan(String name, Currency currency, Customer customer, Collateral collateral) {
        this(name,new Transactions<Transaction>(), 0, currency, customer, collateral);
    }

    public Loan(PendingLoan pendingLoan){
        // get a loan from the pending loan
        this(pendingLoan.name, pendingLoan.transactions, 0, pendingLoan.currency, (Customer) pendingLoan.getUser(), pendingLoan.getCollateral());
        forceTransfer(pendingLoan.getSavingsAccount(), -pendingLoan.getBalance());
    }

    private void forceTransfer(Account o, double amount){
        // force transfer to account
        transferBehavior.forceTransfer(o,amount);
    }

    public Collateral getCollateral() {
        return collateral;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", collateral: "+collateral;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = super.toJSON();
        jsonObject.put("Collateral", collateral.toJSON());
        return jsonObject;
    }
}
