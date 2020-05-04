package bank.gui;

import bank.Bank;
import bank.Listener;
import bank.gui.AddStockPanel;
import bank.gui.EmitterPanel;
import bank.gui.SetStockPricePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StockPanel extends EmitterPanel<String> {
    private AddStockPanel addStockPanel;
    private SetStockPricePanel setStockPricePanel;

    public StockPanel() {

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
                    setStockPricePanel.updateStockList();
                    message = "Added stock "+name;
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
                    double price = Double.parseDouble(strings.get(1));
                    Bank.getManager().changeStockPrice(name, price);
                    message = "Changed price of stock " + name;
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
