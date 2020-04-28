package bank;

public abstract class EndOfMonthBehavior {
    Account account;

    public EndOfMonthBehavior(Account account) {
        this.account = account;
    }

    public abstract void doEndOfMonth();
}
