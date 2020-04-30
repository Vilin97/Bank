package bank;

import java.time.LocalDate;

public class Bank {
    private static Customers<Customer> customers;
    private static Manager manager;
    private static StockMarket stockMarket;
    private static LocalDate currentDate; // the current date does not depend on the specific instance of the bank

    public Bank(Customers<Customer> customers, Manager manager, StockMarket stockMarket) {
        Bank.customers = customers;
        Bank.manager = manager;
        Bank.stockMarket = stockMarket;
    }

    public Bank(Manager manager) {
        this(new Customers<Customer>(), manager, new StockMarket());
    }

    public Bank(Customers<Customer> customers, Manager manager) {
        this(customers, manager, new StockMarket());
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        Bank.currentDate = currentDate;
    }

    public static Customers<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(Customers<Customer> customers) {
        Bank.customers = customers;
    }

    public static Manager getManager() {
        return manager;
    }

    public static void setManager(Manager manager) {
        Bank.manager = manager;
    }

    public static StockMarket getStockMarket() {
        return stockMarket;
    }

    public static void setStockMarket(StockMarket stockMarket) {
        Bank.stockMarket = stockMarket;
    }
}
