package bank;

import java.time.LocalDate;
import java.util.Date;

abstract public class Transaction implements Comparable<Transaction>{
    LocalDate date;
    double amount;

    public Transaction(LocalDate date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "{date=" + date +
                ", amount=" + amount +
                '}';
    }

    public int compareTo(Transaction o) {
        return date.compareTo(o.getDate());
    }
}
