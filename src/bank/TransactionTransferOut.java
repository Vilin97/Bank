package bank;

import java.time.LocalDate;

public class TransactionTransferOut extends Transaction {
    public TransactionTransferOut(LocalDate date, double amount, User customer, Account account) {
        super(date, amount, customer, account);
    }
}
