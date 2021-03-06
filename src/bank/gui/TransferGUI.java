/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.gui;

import bank.Account;
import bank.Accounts;
import bank.Customer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

/**
 *
 * @author adamstreich
 */
public class TransferGUI extends javax.swing.JFrame {

    /**
     * Creates new form TransferGUI
     */
    public TransferGUI(Account acc, Customer cr,CustomerGUI cgui) {
        initComponents();
        initActionListeners(acc,cr,cgui);
        initSpecial(cr);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        transferLabel = new javax.swing.JLabel();
        transferAccListjComboBox1 = new javax.swing.JComboBox<>();
        transferCompletejButton1 = new javax.swing.JButton();
        enterAmountjLabel1 = new javax.swing.JLabel();
        transferAmountjTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        transferLabel.setText("Select an Account to Tranfer to:");

        transferCompletejButton1.setText("Complete Transfer");

        enterAmountjLabel1.setText("Enter Amount you wish to transfer:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(enterAmountjLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(transferAmountjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(transferCompletejButton1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(transferLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(transferAccListjComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterAmountjLabel1)
                    .addComponent(transferAmountjTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferLabel)
                    .addComponent(transferAccListjComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transferCompletejButton1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initActionListeners(Account acc, Customer user, CustomerGUI gui){
        
        transferCompletejButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferComplete(acc, user, gui);
            }
                
        });
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed transfer window");
                e.getWindow().dispose();
                gui.updateAllDsipAndTH(user);
                gui.setVisible(true);
                
            }
        });
    }
    
    
    private void initSpecial(Customer user){
        populateAccList(user);
    }
    
    private void populateAccList(Customer user){
        Accounts all = user.getAllAccounts();
        Iterator iter = all.iterator();
        while(iter.hasNext()){
            Account sa = (Account) iter.next();
            transferAccListjComboBox1.addItem(sa.getName()+ " "+sa.getID());
        }
        
    }
    
    private void transferComplete(Account acc,Customer user, CustomerGUI gui){
        //code to do the money transfer here
        int indexOfAcc = transferAccListjComboBox1.getSelectedIndex();
        Account acco = user.getAllAccounts().get(indexOfAcc);
        try{
            double inp = Double.parseDouble(transferAmountjTextField1.getText());
            System.out.println(inp + " will be taken from the current account.");
            acc.transfer(acco, inp);
        }catch(NumberFormatException ex){
            System.out.println(ex.getMessage());
            System.out.println("User tried to enter not a double");
        }
        this.dispose();
        gui.updateAllDsipAndTH(user);
        gui.setVisible(true);
        
    }
    
    public static void transferGUI(Account acc, Customer cr, CustomerGUI gui){
        gui.setVisible(false);
        TransferGUI temp;
        temp = new TransferGUI(acc,cr,gui);
        temp.setVisible(true);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel enterAmountjLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> transferAccListjComboBox1;
    private javax.swing.JTextField transferAmountjTextField1;
    private javax.swing.JButton transferCompletejButton1;
    private javax.swing.JLabel transferLabel;
    // End of variables declaration//GEN-END:variables
}
