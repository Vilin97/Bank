package bank;

import java.time.LocalDate;
import java.util.Date;

public class TransactionWithdrawal extends Transaction {
    public TransactionWithdrawal(LocalDate date, double amount) {
        super(date, amount);
    }
}
