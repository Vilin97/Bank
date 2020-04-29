package bank;

public abstract class TransferBehavior {
    protected Account account;

    public TransferBehavior(Account account) {
        this.account = account;
    }

    abstract public void transfer(Account account,double amount);

    public void forceTransfer(Account o, double amount){
        // transfer the funds no matter what. No commission here.
        double commission = amount * Constants.getTransferBetweenAccountsFeeFraction();
        doTransfer(o, amount, commission);
        System.out.println(amount+" "+account.getCurrency() + " was force-transferred.");
    }

    protected void doTransfer(Account o, double amount, double commission) {
        account.setBalance(account.getBalance() - amount - commission);
        double exchanged = Constants.exchangeCurrency(account.currency,o.currency,amount);
        o.setBalance(o.getBalance() + exchanged);
        account.getTransactions().add(new TransactionTransferOut(Bank.getCurrentDate(), amount));
        o.getTransactions().add(new TransactionTransferIn(Bank.getCurrentDate(), amount));
    }
}
