package Bank;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Date;
import java.util.Objects;

abstract public class Account {
    protected int ID; // ID is the primary key for accounts
    protected String name;
    protected Transactions<Transaction> transactions;
    protected double balance;
    protected Currency currency;
    protected WithdrawBehavior withdrawBehavior;
    protected DepositBehavior depositBehavior;
    protected TransferBehavior transferBehavior;
    // protected EndOfMonthBehavior endOfMonthBehavior;
    // TODO: implement interest behavior

    private static int nextID = 0;

    public Account(int ID, String name, Transactions<Transaction> transactions,
                   double balance, Currency currency,
                   WithdrawBehavior withdrawBehavior, DepositBehavior depositBehavior, TransferBehavior transferBehavior) {
        this.ID = ID;
        this.name = name;
        this.transactions = transactions;
        this.balance = balance;
        this.currency = currency;
        this.withdrawBehavior = withdrawBehavior;
        this.depositBehavior = depositBehavior;
        this.transferBehavior = transferBehavior;
        nextID += 1;
    }

    public Account(String name, Transactions transactions, double balance, Currency currency) {
        this.ID = nextID;
        this.name = name;
        this.transactions = transactions;
        this.balance = balance;
        this.currency = currency;
        nextID += 1;
    }

    public Account(String name, Currency currency) {
        this(name, new Transactions(), 0, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return ID == account.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public Transactions getTransactionsByTimePeriod(LocalDate begin, LocalDate end) {
        return transactions.getTransactionsByTimePeriod(begin, end);
    }

    public void withdraw(double amount) {
        withdrawBehavior.withdraw(amount);
    }

    public void deposit(double amount) {
        depositBehavior.deposit(amount);
    }

    public void transfer(Account o, double amount) {
        transferBehavior.transfer(o,amount);
    }

    @Override
    public String toString() {
        return this.getClass().getName()+
                ", name='" + name + '\'' +
                ", transactions=" + transactions +
                ", balance=" + balance +
                ", currency=" + currency +
                '}';
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

    public double getBalanceUSD() {
        // get balance in USD equivalent
        return Constants.exchangeCurrencyToUSD(currency, balance);
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
