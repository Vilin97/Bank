package bank;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainManagerFrame extends JFrame {
    private ManagerToolbar toolbar;
    private TextPanel log;
    private StockPanel stockPanel;
    private TransactionPanel transactionPanel;

    public MainManagerFrame() {
        super("Manager Portal");

        toolbar = new ManagerToolbar();
        log = new TextPanel();
        log.setBorder(BorderFactory.createTitledBorder("log"));
        log.setPreferredSize(new Dimension(100, 100));
        stockPanel = new StockPanel();
        transactionPanel = new TransactionPanel();

        toolbar.addListener(new Listener<String>() {
            @Override
            public void receive(List<String> strings) {
                String ss = "";
                for (String s:strings) {
                    ss += s;
                }
                log.appendText(ss);
            }
        });

        stockPanel.addListener(new Listener<String>() {
            @Override
            public void receive(List<String> strings) {
                String ss = "";
                for (String s:strings) {
                    ss += s;
                }
                log.appendText(ss+"\n");
            }
        });

        setLayout(new BorderLayout());
        add(toolbar, BorderLayout.NORTH);
        add(transactionPanel, BorderLayout.EAST);
        add(stockPanel, BorderLayout.WEST);
        add(log, BorderLayout.SOUTH);

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
