package bank;

import java.util.Currency;

public class SecuritiesAccount extends Account {
    private final double startingAmount;
    private Stocks<Stock> stocks;

    public SecuritiesAccount(String name, Transactions transactions, double balance, Currency currency, double startingAmount, Stocks stocks) {
        super(name, transactions, balance, currency);
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CannotDepositBehavior(this);
        this.transferBehavior = new UncheckedTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);

        this.startingAmount = startingAmount;
        this.stocks = stocks;
    }

    public SecuritiesAccount(String name, Currency currency, double startingAmount) {
        this(name,new Transactions(), 0, currency, startingAmount, new Stocks());
    }

    public SecuritiesAccount(String name, Currency currency) {
        this(name, currency, 0);
    }

    public double getRealizedProfit() {
        return getBalance() - startingAmount;
    }

    public double getUnrealizedProfit() {
        // TODO
        return 0;
    }

    public boolean canBuy(double price){
        return getBalanceUSD() >= price;
    }

    public void getStock(Stock stock){
        stocks.add(stock);
    }

    public void payUSD(double amount) {
        setBalanceUSD(getBalanceUSD() - amount);
    }

    public void receiveUSD(double amount){
        setBalanceUSD(getBalanceUSD() + amount);
    }

    public double getStartingAmount() {
        return startingAmount;
    }

    public Stocks<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Stocks<Stock> stocks) {
        this.stocks = stocks;
    }
}
