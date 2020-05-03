package bank;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StockPanel extends JPanel implements hasStringListeners {
    private AddStockPanel addStockPanel;
    private SetStockPricePanel setStockPricePanel;
    private Collection<StringsListener> listeners;
    private String message;

    public StockPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 100));

        addStockPanel = new AddStockPanel();
        setStockPricePanel = new SetStockPricePanel();
        listeners = new ArrayList<>();
        message = "?";

        addStockPanel.addListener(new StringsListener() {
            @Override
            public void receiveStrings(List<String> strings) {
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
                for (StringsListener l:listeners
                ) {
                    l.receiveStrings(Collections.singletonList(message));
                }
            }
        });

        setStockPricePanel.addListener(new StringsListener() {
            @Override
            public void receiveStrings(List<String> strings) {
                try {
                    String name = strings.get(0);
                    double price = Double.parseDouble(strings.get(1));
                    Bank.getManager().changeStockPrice(name, price);
                    message = "Changed price of stock " + name;
                } catch (Exception e){
                    message = "Failed to change price of stock";
                }
                for (StringsListener l:listeners
                ) {
                    l.receiveStrings(Collections.singletonList(message));
                }
            }
        });

        add(addStockPanel, BorderLayout.NORTH);
        add(setStockPricePanel, BorderLayout.SOUTH);
        Border innerBorder = BorderFactory.createTitledBorder("Stock Management");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

    @Override
    public void addListener(StringsListener listener) {
        listeners.add(listener);
    }
}
