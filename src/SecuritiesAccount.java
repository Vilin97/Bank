import java.util.Currency;

public class SecuritiesAccount extends Account {
    private double startingAmount;
    private Stocks<Stock> stocks;

    public SecuritiesAccount(String name, Transactions transactions, double balance, Currency currency, double startingAmount, Stocks stocks) {
        super(name, transactions, balance, currency);
        this.startingAmount = startingAmount;
        this.stocks = stocks;
    }

    public SecuritiesAccount(String name, Currency currency, double startingAmount) {
        super(name, currency);
        this.startingAmount = startingAmount;
    }

    public double getRealizedProfit() {
        return getBalance() - startingAmount;
    }

    public double getUnrealizedProfit() {
        // TODO
        return 0;
    }
}
