package bank;

public class EndOfMonthInterestBehavior extends EndOfMonthBehavior {
    public EndOfMonthInterestBehavior(Account account) {
        super(account);
    }

    @Override
    public void doEndOfMonth() {
        if (account.getBalanceUSD() > Constants.getSavingsAccountInterestThresholdInUSD()) {
            account.setBalance(account.getBalance()*(1 + Constants.getSavingsAccountInterestRate()));
        }
    }
}
