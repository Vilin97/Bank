package bank;

import java.time.LocalDate;

public class TransactionDeposit extends Transaction {
    public TransactionDeposit(LocalDate date,double amount) {
        super(date,amount);
    }
}
