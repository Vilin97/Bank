package bank;

public abstract class DepositBehavior {
    protected Account account;

    public DepositBehavior(Account account) {
        this.account = account;
    }

    abstract public void deposit(double amount);
}
