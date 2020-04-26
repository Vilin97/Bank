package bank;

public class CheckedTransferBehavior extends TransferBehavior {
    public CheckedTransferBehavior(Account account) {
        super(account);
    }

    @Override
    public void transfer(Account account, double amount) {
        // TODO: check and if approved, transfer
    }
}
