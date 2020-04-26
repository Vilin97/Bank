package bank;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        boolean test = true;
        if (test){
            Bank.setCurrentDate(LocalDate.now());
            Currency usdCurrency = Currency.getInstance("USD");
            Currency eurCurrency = Currency.getInstance("EUR");
            Currency rubCurrency = Currency.getInstance("RUB");
            Account checking1 = new CheckingAccount("my checking account", eurCurrency);
            Account savings1 = new SavingsAccount("my savings account", rubCurrency);
            checking1.deposit(100);
//            checking1.transferToAccount(savings1,50);
            System.out.println(checking1);
            System.out.println(savings1);
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
