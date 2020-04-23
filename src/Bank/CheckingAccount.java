package Bank;

import java.util.Currency;

public class CheckingAccount extends Account {
    public CheckingAccount(String name, Transactions transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency);
    }

    public CheckingAccount(String name, Currency currency) {
        super(name, currency);
    }

    public void withdraw(double amount) {
        double commission = amount*Constants.getWithdrawalFeeFraction();
        if (amount >= 0 && getBalance() >= amount + commission){
            setBalance(getBalance() - amount - commission);
            getTransactions().add(new TransactionWithdrawal(Bank.getCurrentDate()));
            System.out.println(amount+getCurrency().getDisplayName() + " was withdrawn. Commission: "+
                    commission+getCurrency().getDisplayName());
            // TODO: give the manager the commission
        }
    }

    public void deposit(double amount) {
        double commission = amount*Constants.getDepositFeeFraction();
        if (amount >= 0 && getBalance() >= amount + commission){
            setBalance(getBalance() + amount - commission);
            getTransactions().add(new TransactionDeposit(Bank.getCurrentDate()));
            System.out.println((amount-commission)+getCurrency().getDisplayName() + " was deposited. Commission: "+
                    commission+getCurrency().getDisplayName());
            // TODO: give the manager the commission
        }
    }
}
