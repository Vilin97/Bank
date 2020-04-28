package bank;

public class UncheckedTransferBehavior extends TransferBehavior {
    // unchecked transfer
    public UncheckedTransferBehavior(Account account) {
        super(account);
    }

    @Override
    public void transfer(Account o, double amount) {
        // transfer amount to o
        double commission = amount * Constants.getTransferBetweenAccountsFeeFraction();
        if (account.getBalance() < amount + commission) {
            System.out.println("Not enough funds");
        } else {
            account.setBalance(account.getBalance() - amount - commission);
            double exchanged = Constants.exchangeCurrency(account.currency,o.currency,amount);
            o.setBalance(o.getBalance() + exchanged);
            account.getTransactions().add(new TransactionTransferOut(Bank.getCurrentDate(), amount));
            o.getTransactions().add(new TransactionTransferIn(Bank.getCurrentDate(), amount));
            System.out.println(amount+" "+account.getCurrency() + " was transferred. Commission: "+
                    commission+" "+account.getCurrency());
            Bank.getManager().receiveMoney(account.getCurrency(), commission);
        }
    }

    public void forceTransfer(Account o, double amount){
        // transfer the funds even if the balance is not high enough
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
