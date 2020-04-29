package bank;

import java.time.LocalDate;

public class TransactionBuyStock extends Transaction {
    Stock stock;

    public TransactionBuyStock(LocalDate date, double amount, Stock stock) {
        super(date, amount);
        this.stock = stock;
    }
}
