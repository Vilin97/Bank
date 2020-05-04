package bank.gui;

import bank.Bank;
import bank.Transaction;
import bank.Transactions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TransactionPanel extends EmitterPanel<String> {
    private TextPanel textDisplayArea;
    private JFormattedTextField dateField;
    private JButton dailyReportButton;

    public TransactionPanel() {
        super();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        dateField = new JFormattedTextField(df);
        dateField.setColumns(10);
        Date date = Date.from(Bank.getCurrentDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        dateField.setValue(date);

        textDisplayArea = new TextPanel();
        textDisplayArea.setBorder(BorderFactory.createTitledBorder("transactions"));
        dailyReportButton = new JButton("Get daily report!");

        dateField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH))) {
                    JOptionPane.showMessageDialog(null, "Must enter a valid date!");
                    e.consume();
                }
            }
        });

        dailyReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) dateField.getValue();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Transactions<Transaction> transactions = Bank.getManager().getTransactionsByTimePeriod(localDate);
                textDisplayArea.setText(transactions.toString());
                emit("Transactions for date "+localDate+" were looked up");
            }
        });

        setUpLayout();
        setBorder(BorderFactory.createTitledBorder("Transaction Panel"));
    }

    private void setUpLayout() {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new BorderLayout());
        // first row //
        add(textDisplayArea, BorderLayout.NORTH);

        // next row //
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("date:"));
        panel.add(dateField);
        add(panel, BorderLayout.CENTER);

        // next row //
        add(dailyReportButton, BorderLayout.SOUTH);
    }
}

