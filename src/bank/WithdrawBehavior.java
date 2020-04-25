package bank;

abstract public class WithdrawBehavior {
    protected Account account;

    public WithdrawBehavior(Account account) {
        this.account = account;
    }

    abstract public void withdraw(double amount);

}
