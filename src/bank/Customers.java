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

    public Customers<Customer> getCustomersWithPendingLoans() {
        Customers<Customer> customers = new Customers<>();
        for (Customer c:this) {
            if (!c.getPendingLoans().isEmpty()) customers.add(c);
        }
        return customers;
    }

    public Customers<Customer> getCustomersWithCurrentLoans() {
        Customers<Customer> customers = new Customers<>();
        for (Customer c:this) {
            if (!c.getLoans().isEmpty()) customers.add(c);
        }
        return customers;
    }

    public Customers<Customer> getCustomersWithAnyLoans() {
        Customers<Customer> res = getCustomersWithCurrentLoans();
        res.addAll(getCustomersWithPendingLoans());
        return res;
    }


}
