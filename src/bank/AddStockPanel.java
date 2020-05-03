package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class AddStockPanel extends JPanel implements ActionListener, hasStringListeners {
    private JTextField priceField;
    private JTextField nameField;
    private JTextField numberOfStocksField;
    private JButton addStockButton;
    private Collection<StringsListener> listeners;

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
        gc.gridy = 1;

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
        add(new Label("price:"), gc);

        gc.insets = noInsets;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(priceField, gc);

        // Third row //
        gc.gridy ++;

        gc.insets = insets;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new Label("number:"), gc);

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
        ArrayList<String> emit = new ArrayList<>();
        emit.add(nameField.getText());
        emit.add(priceField.getText());
        emit.add(numberOfStocksField.getText());
        for (StringsListener l:listeners
        ) {
            l.receiveStrings(emit);
        }
    }

    public void addListener(StringsListener l){
        listeners.add(l);
    }

    public JButton getAddStockButton() {
        return addStockButton;
    }

    public void setAddStockButton(JButton addStockButton) {
        this.addStockButton = addStockButton;
    }
}
