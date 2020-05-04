package bank.gui;

import bank.gui.EmitterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddStockPanel extends EmitterPanel<String> implements ActionListener {
    private JTextField priceField;
    private JTextField nameField;
    private JTextField numberOfStocksField;
    private JButton addStockButton;

    public AddStockPanel() {
        this.priceField = new JTextField(10);
        this.nameField = new JTextField(10);
        this.numberOfStocksField = new JTextField(10);
        this.addStockButton = new JButton("Add stock!");
        addStockButton.addActionListener(this);
        this.listeners = new ArrayList<>();
        setUpLayout();
        setBorder(BorderFactory.createTitledBorder("Add new stock"));

    }

    private void setUpLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;
        Insets insets = new Insets(0,0,0,5);
        Insets noInsets = new Insets(0,0,0,0);

        // first row //
        gc.gridy = 0;

        gc.insets = insets;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("name:"), gc);

        gc.insets = noInsets;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        // next row //
        gc.gridy ++;

        gc.insets = insets;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("price:"), gc);

        gc.insets = noInsets;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(priceField, gc);

        // Third row //
        gc.gridy ++;

        gc.insets = insets;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("number:"), gc);

        gc.insets = noInsets;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(numberOfStocksField, gc);

        // Fourth row //
        gc.gridy ++;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(addStockButton, gc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> toEmit = new ArrayList<>();
        toEmit.add(nameField.getText());
        toEmit.add(priceField.getText());
        toEmit.add(numberOfStocksField.getText());
        emit(toEmit);
    }

    public JButton getAddStockButton() {
        return addStockButton;
    }

    public void setAddStockButton(JButton addStockButton) {
        this.addStockButton = addStockButton;
    }
}
