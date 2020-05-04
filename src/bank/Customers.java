package bank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Customers<C extends Customer> extends ArrayList<C> {

    public Customers(ArrayList<C> customers) {
        super(customers);
    }

    public Customers() {
        this(new ArrayList<C>());
    }
}
