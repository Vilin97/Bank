package bank;

import java.time.LocalDate;

public class TransactionSellStock extends Transaction {
    Stock stock;

    public TransactionSellStock(LocalDate date, double amount, User customer, Account account, Stock stock) {
        super(date, amount, customer, account);
        this.stock = stock;
    }

    public Stock getStock() {
        return stock;
    }
}
