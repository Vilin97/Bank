package bank.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanel extends JPanel{
    private JTextArea textArea;

    public TextPanel() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        setPreferredSize(new Dimension(200, 200));
    }

    public void appendText(String s){
        textArea.append(s);
    }

    public void setText(String s){
        textArea.setText(s);
    }
}
