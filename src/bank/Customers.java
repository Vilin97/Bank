package bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Customers<C extends Customer> implements Collection<C> {
    protected ArrayList<C> customers;

    public Customers(ArrayList<C> customers) {
        this.customers = customers;
    }

    public Customers() {
        this(new ArrayList<C>());
    }

    @Override
    public int size() {
        return customers.size();
    }

    @Override
    public boolean isEmpty() {
        return customers.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return customers.contains(o);
    }

    @Override
    public Iterator<C> iterator() {
        return customers.iterator();
    }

    @Override
    public Object[] toArray() {
        return customers.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return customers.toArray(a);
    }

    @Override
    public boolean add(C c) {
        return customers.add(c);
    }

    @Override
    public boolean remove(Object o) {
        return customers.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return customers.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends C> c) {
        return customers.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return customers.remove(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return customers.retainAll(c);
    }

    @Override
    public void clear() {
        customers.clear();
    }

    @Override
    public String toString() {
        return customers.toString();
    }
}
