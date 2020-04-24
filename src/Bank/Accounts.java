package Bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Accounts<A extends Account> implements Collection<A> {
    protected ArrayList<A> accounts;

    public Accounts(ArrayList<A> accounts) {
        this.accounts = accounts;
    }

    public Accounts() {
        this(new ArrayList<A>());
    }

    @Override
    public int size() {
        return accounts.size();
    }

    @Override
    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return accounts.contains(o);
    }

    @Override
    public Iterator<A> iterator() {
        return accounts.iterator();
    }

    @Override
    public Object[] toArray() {
        return accounts.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return accounts.toArray(a);
    }

    @Override
    public boolean add(A a) {
        return accounts.add(a);
    }

    @Override
    public boolean remove(Object o) {
        return accounts.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return accounts.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends A> c) {
        return accounts.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return accounts.retainAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return accounts.retainAll(c);
    }

    @Override
    public void clear() {
        accounts.clear();
    }
}
