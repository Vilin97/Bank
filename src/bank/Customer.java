package bank;

import static bank.Credentials.createCredentials;
import java.util.ArrayList;

//A customer class must have the following functions:
//        Create checking account -- done
//        Create savings account -- done
//        Create securities account -- done
//        Close an account (where does the money go?)
//        Request loan
//        Deposit cash to an account -- done
//        Withdraw money from a checking account -- done
//        View transactions for an account (for a time period) -- done
//        View balance in an account -- done (just access the account)
//        Buy stock
//        Sell stock
//        Play on the stock market (repeatedly buy and sell stocks)
//        See realized profit from the stock market
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
        // customer with no credential, for testing
        this.savingsAccounts = new Accounts<SavingsAccount>();
        this.checkingAccounts = new Accounts<CheckingAccount>();
        this.securitiesAccounts = new Accounts<SecuritiesAccount>();
        this.loans = new Accounts<Loan>();
        this.pendingLoans = new Accounts<PendingLoan>();
    }

    /*
    private Transactions getTransactionsByTimePeriod(LocalDate begin, LocalDate end, Account account){
        return account.getTransactionsByTimePeriod(begin,end);
    }
    */
    
    private boolean isAnAccountOfCustomer(Account account){
        return savingsAccounts.contains(account) || checkingAccounts.contains(account) ||
                securitiesAccounts.contains(account) || loans.contains(account);
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
        return rt;
    }

    private void requestLoan(String name, String currency, double amountRequest) {
        // creates a pending loan
        // a pending loan becomes a loan if approved
        PendingLoan pendingLoan = AccountFactory.getAccount("loan", name, currency);
        pendingLoan.setBalance(-amountRequest);
        pendingLoans.add(pendingLoan);
    }

    private void openCheckingAccount(String name, String currency) {
        checkingAccounts.add(AccountFactory.getAccount("checking", name, currency));
    }

    private void openSavingsAccount(String name, String currency) {
        savingsAccounts.add(AccountFactory.getAccount("savings", name, currency));
    }

    private void openSecuritiesAccount(String name, String currency, SavingsAccount account, double amount) {
        // open a securities account and transfer amount from account
        if (account.getBalance() < amount ||
                Constants.exchangeCurrencyToUSD(account.getCurrency(),amount) < Constants.getFundsNeededToOpenSecuritiesAccount() ||
                account.getBalance() - amount < Constants.getFundsToMaintainInSavingsToOpenSecurities()) {
            System.out.println("Cannot open a securities account");
        } else{
            SecuritiesAccount securities = AccountFactory.getAccount("savings", name, currency);
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
    
}
