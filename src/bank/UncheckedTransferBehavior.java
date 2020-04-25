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
        if (account.getBalance() < amount) {
            System.out.println("Not enough funds");
        } else {
            account.setBalance(account.getBalance() - amount);
            double exchanged = Constants.exchangeCurrency(account.currency,o.currency,amount-commission);
            o.setBalance(account.getBalance() + exchanged);
            account.getTransactions().add(new TransactionTransferOut(Bank.getCurrentDate()));
            o.getTransactions().add(new TransactionTransferIn(Bank.getCurrentDate()));
            System.out.println(amount-commission+" "+account.getCurrency() + " was transferred. Commission: "+
                    commission+" "+account.getCurrency());
            // TODO: give the manager the commission
        }
    }
}
