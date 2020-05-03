package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class ManagerToolbar extends EmitterPanel<String> implements ActionListener {
    private JButton customersButton;
    private JButton stocksButton;

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


    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == customersButton) {
            emit(Bank.getCustomers().toString());
        } else if (clicked == stocksButton){
            emit(Bank.getStockMarket().toString());
        }
    }
}
