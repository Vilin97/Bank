package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ManagerToolbar extends JPanel implements ActionListener, hasStringListeners {
    private JButton customersButton;
    private JButton stocksButton;
    private ArrayList<StringsListener> listeners;

    public ManagerToolbar() {
        this.customersButton = new JButton("Customers");
        this.stocksButton = new JButton("Stock Market");
        this.listeners = new ArrayList<>();
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(customersButton);
        add(stocksButton);
        customersButton.addActionListener(this);
        stocksButton.addActionListener(this);

        setBorder(BorderFactory.createBevelBorder(10));
    }

    public void addListener(StringsListener l){
        listeners.add(l);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == customersButton) {
            for (StringsListener l:listeners) {
                l.receiveStrings(Collections.singletonList(Bank.getCustomers().toString()));
            }
        } else if (clicked == stocksButton){
            for (StringsListener l:listeners) {
                 l.receiveStrings(Collections.singletonList(Bank.getStockMarket().toString()));
            }
        }
    }
}
