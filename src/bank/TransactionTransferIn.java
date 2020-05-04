package bank;

import java.time.LocalDate;

public class TransactionTransferIn extends Transaction {
    public TransactionTransferIn(LocalDate date, double amount, User customer, Account account) {
        super(date, amount, customer, account);
    }
}
