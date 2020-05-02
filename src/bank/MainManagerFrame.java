package bank;

import javax.swing.*;
import java.awt.*;

public class MainManagerFrame extends JFrame {
    private ManagerToolbar toolbar;
    private TextPanel textPanel;
    private StockAddForm stockForm;

    public MainManagerFrame() {
        super("Manager Portal");
        setLayout(new BorderLayout());

        toolbar = new ManagerToolbar();
        textPanel = new TextPanel();
        stockForm = new StockAddForm();

        toolbar.addListener(new StringListener() {
            public void receiveString(String string) {
                textPanel.setText(string);
            }
        });

        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(stockForm, BorderLayout.WEST);

        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
