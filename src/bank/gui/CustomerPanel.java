package bank.gui;

import bank.Bank;
import bank.Customer;
import bank.Loan;
import bank.PendingLoan;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerPanel  extends EmitterPanel<String>{
    private TextPanel textDisplayArea;
    private JComboBox<Customer> customerComboBox;
    private JButton displayButton;
    private JRadioButton pendingLoansRadio;
    private JRadioButton currentLoansRadio;
    private JComboBox<Loan> loanComboBox;
    private JButton remindButton;
    private JButton approveLoanButton;
    private JButton denyLoanButton;
    private ButtonGroup buttonGroup;

    private Customer selectedCustomer;
    private Loan selectedLoan;

    public CustomerPanel() {
        textDisplayArea = new TextPanel();
        textDisplayArea.setBorder(BorderFactory.createTitledBorder("customer information"));

        // set up customer combo box
        customerComboBox = new JComboBox<>();
        DefaultComboBoxModel<Customer> model = new DefaultComboBoxModel<>();
        model.addAll(Bank.getCustomers());
        customerComboBox.setModel(model);
        if (customerComboBox.getItemCount() != 0) {
            customerComboBox.setSelectedIndex(0);
            selectedCustomer = (Customer) customerComboBox.getSelectedItem();
        }
        customerComboBox.setRenderer(new CustomerRenderer());
        customerComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCustomer = (Customer) customerComboBox.getSelectedItem();
            }
        });

        // set up display button
        displayButton = new JButton("Display info!");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedCustomer != null){
                    textDisplayArea.setText(selectedCustomer.toStringDetailed());
                    emit("Customer "+selectedCustomer.getCreds().getName()+" was looked up");
                }
                else {
                    textDisplayArea.setText("No such customer");
                }
            }
        });

        // set up the loans combo box
        loanComboBox = new JComboBox<>();
        loanComboBox.setRenderer(new LoanRenderer());
        loanComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLoan = (Loan) loanComboBox.getSelectedItem();
            }
        });

        // set up radio buttons
        pendingLoansRadio = new JRadioButton("pending loans");
        currentLoansRadio = new JRadioButton("loans");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(pendingLoansRadio);
        buttonGroup.add(currentLoansRadio);

        pendingLoansRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doPendingLoansRadio();
            }
        });

        currentLoansRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultComboBoxModel<Loan> model = new DefaultComboBoxModel<>();
                if (selectedCustomer != null){
                    model.addAll(selectedCustomer.getLoans());
                }
                loanComboBox.setModel(model);
                if (loanComboBox.getItemCount() > 0) loanComboBox.setSelectedIndex(0);
                remindButton.setVisible(true);
                approveLoanButton.setVisible(false);
                denyLoanButton.setVisible(false);
            }
        });

        // set up buttons
        remindButton = new JButton("Send a reminder!");
        remindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedLoan != null) {
                    emit("Customer "+selectedCustomer.getCreds().getName()+" was sent a reminder");
                }
            }
        });

        approveLoanButton = new JButton("Approve loan!");
        approveLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedLoan != null) {
                    Bank.getManager().approveLoan(selectedCustomer, (PendingLoan) selectedLoan);
                    emit("The loan of customer " + selectedCustomer.getCreds().getName() + " was approved");
                }
            }
        });

        denyLoanButton = new JButton("Deny loan!");
        denyLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedLoan != null) {
                    Bank.getManager().denyLoan(selectedCustomer, (PendingLoan) selectedLoan);
                    emit("The loan of customer " + selectedCustomer.getCreds().getName() + " was denied");
                }
            }
        });

        // set default selections
        pendingLoansRadio.setSelected(true);
        doPendingLoansRadio();

        // set up layout and border
        setUpLayout();
        setBorder(BorderFactory.createTitledBorder("Customer Panel"));
    }

    private void setUpLayout(){
        displayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(textDisplayArea);
        add(customerComboBox);
        add(displayButton);
        add(pendingLoansRadio);
        add(currentLoansRadio);
        pendingLoansRadio.setAlignmentX(Component.LEFT_ALIGNMENT);
        currentLoansRadio.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(loanComboBox);
        remindButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(remindButton);
        JPanel panel = new JPanel();
        panel.add(approveLoanButton);
        panel.add(denyLoanButton);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panel);
    }

    private void doPendingLoansRadio(){
        DefaultComboBoxModel<Loan> model = new DefaultComboBoxModel<>();
        if (selectedCustomer != null){
            model.addAll(selectedCustomer.getPendingLoans());
        }
        loanComboBox.setModel(model);
        if (loanComboBox.getItemCount() > 0) loanComboBox.setSelectedIndex(0);
        remindButton.setVisible(false);
        approveLoanButton.setVisible(true);
        denyLoanButton.setVisible(true);
    }

    static class CustomerRenderer extends BasicComboBoxRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
            if (value != null) {
                Customer customer = (Customer) value;
                String text = customer.getCreds().toString();
                if (index == -1) text = "(" + customer.getID() +") "+text;
                setText(text);
            } else setText("no customers");
            return this;
        }
    }
    static class LoanRenderer extends BasicComboBoxRenderer {
        public Component getListCellRendererComponent(
                JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
            if (value != null) {
                Loan loan = (Loan) value;
                String text = loan.getClass().getSimpleName() +" of " + -loan.getBalance()+" "+loan.getCurrency();
                if (index == -1) text = "(" + loan.getID() +") "+ text;
                setText(text);
            } else setText("no loans");
            return this;
        }
    }
}
