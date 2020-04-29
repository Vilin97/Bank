package bank;

public class CannotTransferBehavior extends TransferBehavior {
    public CannotTransferBehavior(Account account) {
        super(account);
    }

    @Override
    public void transfer(Account account, double amount) {
        System.out.println("Cannot transfer");
    }
}
