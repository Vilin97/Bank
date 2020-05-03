package bank;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockPanel extends EmitterPanel<String> {
    private AddStockPanel addStockPanel;
    private SetStockPricePanel setStockPricePanel;
    private String message;

    public StockPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 100));

        addStockPanel = new AddStockPanel();
        setStockPricePanel = new SetStockPricePanel();
        listeners = new ArrayList<>();
        message = "?";

        addStockPanel.addListener(new Listener<String>(){
            @Override
            public void receive(List<String> strings) {
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

        add(addStockPanel, BorderLayout.NORTH);
        add(setStockPricePanel, BorderLayout.SOUTH);
        Border innerBorder = BorderFactory.createTitledBorder("Stock Management");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
