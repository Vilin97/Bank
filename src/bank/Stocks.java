package bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Stocks<S extends Stock> implements Collection<S> {
    private ArrayList<S> stocks;

    public Stocks(ArrayList<S> stocks) {
        this.stocks = stocks;
    }

    public Stocks() {
        this(new ArrayList<>());
    }

    @Override
    public String toString() {
        return stocks.toString();
    }

    @Override
    public int size() {
        return stocks.size();
    }

    @Override
    public boolean isEmpty() {
        return stocks.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return stocks.contains(o);
    }

    @Override
    public Iterator<S> iterator() {
        return stocks.iterator();
    }

    @Override
    public Object[] toArray() {
        return stocks.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return stocks.toArray(a);
    }

    @Override
    public boolean add(S s) {
        return stocks.add(s);
    }

    @Override
    public boolean remove(Object o) {
        return stocks.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return stocks.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends S> c) {
        return stocks.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return stocks.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return stocks.retainAll(c);
    }

    @Override
    public void clear() {
        stocks.clear();
    }

    public boolean hasStock(String name){
        for (S s:stocks) {
            if (s.getName().equals(name)) return true;
        }
        return false;
    }

    public S getStock(String name){
        for (S s:stocks) {
            if (s.getName().equals(name)) return s;
        }
        return (S) new Stock("");
    }

}
