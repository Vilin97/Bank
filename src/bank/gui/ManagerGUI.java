package bank.gui;

import bank.*;

import java.time.LocalDate;
import java.util.Currency;

public class ManagerGUI {

    public static void main(String[] args) {
//        Manager manager = new Manager(new Credentials("Man", "man", "123"));
//        Bank.setCurrentDate(LocalDate.now());
//        Customers<Customer> customers = new Customers<>();
//        Customer customer1 = new Customer("Vas", "v", "123");
//        Customer customer2 = new Customer("Adam", "a", "123");
//        customer1.openCheckingAccount("acc1", Currency.getInstance("USD"));
//        customer1.openSavingsAccount("acc2", Currency.getInstance("USD"));
//        SavingsAccount savingsAccount = customer1.getSavingsAccounts().get(0);
//        customer1.deposit(100000, savingsAccount);
//        customer1.openSecuritiesAccount("acc3", "USD", customer1.getSavingsAccounts().get(0), 10000);
//        StockMarket stockMarket = new StockMarket();
//        Bank.setStockMarket(stockMarket);
//        customers.add(customer1);
//        customers.add(customer2);
//        customer1.requestLoan("loan", "USD", 100, savingsAccount, new Collateral("car", 100));
//        Bank.setCustomers(customers);
//        Bank.getManager().addNewStock("stock1", 100, 3);
//        customer1.buyStock("stock1", customer1.getSecuritiesAccounts().get(0));
//        Bank.getManager().changeStockPrice("stock1", 2000);
//        customer1.sellStock("stock1", customer1.getSecuritiesAccounts().get(0));
        // TODO: add option to deposit and withdraw money
        new MainManagerFrame();
    }

}
