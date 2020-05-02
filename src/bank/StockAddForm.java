package bank;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class StockAddForm extends JPanel {
    private JTextField priceField;
    private JTextField nameField;
    private JTextField numberOfStocksField;
    private JLabel priceLabel;
    private JLabel nameLabel;
    private JLabel numberOfStocksLabel;
    private JButton addStockButton;

    public StockAddForm() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        priceField = new JTextField(10);
        nameField = new JTextField(10);
        numberOfStocksField = new JTextField(10);
        priceLabel = new JLabel("price:");
        nameLabel = new JLabel("name:");
        numberOfStocksLabel = new JLabel("number:");

        addStockButton = new JButton("Add stock!");
        addStockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: add input validation
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int number = Integer.parseInt(numberOfStocksField.getText());
                Bank.getManager().addNewStock(name, price, number);
            }
        });


        Border innerBorder = BorderFactory.createTitledBorder("Add a new stock");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;
        gc.weighty = 0.1;
        gc.insets = new Insets(0,0,0,5);
        // First row //
        gc.gridy = 0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        // Second row //
        gc.gridy = 1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(priceLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(priceField, gc);

        // Third row //
        gc.gridy = 2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(numberOfStocksLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(numberOfStocksField, gc);

        // Fourth row //
        gc.gridy = 3;

        gc.insets = new Insets(0,0,0,0);
        gc.weighty = 2;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(addStockButton, gc);
    }
}
