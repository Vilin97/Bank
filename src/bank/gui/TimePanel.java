package bank.gui;

import bank.Bank;
import bank.Loan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TimePanel extends EmitterPanel<String> {
    private JFormattedTextField currentDateField;
    private JFormattedTextField futureDateField;
    private JButton moveTimeButton;

    public TimePanel() {
        super();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        currentDateField = new JFormattedTextField(df);
        currentDateField.setColumns(10);
        Date date = Date.from(Bank.getCurrentDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        currentDateField.setValue(date);
        currentDateField.setEditable(false);

        futureDateField = new JFormattedTextField(df);
        futureDateField.setColumns(10);
        LocalDate localDate = Bank.getCurrentDate();
        localDate = localDate.plusDays(1);
        date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        futureDateField.setValue(date);

        futureDateField.addKeyListener(new KeyAdapter() {
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

        moveTimeButton = new JButton("Move time forward!");
        moveTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date date = (Date) futureDateField.getValue();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (localDate.compareTo(Bank.getCurrentDate()) > 0) {
                    Bank.getManager().moveTimeToDate(localDate);
                    emit("Moved time to "+localDate);
                    localDate = localDate.plusDays(1);
                    date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                    futureDateField.setValue(date);
                }
            }
        });

        setUpLayout();
        setBorder(BorderFactory.createTitledBorder("Time panel"));
    }

    private void setUpLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.add(new JLabel("current date: "));
        panel.add(currentDateField);
        add(panel);

        panel = new JPanel();
        panel.add(new JLabel("future date: "));
        panel.add(futureDateField);
        add(panel);

        add(moveTimeButton);
    }
}
