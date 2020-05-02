package bank;

import java.util.*;

public class Accounts<A extends Account> implements List<A> {
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

    @Override
    public boolean addAll(int index, Collection<? extends A> c) {
        return accounts.addAll(index, c);
    }

    @Override
    public A set(int index, A element) {
        return accounts.set(index, element);
    }

    @Override
    public void add(int index, A element) {
        accounts.add(index, element);
    }

    @Override
    public A remove(int index) {
        return accounts.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return accounts.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return accounts.lastIndexOf(o);
    }

    @Override
    public ListIterator<A> listIterator() {
        return accounts.listIterator();
    }

    @Override
    public ListIterator<A> listIterator(int index) {
        return accounts.listIterator(index);
    }

    @Override
    public List<A> subList(int fromIndex, int toIndex) {
        return accounts.subList(fromIndex, toIndex);
    }

    @Override
    public A get(int index) {
        return accounts.get(index);
    }

    public A getByID(int ID) throws NoSuchFieldError{
        for (A a:accounts) {
            if (a.getID() == ID) return a;
        }
        throw new NoSuchFieldError("no ID "+ID+" found");
    }

    @Override
    public String toString() {
        return accounts.toString();
    }
}
