package bank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerGUI {
    private JPanel mainPanel;
    private JButton customersButton;
    private JButton stocksButton;
    private JButton button2;
    private JButton button3;

    public ManagerGUI() {

        customersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Manager Portal");
        jFrame.setContentPane(new ManagerGUI().mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
