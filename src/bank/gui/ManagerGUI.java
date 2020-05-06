package bank.gui;

import bank.*;

import java.time.LocalDate;
import java.util.Currency;

public class ManagerGUI {

    public static void main(String[] args) {
        Customers<Customer> customers = new Customers<>();
        Customer customer1 = new Customer("Vas", "v", "123");
        Customer customer2 = new Customer("Adam", "a", "123");
        customer1.openCheckingAccount("acc1", Currency.getInstance("USD"));
        new MainManagerFrame();
    }

}
