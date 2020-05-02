package bank;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Currency;

public class ManagerGUI {

    public static void main(String[] args) {
        Currency usdCurrency = Currency.getInstance("USD");
        Manager manager = new Manager(Credentials.createCredentials("a","b","c","d"));
        new Bank(manager);
        Bank.setCurrentDate(LocalDate.now());
        manager.receiveMoney(usdCurrency, 10000);
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Bank.getCustomers().add(customer1);
        Bank.getCustomers().add(customer2);
        new MainManagerFrame();

    }

}
