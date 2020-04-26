package bank;

public class CanDepositBehavior extends DepositBehavior {
    public CanDepositBehavior(Account account) {
        super(account);
    }

    @Override
    public void deposit(double amount) {
        double commission = amount*Constants.getDepositFeeFraction();
        if (amount >= 0){
            account.setBalance(account.getBalance() + amount - commission);
            account.getTransactions().add(new TransactionDeposit(Bank.getCurrentDate()));
            System.out.println((amount-commission)+" "+account.getCurrency() + " was deposited. Commission: "+
                    commission+" "+account.getCurrency());
            // TODO: give the manager the commission
        } else {
            System.out.println("Cannot deposit");
        }
    }
}
