package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

//    Buy stock from a user
//    Sell a stock to the user -- done
//    Create a stock
//    Change price of a stock


public class StockMarket {
    private Stocks<Stock> stocks;
    private Map<String, Double> prices;

    public StockMarket(Stocks<Stock> stocks, Map<String, Double> prices) {
        this.stocks = stocks;
        this.prices = prices;
    }

    public StockMarket() {
        this(new Stocks<Stock>(), new HashMap<String, Double>());
    }

    public void buyStock(String name, SecuritiesAccount securitiesAccount){
        // buy stock with name == name from the securitiesAccount
        if (prices.containsKey(name) && securitiesAccount.getStocks().hasStock(name)){
            Stock stock = securitiesAccount.getStocks().getStock(name);
            securitiesAccount.receiveUSD(getStockPrice(stock));
            securitiesAccount.getStocks().remove(stock);
            stocks.add(stock);
            securitiesAccount.getTransactions().add(new TransactionSellStock(Bank.getCurrentDate(), getStockPrice(stock), securitiesAccount.getUser(), securitiesAccount, stock));
        }
    }

    public void sellStock(String name, SecuritiesAccount securitiesAccount){
        // sell stock with name == name to the securitiesAccount
        if (hasStock(name) && securitiesAccount.canBuy(prices.get(name)) ){
            Stock stock = stocks.getStock(name);
            securitiesAccount.payUSD(prices.get(name));
            securitiesAccount.getStock(stock);
            stocks.remove(stock);
            securitiesAccount.getTransactions().add(new TransactionBuyStock(Bank.getCurrentDate(), getStockPrice(stock), securitiesAccount.getUser(), securitiesAccount, stock));
        } else System.out.println("Cannot sell stock "+ name);
    }

    public boolean hasStock(String name){
        return stocks.hasStock(name) && prices.containsKey(name);
    }

    public double getStockPrice(String name) {
        return prices.get(name);
    }

    public double getStockPrice(Stock stock){
        return prices.get(stock.getName());
    }

    public void setStockPrice(String name, double price){
        prices.put(name, price);
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

    public Map<String, Integer> getStockCounts() {
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (Stock s:stocks) {
            if (counts.containsKey(s.getName())) counts.put(s.getName(), counts.get(s.getName()) + 1);
            else counts.put(s.getName(), 1);
        }
        return counts;
    }

    @Override
    public String toString() {
        return "StockMarket:\n" +
                "\n"+getStockCounts() +
                "\nprices:" + prices;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        JSONArray stocksObject = new JSONArray();
        for (Stock stock:getStocks()) {
            stocksObject.add(stock.toJSON());
        }
        jsonObject.put("stocks", stocksObject);
        jsonObject.put("prices", new JSONObject(prices));
        return jsonObject;
    }

    public static StockMarket fromJSON(JSONObject jsonObject) {
        JSONArray stocksArray = (JSONArray) jsonObject.get("stocks");
        Stocks<Stock> stocks = new Stocks();
        for (Object s:stocksArray) {
            stocks.add(Stock.fromJSON((JSONObject) s));
        }
        Map<String, Double> prices = (Map<String, Double>) jsonObject.get("prices");
        return new StockMarket(stocks,prices);
    }
}
