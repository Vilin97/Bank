package bank;

public abstract class TransferBehavior {
    protected Account account;

    public TransferBehavior(Account account) {
        this.account = account;
    }

    abstract public void transfer(Account account,double amount);
    abstract public void forceTransfer(Account account,double amount);
}
