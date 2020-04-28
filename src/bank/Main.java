package bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        boolean test = true;

        if (test){
            Customer customer1 = new Customer();

            Bank.setCurrentDate(LocalDate.now());
            Bank.setManager(Manager.createManager("a","b","c","d"));
            Bank.setCustomers(new Customers<>(new ArrayList<>(Arrays.asList(customer1))));

            Currency usdCurrency = Currency.getInstance("USD");
            Currency eurCurrency = Currency.getInstance("EUR");
            Currency rubCurrency = Currency.getInstance("RUB");
            Account checking1 = new CheckingAccount("my checking account 1", eurCurrency);
            Account checking2 = new CheckingAccount("my checking account 2", eurCurrency);
            Account savings1 = new SavingsAccount("my savings account", rubCurrency);
            Account securities1 = new SecuritiesAccount("my sec account", usdCurrency);
            Accounts<Account> accounts = new Accounts<>(new ArrayList<>(Arrays.asList(checking1, savings1, securities1)));

            customer1.getCheckingAccounts().add((CheckingAccount) checking1);
            customer1.getCheckingAccounts().add((CheckingAccount) checking2);
            customer1.getSavingsAccounts().add((SavingsAccount) savings1);
            customer1.getSecuritiesAccounts().add((SecuritiesAccount) securities1);

            checking1.deposit(100);
            checking1.transfer(securities1, 10);
            System.out.println(securities1);

            for (Account account:accounts) {
                account.deposit(1000000);
                account.withdraw(10);
                account.transfer(securities1, 20);
            }

            //System.out.println(Bank.getManager().getAccount());
            for (Account account:accounts) {
                System.out.println(account);
            }
            Bank.moveTimeToDate(LocalDate.of(2020,10, 26));
            for (Account account:accounts) {
                System.out.println(account);
            }
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
            Bank bank = new Bank();
        }
    }
}
