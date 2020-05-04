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
            doTransfer(o, amount, commission);
            Bank.getManager().receiveMoney(account.getCurrency(), commission);
            System.out.println(amount+" "+account.getCurrency() + " was transferred. Commission: "+
                    commission+" "+account.getCurrency());
        }
    }


}
