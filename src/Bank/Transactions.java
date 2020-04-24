package Bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Transactions<T extends Transaction> implements Collection<T> {
    private ArrayList<T> transactions;

    public Transactions(ArrayList<T> transactions) {
        this.transactions = transactions;
    }

    public Transactions() {
        this(new ArrayList<>());
    }

    @Override
    public int size() {
        return transactions.size();
    }

    @Override
    public boolean isEmpty() {
        return transactions.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return transactions.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return transactions.iterator();
    }

    @Override
    public Object[] toArray() {
        return transactions.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return transactions.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return transactions.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return transactions.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return transactions.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return transactions.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return transactions.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return transactions.retainAll(c);
    }

    @Override
    public void clear() {
        transactions.clear();
    }

    public Transactions<T> getTransactionsByTimePeriod(LocalDate begin, LocalDate end){
        // get all transactions in the time period
        Transactions<T> res = new Transactions<T>();
        for (T transaction : transactions) {
            if (transaction.getDate().compareTo(begin) > 0 && transaction.getDate().compareTo(end) < 0) res.add(transaction);
        }
        return res;
    }

    @Override
    public String toString() {
        return transactions.toString();
    }

    public ArrayList<T> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<T> transactions) {
        this.transactions = transactions;
    }
}
