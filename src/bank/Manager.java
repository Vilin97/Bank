package bank;

import java.time.LocalDate;
import java.util.Currency;

import static bank.Credentials.createCredentials;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adamstreich
 */
public class Manager extends User {
    private ManagerAccount account;
    private Bank bank;

    public Manager(Credentials cd, ManagerAccount managerAccount, Bank bank) {
        super(cd);
        this.account = managerAccount;
        this.bank = bank;
    }

    public Manager(Credentials cd) {
        this(cd, new ManagerAccount(Currency.getInstance("USD")), new Bank());
        this.bank.setManager(this);
    }
    
    public static Manager createManager(String fn, String ln, String un, String pw){
        Manager rt;
        Credentials cr;
        try{
            cr = createCredentials(fn,ln,un,pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("Wrong credential specs");
        }
        rt = new Manager(cr);
        return rt;
    }

    public void moveTimeToDate(LocalDate date){
        int numMonths = date.getMonthValue() - Bank.getCurrentDate().getMonthValue() + 12*(date.getYear() - bank.getCurrentDate().getYear());
        if (Bank.getCurrentDate().compareTo(date) < 0 && numMonths > 0){
            Bank.setCurrentDate(date);
            for (Customer customer:bank.getCustomers()) {
                for (Account account:customer.getAllAccounts()) {
                    for (int i = 0; i < numMonths; i++) {
                        account.doEndOfMonth();
                    }
                }
            }
        }
    }

    @Override
    public boolean sudoUser() {
        return true;
    }

    public void approveLoan(Customer customer, PendingLoan pendingLoan){
        // approve the pending loan if enough funds
        if (account.getBalanceUSD() >= -pendingLoan.getBalanceUSD()){
            customer.getLoans().add(new Loan(pendingLoan));
            customer.getPendingLoans().remove(pendingLoan);
            account.changeBalanceBy(pendingLoan.getBalance());
        } else disapproveLoan(customer, pendingLoan);
    }

    public void disapproveLoan(Customer customer, PendingLoan pendingLoan){
        // disapprove the pending loan
        customer.getPendingLoans().remove(pendingLoan);
    }

    public void receiveMoney(Currency c, double amount){
        if (amount > 0){
            account.changeBalanceBy(Constants.exchangeCurrency(account.getCurrency(), getAccount().getCurrency(), amount));
        }
    }

    public ManagerAccount getAccount() {
        return account;
    }

    public void setAccount(ManagerAccount account) {
        this.account = account;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
