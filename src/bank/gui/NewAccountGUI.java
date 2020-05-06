/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.gui;

import bank.Customer;
import bank.SavingsAccount;
import static bank.Constants.curencies;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;

/**
 *
 * @author adamstreich
 */
public class NewAccountGUI extends javax.swing.JFrame {

    /**
     * Creates new form NewAccountGUI
     */
    public NewAccountGUI(Customer user, CustomerGUI maingui) {
        initComponents();
        initActionListeners(user, maingui);
        initCustom();
        
    }
    
    private void initCustom(){
        chekjPanel3.setVisible(false);
        secjPanel4.setVisible(false);
        fillCurrency();
    }
    private void initActionListeners(Customer user, CustomerGUI maingui){
        
        createChekAccjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectChekAcc(user, maingui);
            }
                
        });
        
        createSavAccjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSavAcc(user, maingui);
            }
                
        });
        
        savCreateAccjButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSavAcc(user, maingui);
            }
                
        });
        
        chekcreateAccjButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChekAcc(user, maingui);
            }
                
        });
        
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSecAcc(user, maingui);
            }
                
        });
        
        secCreateAccjButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSecAcc(user, maingui);
            }
                
        });
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.out.println("Closed transfer window");
                e.getWindow().dispose();
                maingui.updateAllDsipAndTH(user);
                maingui.setVisible(true);
                
            }
        });
    }
    
    public void fillCurrency(){
        for(int c = 0; c < curencies.size(); c++){
            System.out.println(curencies.get(c));
            secCurrencyjComboBox.addItem(curencies.get(c));
            chekcurrencyjComboBox1.addItem(curencies.get(c));
        }
    }
    public static void newAccGUI(Customer cr, CustomerGUI gui){
        gui.setVisible(false);
        NewAccountGUI temp;
        temp = new NewAccountGUI(cr,gui);
        temp.setVisible(true);
    }
        
        
    public void selectSavAcc(Customer user, CustomerGUI gui){
        jPanel1.setVisible(false);
        secjPanel4.setVisible(true);
        
    }
    public void selectChekAcc(Customer user, CustomerGUI gui){
        jPanel1.setVisible(false);
        chekjPanel3.setVisible(true);
        
    }
    
    public void selectSecAcc(Customer user, CustomerGUI gui){
        jPanel1.setVisible(false);
        secjPanel4.setVisible(true);
        
    }
    
    public void createSavAcc(Customer user, CustomerGUI gui){
        
        user.openSavingsAccount(savAccountNamejTextField2.getText(), savCurrencyjComboBox3.getSelectedItem().toString());
        this.dispose();
        gui.setVisible(true);
        gui.updateSavingsAccList(user);
    }
    public void createChekAcc(Customer user, CustomerGUI gui){
        
        user.openCheckingAccount(chekaccountNamejTextField.getText(), chekcurrencyjComboBox1.getSelectedItem().toString());
        this.dispose();
        gui.setVisible(true);
        gui.updateCheckingAccList(user);
    }
    public void createSecAcc(Customer user, CustomerGUI gui){
        double amtr = Double.parseDouble(ammountToTransferjTextField1.getText());
        int indexOfAcc = accToTransferFromjComboBox1.getSelectedIndex();
        if(indexOfAcc != -1){
            SavingsAccount acc = user.getSavingsAccounts().get(indexOfAcc);
            user.openSecuritiesAccount(secAccountNamejTextField.getText(), secCurrencyjComboBox.getSelectedItem().toString(), acc, amtr);
        }
       
        this.dispose();
        gui.setVisible(true);
        //gui.updateCheckingAccList(user);
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
        jLabel1 = new javax.swing.JLabel();
        createChekAccjButton = new javax.swing.JButton();
        createSavAccjButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        chekjPanel3 = new javax.swing.JPanel();
        chekappjLabel = new javax.swing.JLabel();
        chekAccNamejLabel = new javax.swing.JLabel();
        ChekAcccurrencyjLabel = new javax.swing.JLabel();
        chekcreateAccjButton = new javax.swing.JButton();
        chekaccountNamejTextField = new javax.swing.JTextField();
        chekcurrencyjComboBox1 = new javax.swing.JComboBox<>();
        secjPanel4 = new javax.swing.JPanel();
        savAppjLabel1 = new javax.swing.JLabel();
        savAccNamejLabel1 = new javax.swing.JLabel();
        savAcccurrencyjLabel1 = new javax.swing.JLabel();
        secCreateAccjButton1 = new javax.swing.JButton();
        secAccountNamejTextField = new javax.swing.JTextField();
        secCurrencyjComboBox = new javax.swing.JComboBox<>();
        accToTransferFromjComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ammountToTransferjTextField1 = new javax.swing.JTextField();
        savjPanel5 = new javax.swing.JPanel();
        savAppjLabel2 = new javax.swing.JLabel();
        savAccNamejLabel2 = new javax.swing.JLabel();
        savAcccurrencyjLabel2 = new javax.swing.JLabel();
        savCreateAccjButton2 = new javax.swing.JButton();
        savAccountNamejTextField2 = new javax.swing.JTextField();
        savCurrencyjComboBox3 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("New Account Application");

        createChekAccjButton.setText("Checking Account");

        createSavAccjButton.setText("Savings Account");

        jButton3.setText("Securities Account");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(createChekAccjButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addComponent(createSavAccjButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(createChekAccjButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createSavAccjButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(357, Short.MAX_VALUE))
        );

        chekappjLabel.setText("Application");

        chekAccNamejLabel.setText("Account Name:");

        ChekAcccurrencyjLabel.setText("Currency:");

        chekcreateAccjButton.setText("Create");

        javax.swing.GroupLayout chekjPanel3Layout = new javax.swing.GroupLayout(chekjPanel3);
        chekjPanel3.setLayout(chekjPanel3Layout);
        chekjPanel3Layout.setHorizontalGroup(
            chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chekjPanel3Layout.createSequentialGroup()
                .addGroup(chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chekcreateAccjButton)
                    .addGroup(chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(chekjPanel3Layout.createSequentialGroup()
                            .addGap(170, 170, 170)
                            .addComponent(chekappjLabel))
                        .addGroup(chekjPanel3Layout.createSequentialGroup()
                            .addGap(71, 71, 71)
                            .addGroup(chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ChekAcccurrencyjLabel)
                                .addComponent(chekAccNamejLabel)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chekaccountNamejTextField)
                    .addComponent(chekcurrencyjComboBox1, 0, 92, Short.MAX_VALUE))
                .addGap(73, 73, 73))
        );
        chekjPanel3Layout.setVerticalGroup(
            chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chekjPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chekappjLabel)
                .addGap(8, 8, 8)
                .addGroup(chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chekAccNamejLabel)
                    .addComponent(chekaccountNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chekjPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(chekjPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ChekAcccurrencyjLabel)
                            .addComponent(chekcurrencyjComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(chekjPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(chekcreateAccjButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        savAppjLabel1.setText("Application");

        savAccNamejLabel1.setText("Account Name:");

        savAcccurrencyjLabel1.setText("Currency:");

        secCreateAccjButton1.setText("Create");

        jLabel2.setText("Account to transfer from:");

        jLabel3.setText("Amount to transfer:");

        javax.swing.GroupLayout secjPanel4Layout = new javax.swing.GroupLayout(secjPanel4);
        secjPanel4.setLayout(secjPanel4Layout);
        secjPanel4Layout.setHorizontalGroup(
            secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secjPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(savAppjLabel1)
                    .addGroup(secjPanel4Layout.createSequentialGroup()
                        .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(secjPanel4Layout.createSequentialGroup()
                                    .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(savAccNamejLabel1)
                                        .addComponent(savAcccurrencyjLabel1))
                                    .addGap(82, 82, 82))
                                .addGroup(secjPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)))
                            .addGroup(secjPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(54, 54, 54)))
                        .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(secCurrencyjComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(secAccountNamejTextField)
                            .addComponent(accToTransferFromjComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ammountToTransferjTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, secjPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(secCreateAccjButton1)
                .addContainerGap())
        );
        secjPanel4Layout.setVerticalGroup(
            secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secjPanel4Layout.createSequentialGroup()
                .addComponent(savAppjLabel1)
                .addGap(14, 14, 14)
                .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savAccNamejLabel1)
                    .addComponent(secAccountNamejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savAcccurrencyjLabel1)
                    .addComponent(secCurrencyjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accToTransferFromjComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(secCreateAccjButton1)
                    .addGroup(secjPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(ammountToTransferjTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        savAppjLabel2.setText("Application");

        savAccNamejLabel2.setText("Account Name:");

        savAcccurrencyjLabel2.setText("Currency:");

        savCreateAccjButton2.setText("Create");

        javax.swing.GroupLayout savjPanel5Layout = new javax.swing.GroupLayout(savjPanel5);
        savjPanel5.setLayout(savjPanel5Layout);
        savjPanel5Layout.setHorizontalGroup(
            savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(savjPanel5Layout.createSequentialGroup()
                .addGroup(savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(savCreateAccjButton2)
                    .addGroup(savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(savjPanel5Layout.createSequentialGroup()
                            .addGap(170, 170, 170)
                            .addComponent(savAppjLabel2))
                        .addGroup(savjPanel5Layout.createSequentialGroup()
                            .addGap(71, 71, 71)
                            .addGroup(savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(savAcccurrencyjLabel2)
                                .addComponent(savAccNamejLabel2)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(savAccountNamejTextField2)
                    .addComponent(savCurrencyjComboBox3, 0, 92, Short.MAX_VALUE))
                .addGap(73, 73, 73))
        );
        savjPanel5Layout.setVerticalGroup(
            savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(savjPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(savAppjLabel2)
                .addGap(8, 8, 8)
                .addGroup(savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(savAccNamejLabel2)
                    .addComponent(savAccountNamejTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(savjPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(savjPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(savAcccurrencyjLabel2)
                            .addComponent(savCurrencyjComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(savjPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(savCreateAccjButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chekjPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(secjPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(savjPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(346, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chekjPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(secjPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(161, 161, 161)
                    .addComponent(savjPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(217, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new NewAccountGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChekAcccurrencyjLabel;
    private javax.swing.JComboBox<String> accToTransferFromjComboBox1;
    private javax.swing.JTextField ammountToTransferjTextField1;
    private javax.swing.JLabel chekAccNamejLabel;
    private javax.swing.JTextField chekaccountNamejTextField;
    private javax.swing.JLabel chekappjLabel;
    private javax.swing.JButton chekcreateAccjButton;
    private javax.swing.JComboBox<String> chekcurrencyjComboBox1;
    private javax.swing.JPanel chekjPanel3;
    private javax.swing.JButton createChekAccjButton;
    private javax.swing.JButton createSavAccjButton;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel savAccNamejLabel1;
    private javax.swing.JLabel savAccNamejLabel2;
    private javax.swing.JLabel savAcccurrencyjLabel1;
    private javax.swing.JLabel savAcccurrencyjLabel2;
    private javax.swing.JTextField savAccountNamejTextField2;
    private javax.swing.JLabel savAppjLabel1;
    private javax.swing.JLabel savAppjLabel2;
    private javax.swing.JButton savCreateAccjButton2;
    private javax.swing.JComboBox<String> savCurrencyjComboBox3;
    private javax.swing.JPanel savjPanel5;
    private javax.swing.JTextField secAccountNamejTextField;
    private javax.swing.JButton secCreateAccjButton1;
    private javax.swing.JComboBox<String> secCurrencyjComboBox;
    private javax.swing.JPanel secjPanel4;
    // End of variables declaration//GEN-END:variables
}
