package bank;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Date;

abstract public class Account {
    protected String name;
    protected Transactions<Transaction> transactions;
    protected double balance;
    protected Currency currency;

    public Account(String name, Transactions transactions, double balance, Currency currency) {
        this.name = name;
        this.transactions = transactions;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(String name, Currency currency) {
        this(name, new Transactions(), 0, currency);
    }

    public Transactions getTransactionsByTimePeriod(LocalDate begin, LocalDate end) {
        return transactions.getTransactionsByTimePeriod(begin, end);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
