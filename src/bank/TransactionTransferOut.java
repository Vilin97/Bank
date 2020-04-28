package bank;

import java.time.LocalDate;

public class TransactionTransferOut extends Transaction {
    public TransactionTransferOut(LocalDate date, double amount) {
        super(date, amount);
    }
}
