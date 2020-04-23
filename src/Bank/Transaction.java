package Bank;

import java.util.Date;

abstract public class Transaction {
    Date date;

    public Transaction(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                '}';
    }
}
