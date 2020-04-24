package bank;

import java.time.LocalDate;
import java.util.Date;

abstract public class Transaction implements Comparable<Transaction>{
    LocalDate date;

    public Transaction(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.getClass().getName() +
                ", date=" + date +
                '}';
    }

    public int compareTo(Transaction o) {
        return date.compareTo(o.getDate());
    }
}
