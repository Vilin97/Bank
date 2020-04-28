package bank;

import java.time.LocalDate;

public class Bank {
    // TODO: why the fuck did I make everything static? BS
    private static Customers<Customer> customers;
    private static Manager manager;

    private static LocalDate currentDate; // the current date does not depend on the specific instance of the bank

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        Bank.currentDate = currentDate;
    }

    public static void moveTimeToDate(LocalDate date){
        int numMonths = date.getMonthValue() - currentDate.getMonthValue() + 12*(date.getYear() - currentDate.getYear());
        if (currentDate.compareTo(date) < 0 && numMonths > 0){
            Bank.setCurrentDate(date);
            for (Customer customer:customers) {
                for (Account account:customer.getAllAccounts()) {
                    for (int i = 0; i < numMonths; i++) {
                        account.doEndOfMonth();
                    }
                }
            }
        }
    }

    public Customers<Customer> getCustomers() {
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


}
