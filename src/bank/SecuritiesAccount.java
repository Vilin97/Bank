package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Currency;

public class SecuritiesAccount extends Account {
    private final double startingAmount;
    private Stocks<Stock> stocks;

    public SecuritiesAccount(String name, Transactions<Transaction> transactions, double balance, Currency currency, Customer customer, double startingAmount, Stocks stocks) {
        super(name, transactions, balance, currency, customer);
        this.withdrawBehavior = new CannotWithdrawBehavior(this);
        this.depositBehavior = new CannotDepositBehavior(this);
        this.transferBehavior = new UncheckedTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);

        this.startingAmount = startingAmount;
        this.stocks = stocks;
    }

    public SecuritiesAccount(String name, Currency currency, double startingAmount, Customer customer) {
        this(name,new Transactions(), 0, currency, customer, startingAmount, new Stocks());
    }

    public double getRealizedProfit() {
        return getBalance() - startingAmount;
    }

    public double getUnrealizedProfit(StockMarket stockMarket) {
        // disregards stocks for which there is no price
        double total = 0;
        for (Stock stock : stocks){
            if (stockMarket.getPrices().containsKey(stock.getName()))
            total += stockMarket.getPrices().get(stock.getName());
        }
        return Constants.exchangeUSDtoCurrency(getCurrency(), total) + getBalance() - startingAmount;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", startingAmount: " + startingAmount +
                ", stocks: " + stocks;
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

    @Override
    public JSONObject toJSON() {
        JSONObject accountObject = super.toJSON();
        JSONArray stocksObject = new JSONArray();
        for (Stock stock:getStocks()) {
            stocksObject.add(stock.toJSON());
        }
        accountObject.put("stocks", stocksObject);
        accountObject.put("startingAmount", startingAmount);
        return accountObject;
    }
}
