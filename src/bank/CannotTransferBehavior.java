package bank;

public class CannotTransferBehavior extends TransferBehavior {
    public CannotTransferBehavior(Account account) {
        super(account);
    }

    @Override
    public void transfer(Account account, double amount) {
        System.out.println("Cannot transfer");
    }

    @Override
    public void forceTransfer(Account o, double amount){
        // transfer the funds even if the balance is not high enough, and the account does not support transfer
        double commission = amount * Constants.getTransferBetweenAccountsFeeFraction();
        account.setBalance(account.getBalance() - amount - commission);
        double exchanged = Constants.exchangeCurrency(account.currency,o.currency,amount);
        o.setBalance(o.getBalance() + exchanged);
        account.getTransactions().add(new TransactionTransferOut(Bank.getCurrentDate(), amount));
        o.getTransactions().add(new TransactionTransferIn(Bank.getCurrentDate(), amount));
        System.out.println(amount+" "+account.getCurrency() + " was force-transferred. Commission: "+
                commission+" "+account.getCurrency());
        Bank.getManager().receiveMoney(account.getCurrency(), commission);
    }
}
