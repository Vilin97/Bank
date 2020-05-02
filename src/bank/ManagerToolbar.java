package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerToolbar extends JPanel implements ActionListener{
    private JButton customersButton;
    private JButton stocksButton;
    private ArrayList<StringListener> listeners;

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

    public void addListener(StringListener l){
        listeners.add(l);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == customersButton) {
            for (StringListener l:listeners) {
                l.receiveString(Bank.getCustomers().toString());
            }
        } else if (clicked == stocksButton){
            for (StringListener l:listeners) {
                 l.receiveString(Bank.getStockMarket().toString());
            }
        }
    }
}
