package bank;

import java.time.LocalDate;
import java.util.Date;

abstract public class Transaction implements Comparable<Transaction>{
    LocalDate date;
    double amount;
    User customer;
    Account account;
    // TODO: refactor transaction to include user and account

    public Transaction(LocalDate date, double amount, User customer, Account account) {
        this.date = date;
        this.amount = amount;
        this.customer = customer;
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{customer="+customer.getCreds().getName()+
                ", account="+account.getClass().getSimpleName()+
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }

    public int compareTo(Transaction o) {
        return date.compareTo(o.getDate());
    }
}
