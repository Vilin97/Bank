package bank.gui;

import bank.Bank;
import bank.StockMarket;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StockPanel extends EmitterPanel<String> {
    private AddStockPanel addStockPanel;
    private SetStockPricePanel setStockPricePanel;

    public StockPanel() {
        super();
        addStockPanel = new AddStockPanel();
        setStockPricePanel = new SetStockPricePanel();
        listeners = new ArrayList<>();

        addStockPanel.addListener(new Listener<String>(){
            @Override
            public void receive(List<String> strings) {
                String message;
                try {
                    String name = strings.get(0);
                    double price = Double.parseDouble(strings.get(1));
                    int number = Integer.parseInt(strings.get(2));
                    Bank.getManager().addNewStock(name, price, number);
                    StockMarket stockMarket = Bank.getStockMarket();
                    setStockPricePanel.updateStockList();
                    message = "Added " + number + " stocks "+name+" at price "+price;
                } catch (Exception e){
                    message = "Failed to add stock";
                }
                emit(message);
            }
        });

        setStockPricePanel.addListener(new Listener<String>() {
            @Override
            public void receive(List<String> strings) {
                String message;
                try {
                    String name = strings.get(0);
                    double currentPrice = Bank.getStockMarket().getStockPrice(name);
                    double price = Double.parseDouble(strings.get(1));
                    Bank.getManager().changeStockPrice(name, price);
                    message = "Changed price of stock " + name +" from " + currentPrice + " to "+price;
                } catch (Exception e){
                    message = "Failed to change price of stock";
                }
                emit(message);
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(addStockPanel);
        add(setStockPricePanel);
        setBorder(BorderFactory.createTitledBorder("Stock Panel"));
    }
}
