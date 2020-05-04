package bank;

import java.time.LocalDate;

public class TransactionBuyStock extends Transaction {
    Stock stock;

    public TransactionBuyStock(LocalDate date, double amount, User customer, Account account, Stock stock) {
        super(date, amount, customer, account);
        this.stock = stock;
    }
}
