package Bank;

public class CannotDepositBehavior extends DepositBehavior {
    public CannotDepositBehavior(Account account) {
        super(account);
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Cannot deposit");
    }
}
