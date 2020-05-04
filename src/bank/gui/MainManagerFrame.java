package bank.gui;

import bank.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainManagerFrame extends JFrame {
    private ManagerToolbar toolbar;
    private TextPanel log;
    private StockPanel stockPanel;
    private TransactionPanel transactionPanel;
    private CustomerPanel customerPanel;
    private JPanel panels;

    public MainManagerFrame() {
        super("Manager Portal");

        toolbar = new ManagerToolbar();
        log = new TextPanel();
        log.setBorder(BorderFactory.createTitledBorder("log"));
        log.setPreferredSize(new Dimension(100, 100));
        int panelHeight = 350;
        stockPanel = new StockPanel();
        stockPanel.setPreferredSize(new Dimension(200, panelHeight));
        transactionPanel = new TransactionPanel();
        transactionPanel.setPreferredSize(new Dimension(200, panelHeight));
        customerPanel = new CustomerPanel();
        customerPanel.setPreferredSize(new Dimension(300, 600));
        panels = new JPanel();
        panels.setLayout(new FlowLayout());
        panels.add(stockPanel);
        panels.add(transactionPanel);
        panels.add(customerPanel);

        Listener<String> logListener = new Listener<String>() {
            @Override
            public void receive(List<String> strings) {
                StringBuilder ss = new StringBuilder();
                for (String s:strings) {
                    ss.append(s);
                }
                log.appendText(ss.toString()+"\n");
            }
        };

        toolbar.addListener(logListener);
        stockPanel.addListener(logListener);
        transactionPanel.addListener(logListener);
        customerPanel.addListener(logListener);

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(panels, BorderLayout.CENTER);
        add(log, BorderLayout.SOUTH);

        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
