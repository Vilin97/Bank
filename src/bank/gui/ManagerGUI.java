package bank.gui;

import bank.*;

import java.time.LocalDate;
import java.util.Currency;

public class ManagerGUI {

    public static void main(String[] args) {
        Currency usdCurrency = Currency.getInstance("USD");
        Currency eurCurrency = Currency.getInstance("EUR");
        Currency rubCurrency = Currency.getInstance("RUB");
        Manager manager = new Manager(Credentials.createCredentials("a","b","c","d"));
        new Bank(manager);
        Bank.setCurrentDate(LocalDate.now());
        manager.receiveMoney(usdCurrency, 10000);
        Customer customer1 = new Customer(new Credentials("Vasily","Vas", "123"));
        Customer customer2 = new Customer(new Credentials("Adam","Adam", "1234"));
        Bank.getCustomers().add(customer1);
        Bank.getCustomers().add(customer2);
        manager.addNewStock("Stock1", 20, 2);
        customer1.openCheckingAccount("my checking account 1", "EUR");
        customer1.openSavingsAccount("my savings account 1", "USD");
        CheckingAccount checking1 = customer1.getCheckingAccounts().get(0);
        SavingsAccount savings1 = customer1.getSavingsAccounts().get(0);

        checking1.deposit(20000);
        customer1.transferToAccount(checking1, savings1, 15000);
        customer1.openSecuritiesAccount("my sec account", "USD", (SavingsAccount) savings1, 6000);
        SecuritiesAccount securities1 = customer1.getSecuritiesAccounts().get(0);

        manager.addNewStock("Stock1", 100, 10);

        customer1.buyStock("Stock1", securities1);
        customer1.buyStock("Stock1", securities1);
        manager.changeStockPrice("Stock1", 300);
        customer1.sellStock("Stock1", securities1);
        customer1.requestLoan("loan1", "USD", 100, savings1);
        new MainManagerFrame();

    }

}
