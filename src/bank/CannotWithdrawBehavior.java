package bank;

public class    CannotWithdrawBehavior extends WithdrawBehavior {
    public CannotWithdrawBehavior(Account account) {
        super(account);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Cannot withdraw");
    }
}
