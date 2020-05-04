package bank;

import java.time.LocalDate;
import java.util.Date;

public class TransactionWithdrawal extends Transaction {
    public TransactionWithdrawal(LocalDate date, double amount, User customer, Account account) {
        super(date, amount, customer, account);
    }
}
