package bank;

import static bank.Credentials.createCredentials;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;

//A user class must have the following functions:
//        Create checking account -- done
//        Create savings account -- done
//        Create securities account -- done
//        Close an account (where does the money go?)
//        Request loan -- done
//        Deposit cash to an account -- done
//        Withdraw money from a checking account -- done
//        View transactions for an account (for a time period) -- done
//        View balance in an account -- done (just access the account)
//        Buy stock -- done
//        Sell stock -- done
//        Play on the stock market (repeatedly buy and sell stocks)
//        See realized profit from the stock market -- done (view balance on securities account)
//        See unrealized profit from the stock market

/**
 *
 * @author adamstreich
 */
public class Customer extends User {
    private Accounts<SavingsAccount> savingsAccounts;
    private Accounts<CheckingAccount> checkingAccounts;
    private Accounts<SecuritiesAccount> securitiesAccounts;
    private Accounts<Loan> loans;
    private Accounts<PendingLoan> pendingLoans;
    
    public Customer(Credentials cd) {
        super(cd);
        this.savingsAccounts = new Accounts<SavingsAccount>();
        this.checkingAccounts = new Accounts<CheckingAccount>();
        this.securitiesAccounts = new Accounts<SecuritiesAccount>();
        this.loans = new Accounts<Loan>();
        this.pendingLoans = new Accounts<PendingLoan>();
    }

    public Customer() {
        // user with no credential, for testing
        this.savingsAccounts = new Accounts<SavingsAccount>();
        this.checkingAccounts = new Accounts<CheckingAccount>();
        this.securitiesAccounts = new Accounts<SecuritiesAccount>();
        this.loans = new Accounts<Loan>();
        this.pendingLoans = new Accounts<PendingLoan>();
    }

    public Transactions<Transaction> getTransactionsByTimePeriod(LocalDate begin, LocalDate end, Account account){
        return account.getTransactionsByTimePeriod(begin,end);
    }

    private boolean isAnAccountOfCustomer(Account account){
        return savingsAccounts.contains(account) || checkingAccounts.contains(account) ||
                securitiesAccounts.contains(account) || loans.contains(account);
    }

    public void buyStock(String name, SecuritiesAccount securitiesAccount){
        if (isAnAccountOfCustomer(securitiesAccount)) Bank.getStockMarket().sellStock(name, securitiesAccount);
        else System.out.println("Not your account");
    }

    public void sellStock(String name, SecuritiesAccount securitiesAccount){
        if (isAnAccountOfCustomer(securitiesAccount)) Bank.getStockMarket().buyStock(name, securitiesAccount);
        else System.out.println("Not your account");
    }

    public double getRealizedProfit(){
        double res = 0;
        for (SecuritiesAccount securitiesAccount:getSecuritiesAccounts()) {
            res += securitiesAccount.getRealizedProfit();
        }
        return res;
    }

    public double getUnrealizedProfit(){
        double res = 0;
        for (SecuritiesAccount securitiesAccount:getSecuritiesAccounts()) {
            res += securitiesAccount.getUnrealizedProfit(Bank.getStockMarket());
        }
        return res;
    }

    private void withdraw(double amount, Account account) {
        account.withdraw(amount);
    }

    private void deposit(double amount, Account account) {
        account.deposit(amount);
    }

    public static Customer createCustomer(String fn, String ln, String un, String pw){
        Customer rt;
        Credentials cr;
        try{
            cr = createCredentials(fn,ln,un,pw);
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            throw new IllegalArgumentException("Wrong credential specs");
        }
        
        rt = new Customer(cr);
        
        //THIS NEEDS TO BE IMPLEMENTED ONCE THE DATA SET IS ADDED
        /*
        if( DataSet contains rt.getCreds().getUname() ){
            throw new IllegalArgumentException("username already taken");
        }
        */
        
        return rt;
    }
   

    public void requestLoan(String name, String currency, double amountRequest, SavingsAccount savingsAccount) {
        // creates a pending loan
        // a pending loan becomes a loan if approved
        // the money is transferred to the savings account specified if the loan is approved
        PendingLoan pendingLoan = AccountFactory.getAccount("loan", name, currency, this);
        pendingLoan.setBalance(-amountRequest);
        pendingLoan.setSavingsAccount(savingsAccount);
        pendingLoans.add(pendingLoan);
    }

    public void transferToAccount(Account from, Account to, double amount){
        if (isAnAccountOfCustomer(from)) from.transfer(to, amount);
    }

    public void openCheckingAccount(String name, String currency) {
        checkingAccounts.add(AccountFactory.getAccount("checking", name, currency, this));
    }

    public void openSavingsAccount(String name, String currency) {
        savingsAccounts.add(AccountFactory.getAccount("savings", name, currency, this));
    }

    public void openSecuritiesAccount(String name, String currency, SavingsAccount account, double amount) {
        // open a securities account and transfer amount from account
        if (account.getBalance() < amount ||
                Constants.exchangeCurrencyToUSD(account.getCurrency(),amount) < Constants.getFundsNeededToOpenSecuritiesAccount() ||
                account.getBalance() - amount < Constants.getFundsToMaintainInSavingsToOpenSecurities() ||
                !isAnAccountOfCustomer(account)) {
            System.out.println("Cannot open a securities account");
        } else{
            SecuritiesAccount securities = new SecuritiesAccount(name, Currency.getInstance(currency), amount, this);
            account.transfer(securities, amount);
            securitiesAccounts.add(securities);
        }
    }

    public Accounts<Account> getAllAccounts(){
        Accounts<Account> accounts = new Accounts<>();
        accounts.addAll(savingsAccounts);
        accounts.addAll(checkingAccounts);
        accounts.addAll(securitiesAccounts);
        accounts.addAll(loans);
        accounts.addAll(pendingLoans);
        return accounts;
    }

    public Accounts<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(Accounts<SavingsAccount> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public Accounts<CheckingAccount> getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(Accounts<CheckingAccount> checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public Accounts<SecuritiesAccount> getSecuritiesAccounts() {
        return securitiesAccounts;
    }

    public void setSecuritiesAccounts(Accounts<SecuritiesAccount> securitiesAccounts) {
        this.securitiesAccounts = securitiesAccounts;
    }

    public Accounts<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Accounts<Loan> loans) {
        this.loans = loans;
    }

    public Accounts<PendingLoan> getPendingLoans() {
        return pendingLoans;
    }

    public void setPendingLoans(Accounts<PendingLoan> pendingLoans) {
        this.pendingLoans = pendingLoans;
    }

    @Override
    public boolean sudoUser() {
        return false;
    }
    
    public int numSecAcc(){
        return this.securitiesAccounts.size();
    }
    
    public int numChecAcc(){
        return this.checkingAccounts.size();
    }
    
    public int numSavAcc(){
        return this.savingsAccounts.size();
    }
    /*
    public int numLoans(){
        return this.creditAccounts.size();
    }
    */
    //geters for iterators for all the collection accounts
    public Iterator getSASIter(){
        Iterator iter = this.savingsAccounts.iterator();
        return iter;
    }
    
    public Iterator getCEKAIter(){
        Iterator iter = this.checkingAccounts.iterator();
        return iter;
    }
    
    /*
    
    are being replaced
    public SavingsAccount getSavAccByName(String nm){
        SavingsAccount rt = null;
        Iterator SAiter = this.getSASIter();
        while(SAiter.hasNext()){
            SavingsAccount sa = (SavingsAccount) SAiter.next();
            if(sa.name.equals(nm)){
                rt = sa;
            }
        }
        
        return rt;
    }
    
    public CheckingAccount getChekAccByName(String nm){
        CheckingAccount rt = null;
        Iterator CAiter = this.getCEKAIter();
        while(CAiter.hasNext()){
            CheckingAccount sa = (CheckingAccount) CAiter.next();
            if(sa.name.equals(nm)){
                rt = sa;
            }
        }
        
        return rt;
    }
*/
    
    //public methods for creating accounts
    public void createSavingsAccount(String name, String currency){
        openSavingsAccount( name, currency);
    }
    
    //public methods for creating accounts
    public void createCheckingAccount(String name, String currency){
        openCheckingAccount( name, currency);
    }

    @Override
    public String toString() {
        return "Customer{" + super.toString() +
                "\n"+savingsAccounts +
                ""+checkingAccounts +
                ""+securitiesAccounts +
                ""+loans +
                "" + pendingLoans +
                '}';
    }
}
