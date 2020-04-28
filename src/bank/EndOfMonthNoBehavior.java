package bank;

public class EndOfMonthNoBehavior extends EndOfMonthBehavior {
    public EndOfMonthNoBehavior(Account account) {
        super(account);
    }

    @Override
    public void doEndOfMonth() {
        return;
    }
}
