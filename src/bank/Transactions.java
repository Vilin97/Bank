package bank;

import java.time.LocalDate;
import java.util.*;

public class Transactions<T extends Transaction> extends ArrayList<T> {

    public Transactions(ArrayList<T> transactions) {
        super(transactions);
    }

    public Transactions() {
        this(new ArrayList<>());
    }

    public Transactions<T> getTransactionsByTimePeriod(LocalDate begin, LocalDate end){
        // get all transactions in the time period
        Transactions<T> res = new Transactions<T>();
        for (T transaction : this) {
            if (transaction.getDate().compareTo(begin) >= 0 && transaction.getDate().compareTo(end) <= 0) res.add(transaction);
        }
        return res;
    }

    @Override
    public String toString() {
        // TODO: make this prettier
        return super.toString();
    }

}
