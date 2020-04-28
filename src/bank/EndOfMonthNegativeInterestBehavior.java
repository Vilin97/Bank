package bank;

public class EndOfMonthNegativeInterestBehavior extends EndOfMonthBehavior {
    public EndOfMonthNegativeInterestBehavior(Account account) {
        super(account);
    }

    @Override
    public void doEndOfMonth() {
        if (account.getBalanceUSD() < 0){
            account.setBalance(account.getBalance()*(1 + Constants.getLoanInterestRate()));
        }
    }
}
