package bank.gui;

import bank.Bank;
import bank.Customer;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel  extends EmitterPanel<String>{
    private TextPanel textDisplayArea;
    private JComboBox<Customer> customerComboBox;
    private JButton displayButton;

    public CustomerPanel() {
        textDisplayArea = new TextPanel();
        textDisplayArea.setBorder(BorderFactory.createTitledBorder("customer information"));

        customerComboBox = new JComboBox<>();
        DefaultComboBoxModel<Customer> model = new DefaultComboBoxModel<>();
        model.addAll(Bank.getCustomers());
        customerComboBox.setModel(model);
        if (customerComboBox.getItemCount() != 0) customerComboBox.setSelectedIndex(0);
        customerComboBox.setRenderer(new CustomerRenderer());

        displayButton = new JButton("Display info!");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = (Customer) customerComboBox.getSelectedItem();
                if (customer != null){
                    textDisplayArea.setText(customer.toString());
                    emit("Customer "+customer.getCreds().getName()+" was looked up");
                }
                else {
                    textDisplayArea.setText("No such customer");
                }
            }
        });

        setLayout(new BorderLayout());
        add(textDisplayArea, BorderLayout.NORTH);
        add(customerComboBox, BorderLayout.CENTER);
        add(displayButton, BorderLayout.SOUTH);
        setBorder(BorderFactory.createTitledBorder("Customer Panel"));
    }

    static class CustomerRenderer extends BasicComboBoxRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
            if (value != null) {
                Customer customer = (Customer) value;
                setText( customer.getCreds().toString() );
            }
            if (index == -1) {
                Customer customer = (Customer)value;
                setText( "(" + customer.getID() +") "+ customer.getCreds());
            }
            return this;
        }
    }
}
