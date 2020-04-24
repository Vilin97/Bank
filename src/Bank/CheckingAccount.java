package bank;

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
            System.out.println(amount+" "+getCurrency() + " was withdrawn. Commission: "+
                    commission+" "+getCurrency());
            // TODO: give the manager the commission
        } else {
            System.out.println("Cannot withdraw");
        }
    }

    public void deposit(double amount) {
        double commission = amount*Constants.getDepositFeeFraction();
        if (amount >= 0){
            setBalance(getBalance() + amount - commission);
            getTransactions().add(new TransactionDeposit(Bank.getCurrentDate()));
            System.out.println((amount-commission)+" "+getCurrency() + " was deposited. Commission: "+
                    commission+" "+getCurrency());
            // TODO: give the manager the commission
        } else {
            System.out.println("Cannot deposit");
        }
    }

    @Override
    public String toString() {
        return "CheckingAccount{" +
                "name='" + name + '\'' +
                ", transactions=" + transactions +
                ", balance=" + balance +
                ", currency=" + currency +
                '}';
    }
}
