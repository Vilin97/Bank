package bank;

import java.time.LocalDate;

public class TransactionTransferIn extends Transaction {
    public TransactionTransferIn(LocalDate date, double amount) {
        super(date, amount);
    }
}
