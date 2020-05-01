package bank;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        boolean test = true;

        if (test){
            Currency usdCurrency = Currency.getInstance("USD");
            Currency eurCurrency = Currency.getInstance("EUR");
            Currency rubCurrency = Currency.getInstance("RUB");
            Manager manager = new Manager(Credentials.createCredentials("a","b","c","d"));
            new Bank(manager);
            Bank.setCurrentDate(LocalDate.now());
            manager.receiveMoney(usdCurrency, 10000);
            Customer customer1 = new Customer();
            Customer customer2 = new Customer();
            Bank.getCustomers().add(customer1);
            Bank.getCustomers().add(customer2);


            Account checking1 = new CheckingAccount("my checking account 1", eurCurrency);
            Account savings1 = new SavingsAccount("my savings account", usdCurrency);
            customer1.getCheckingAccounts().add((CheckingAccount) checking1);
            customer1.getSavingsAccounts().add((SavingsAccount) savings1);

            checking1.deposit(20000);
            customer1.transferToAccount(checking1, savings1, 15000);
            customer1.openSecuritiesAccount("my sec account", "USD", (SavingsAccount) savings1, 6000);
            SecuritiesAccount securities1 = customer1.getSecuritiesAccounts().get(0);

            manager.addNewStock("Stock1", 100, 10);

            customer1.buyStock("Stock1", securities1);
            customer1.buyStock("Stock1", securities1);
            manager.changeStockPrice("Stock1", 300);
            customer1.sellStock("Stock1", securities1);
            System.out.println(securities1);
            System.out.println(customer1.getRealizedProfit());
            System.out.println(customer1.getUnrealizedProfit());

//            Stock stock2 = new Stock("Stock2");
//            Stock stock3 = new Stock("Stock2");
//
//            SavingsAccount savings2 = new SavingsAccount("my savings account", usdCurrency);
//            customer2.getSavingsAccounts().add(savings2);
//            customer2.requestLoan("my loan", "USD", 1000, savings2);
//            for (Account account:customer2.getAllAccounts()) {
//                System.out.println(account);
//            }
//            manager.approveLoan(customer2, customer2.getPendingLoans().get(0));

//            checking1.deposit(100);
//            checking1.transfer(securities1, 10);
//            System.out.println(securities1);
//
//            for (Account account:accounts) {
//                account.deposit(1000000);
//                account.withdraw(10);
//                account.transfer(securities1, 20);
//            }

            //System.out.println(Bank.getManager().getAccount());
//            for (Account account:customer2.getAllAccounts()) {
//                System.out.println(account);
//            }
//            manager.moveTimeToDate(LocalDate.of(2020,10, 26));
//            for (Account account:customer2.getAllAccounts()) {
//                System.out.println(account);
//            }
//            checking1.deposit(100);
//            checking1.transfer(savings1,50);
//            System.out.println(checking1);
//            System.out.println(savings1);
//            System.out.println(checking1);
//            checking1.deposit(100);
//            System.out.println(checking1);
//            checking1.withdraw(50);
//            System.out.println(checking1);

        } else {
            // Bank bank = new Bank();
        }
    }
}
