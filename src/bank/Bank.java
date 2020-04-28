package bank;

import java.time.LocalDate;

public class Bank {
    private Customers<Customer> customers;
    private static Manager manager;
    private static LocalDate currentDate; // the current date does not depend on the specific instance of the bank

    public Bank(Customers<Customer> customers, Manager manager) {
        this.customers = customers;
        this.manager = manager;
    }

    public Bank(){
        this.customers = new Customers<Customer>();
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        Bank.currentDate = currentDate;
    }

    public Customers<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Customers<Customer> customers) {
        this.customers = customers;
    }

    public static Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
