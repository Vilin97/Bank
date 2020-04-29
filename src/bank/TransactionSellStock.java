package bank;

import java.time.LocalDate;

public class TransactionSellStock extends Transaction {
    Stock stock;

    public TransactionSellStock(LocalDate date, double amount, Stock stock) {
        super(date, amount);
        this.stock = stock;
    }
}
