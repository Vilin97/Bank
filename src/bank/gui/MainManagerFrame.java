package bank.gui;

import bank.Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static bank.JSONTools.writeBank;
//import static bank.JSONTools.writeUserData;

public class MainManagerFrame extends JFrame {
    private ManagerToolbar toolbar;
    private TextPanel log;
    private StockPanel stockPanel;
    private TransactionPanel transactionPanel;
    private CustomerPanel customerPanel;
    private TimePanel timePanel;
    private JPanel panels;

    public MainManagerFrame() {
        super("Manager Portal");

        toolbar = new ManagerToolbar();
        log = new TextPanel();
        log.setBorder(BorderFactory.createTitledBorder("log"));
        log.setPreferredSize(new Dimension(100, 100));
        int panelHeight = 400;
        stockPanel = new StockPanel();
        stockPanel.setPreferredSize(new Dimension(200, panelHeight));
        transactionPanel = new TransactionPanel();
        transactionPanel.setPreferredSize(new Dimension(200, panelHeight));
        customerPanel = new CustomerPanel();
        customerPanel.setPreferredSize(new Dimension(300, panelHeight));
        timePanel = new TimePanel();
        timePanel.setPreferredSize(new Dimension(300, panelHeight));
        panels = new JPanel();
        panels.setLayout(new FlowLayout());
        panels.add(stockPanel);
        panels.add(transactionPanel);
        panels.add(customerPanel);
        panels.add(timePanel);

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
        timePanel.addListener(logListener);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closed");
                writeBank();
                //writeUserData(Bank.getManager());
                e.getWindow().dispose();
            }
        });

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(panels, BorderLayout.CENTER);
        add(log, BorderLayout.SOUTH);

        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
