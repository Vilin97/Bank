package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    public Manager(Credentials cred, int ID, ManagerAccount account) {
        super(cred, ID);
        this.account = account;
    }

    public Manager(Credentials cd, ManagerAccount managerAccount) {
        super(cd);
        this.account = managerAccount;
    }

    public Manager(Credentials cred, int ID) {
        this(cred, ID, new ManagerAccount(Currency.getInstance("USD")));
    }

    public Manager(Credentials cd) {
        this(cd, new ManagerAccount(Currency.getInstance("USD")));
        Bank.setManager(this);
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

    public void addNewStock(String name, double price, int numberOfStocks){
        if (!Bank.getStockMarket().hasStock(name)) {
            changeStockPrice(name, price);
            Stocks<Stock> stocks = Bank.getStockMarket().getStocks();
            for (int i = 0; i < numberOfStocks; i++) {
                stocks.add(new Stock(name));
            }
        }
    }

    public void changeStockPrice(String name, double price){
        Bank.getStockMarket().setStockPrice(name, price);
    }

    public void moveTimeToDate(LocalDate date){
        int numMonths = date.getMonthValue() - Bank.getCurrentDate().getMonthValue() + 12*(date.getYear() - Bank.getCurrentDate().getYear());
        if (Bank.getCurrentDate().compareTo(date) < 0 && numMonths > 0){
            Bank.setCurrentDate(date);
            for (Customer customer:Bank.getCustomers()) {
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
        } else denyLoan(customer, pendingLoan);
    }

    public void denyLoan(Customer customer, PendingLoan pendingLoan){
        // disapprove the pending loan
        customer.getPendingLoans().remove(pendingLoan);
    }

    public void receiveMoney(Currency c, double amount){
        if (amount > 0){
            account.changeBalanceBy(Constants.exchangeCurrency(account.getCurrency(), getAccount().getCurrency(), amount));
        }
    }

    public Transactions<Transaction> getTransactionsByTimePeriod(LocalDate day){
        return getTransactionsByTimePeriod(day, day);
    }

    public Transactions<Transaction> getTransactionsByTimePeriod(LocalDate begin, LocalDate end){
        Transactions<Transaction> transactions = new Transactions<>();
        for (Customer c:Bank.getCustomers()) {
            for (Account account:c.getAllAccounts()) {
                transactions.addAll(c.getTransactionsByTimePeriod(begin, end, account));
            }
        }
        return transactions;
    }

    public ManagerAccount getAccount() {
        return account;
    }

    public void setAccount(ManagerAccount account) {
        this.account = account;
    }

    public JSONObject toJSON(){
        JSONObject jsonObject = super.toJSON();
        JSONObject accountObject = account.toJSON();
        jsonObject.put("account", accountObject);
        return jsonObject;
    }

    public static Manager fromJSON(JSONObject jsonObject){
        Credentials credentials = Credentials.fromJSON((JSONObject) jsonObject.get("cred"));
        int ID = (int) jsonObject.get("ID");
        Manager manager = new Manager(credentials, ID);
        ManagerAccount account = ManagerAccount.fromJSON((JSONObject) jsonObject.get("account"), manager);
        manager.setAccount(account);
        return manager;
    }
}
