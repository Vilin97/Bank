package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SetStockPricePanel extends EmitterPanel<String> implements ActionListener {
    private JTextField priceField;
    private JComboBox<String> stocksList;
    private JButton setPriceButton;

    public SetStockPricePanel() {
        this.priceField = new JTextField(10);
        this.setPriceButton = new JButton("Set price!");
        this.listeners = new ArrayList<>();
        setPriceButton.addActionListener(this);
        this.stocksList = new JComboBox<>();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(Bank.getStockMarket().getPrices().keySet());
        stocksList.setModel(model);
        stocksList.setSelectedIndex(0);

        setUpLayout();
        setBorder(BorderFactory.createTitledBorder("Set stock price"));

    }

    private void setUpLayout(){
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;
        gc.insets = new Insets(0,0,0,5);

        // first row //
        gc.gridy = 0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("name:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(stocksList);

        // next row //
        gc.gridy ++;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new Label("price:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(priceField, gc);

        // next row //
        gc.gridy ++;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(setPriceButton, gc);

        setBorder(BorderFactory.createTitledBorder("Change price"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> ToEmit = new ArrayList<>();
        ToEmit.add((String) stocksList.getSelectedItem());
        ToEmit.add(priceField.getText());
        emit(ToEmit);
    }

    public JComboBox<String> getStocksList() {
        return stocksList;
    }

    public void setStocksList(JComboBox<String> stocksList) {
        this.stocksList = stocksList;
    }

    public void updateStockList(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addAll(Bank.getStockMarket().getPrices().keySet());
        stocksList.setModel(model);
        stocksList.setSelectedIndex(0);
    }
}
