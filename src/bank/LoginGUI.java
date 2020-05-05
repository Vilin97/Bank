package bank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginGUI extends javax.swing.JFrame {

    private JPanel mainPanel;
    private JLabel usernameTextBox;
    private JLabel passwordTextBox;
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JButton signUpButton;
    private JButton loginButton;
    private JTextField firstNameInput;
    private JTextField lastNameInput;
    private JPasswordField passWordInput;
    private JTextField unameInput;

    public LoginGUI() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // take the parameters that the user has entered and create an account
                String firstName = firstNameInput.getText();
                String lastname = lastNameInput.getText();
                String password = String.valueOf(passWordInput.getPassword());
                String username = unameInput.getText();

                Customer newCustomer = bank.Customer.createCustomer(firstName, lastname, username, password);
                // give a new user a savings account
                //newCustomer.createSavingsAccount("Savings", "USD");

                // open the customer GUI with this user
                Manager temp = bank.Manager.createManager("Mr.", "Monopoly", "mrMono", "12345");
                Customers customersForBank = new Customers();
                customersForBank.add(newCustomer);
                Bank bank = new Bank(customersForBank, temp);
                LocalDate currentDate = LocalDate.now();
                Bank.setCurrentDate(currentDate);
                CustomerGUI customerGUI = new CustomerGUI(newCustomer, bank);
                customerGUI.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame loginFrame = new JFrame("LoginGUI");
        loginFrame.setContentPane(new LoginGUI().mainPanel);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }
}
