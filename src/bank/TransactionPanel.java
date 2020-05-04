package bank;

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
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        dateField = new JFormattedTextField(df);
        dateField.setColumns(10);
        Date date = Date.from(Bank.getCurrentDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        dateField.setValue(date);

        textDisplayArea = new TextPanel();
        textDisplayArea.setBorder(BorderFactory.createTitledBorder("transactions"));
        textDisplayArea.setPreferredSize(new Dimension(300, 200));
        dailyReportButton = new JButton("Get daily report!");

        dateField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH))) {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid");
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
            }
        });

        setUpLayout();
    }

    private void setUpLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;
        Insets insets = new Insets(0,0,0,5);
        Insets noInsets = new Insets(0,0,0,0);

        // first row //
        gc.gridy = 0;
        gc.weighty = 0.5;

        gc.insets = insets;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(textDisplayArea, gc);

        // next row //
        gc.gridy ++;
        gc.weighty = 0.5;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("date:"), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(dateField, gc);

        // next row //
        gc.gridy ++;

        gc.weighty = 0.5;
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(dailyReportButton, gc);
    }
}

