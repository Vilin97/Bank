package bank;

import java.util.Map;

//    Buy stock from a customer
//    Sell a stock to the customer -- done
//    Create a stock
//    Change price of a stock


public class StockMarket {
    private Stocks<Stock> stocks;
    private Map<String, Double> prices;

    public StockMarket(Stocks<Stock> stocks, Map<String, Double> prices) {
        this.stocks = stocks;
        this.prices = prices;
    }

    public void buyStock(String name, SecuritiesAccount securitiesAccount){
        // buy stock with name == name from the securitiesAccount
        if (prices.containsKey(name) && securitiesAccount.getStocks().hasStock(name)){
            Stock stock = securitiesAccount.getStocks().getStock(name);
            securitiesAccount.receiveUSD(getStockPrice(stock));
            securitiesAccount.getStocks().remove(stock);
            stocks.add(stock);
            securitiesAccount.getTransactions().add(new TransactionSellStock(Bank.getCurrentDate(), getStockPrice(stock), stock));
        }
    }

    public void sellStock(String name, SecuritiesAccount securitiesAccount){
        // sell stock with name == name to the securitiesAccount
        if (hasStock(name) && securitiesAccount.canBuy(prices.get(name)) ){
            Stock stock = stocks.getStock(name);
            securitiesAccount.payUSD(prices.get(name));
            securitiesAccount.getStock(stock);
            stocks.remove(stock);
            securitiesAccount.getTransactions().add(new TransactionBuyStock(Bank.getCurrentDate(), getStockPrice(stock), stock));
        } else System.out.println("Cannot sell stock "+ name);
    }

    private boolean hasStock(String name){
        return stocks.hasStock(name) && prices.containsKey(name);
    }

    private double getStockPrice(String name) {
        return prices.get(name);
    }

    private double getStockPrice(Stock stock){
        return prices.get(stock.getName());
    }


    public Stocks<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Stocks<Stock> stocks) {
        this.stocks = stocks;
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Double> prices) {
        this.prices = prices;
    }
}
