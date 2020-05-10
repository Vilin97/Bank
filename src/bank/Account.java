package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

abstract public class Account {
    protected int ID; // ID is the primary key for accounts
    protected String name;
    protected Transactions<Transaction> transactions;
    protected double balance;
    protected Currency currency;
    protected User user;
    protected WithdrawBehavior withdrawBehavior;
    protected DepositBehavior depositBehavior;
    protected TransferBehavior transferBehavior;
    protected EndOfMonthBehavior endOfMonthBehavior;

    private static int nextID = 0;

    public Account(int ID, String name, Transactions<Transaction> transactions,
                   double balance, Currency currency, Customer customer,
                   WithdrawBehavior withdrawBehavior, DepositBehavior depositBehavior,
                   TransferBehavior transferBehavior, EndOfMonthBehavior endOfMonthBehavior) {
        this.ID = ID;
        this.name = name;
        this.transactions = transactions;
        this.balance = balance;
        this.currency = currency;
        this.user = customer;
        this.withdrawBehavior = withdrawBehavior;
        this.depositBehavior = depositBehavior;
        this.transferBehavior = transferBehavior;
        this.endOfMonthBehavior = endOfMonthBehavior;
        nextID += 1;
    }

    public Account(String name, Transactions transactions, double balance, Currency currency, User user) {
        this.ID = nextID;
        this.name = name;
        this.transactions = transactions;
        this.balance = balance;
        this.currency = currency;
        this.user = user;
        nextID += 1;
    }

    public Account(String name, Currency currency, User user) {
        this(name, new Transactions(), 0, currency, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return ID == account.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public Transactions<Transaction> getTransactionsByTimePeriod(LocalDate begin, LocalDate end) {
        return transactions.getTransactionsByTimePeriod(begin, end);
    }

    public void doEndOfMonth(){
        endOfMonthBehavior.doEndOfMonth();
    }

    public void withdraw(double amount) {
        withdrawBehavior.withdraw(amount);
    }

    public void deposit(double amount) {
        depositBehavior.deposit(amount);
    }

    public void transfer(Account o, double amount) {
        transferBehavior.transfer(o,amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transactions<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getBalanceUSD() {
        // get balance in USD equivalent
        return Constants.exchangeCurrencyToUSD(currency, balance);
    }

    public void setBalanceUSD(double balance){
        // set balance in USD equivalent
        setBalance(Constants.exchangeUSDtoCurrency(getCurrency(), balance));
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void changeBalanceBy(double amount){
        // no input checking
        setBalance(getBalance() + amount);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ " '" + name + '\'' +
                ", balance: " + balance +
                ", currency: " + currency;
    }

    public String toStringDetailed() {
        return this.getClass().getSimpleName()+ " '" + name + '\'' +
                ", balance: " + balance +
                ", currency: " + currency+
                ", transactions: " + transactions;
    }

    public JSONObject toJSON(){
        JSONObject accountObject = new JSONObject();
        accountObject.put("type", getClass().getSimpleName());
        accountObject.put("name", name);
        accountObject.put("ID", ID);
        accountObject.put("balance", balance);
        accountObject.put("currency", currency.toString());

        JSONArray transactionsArray = new JSONArray();
        for (Transaction transaction:getTransactions()) {
            transactionsArray.add(transaction.toJSON());
        }
        accountObject.put("transactions", transactionsArray);
        return accountObject;
    }

    public static Account fromJSON(JSONObject jsonObject, User customer){
        String name = (String) jsonObject.get("name");
        Long temp = (Long) jsonObject.get("ID");
        int ID = Math.toIntExact(temp);
        double balance = (double) jsonObject.get("balance");
        Currency currency = Currency.getInstance((String) jsonObject.get("currency"));
        String type = (String) jsonObject.get("type");
        Account account;
        switch (type){
            case "CheckingAccount":
                account = new CheckingAccount(name, currency, (Customer) customer);
                account.setBalance(balance);
                break;
            case "SavingsAccount":
                account = new SavingsAccount(name, currency, (Customer) customer);
                account.setBalance(balance);
                break;
            case "SecuritiesAccount":
                double startingAmount = (double) jsonObject.get("startingAmount");
                JSONArray stocksArray = (JSONArray) jsonObject.get("stocks");
                Stocks stocks = new Stocks();
                for (Object s:stocksArray) {
                    stocks.add(Stock.fromJSON((JSONObject) s));
                }
                SecuritiesAccount securitiesAccount = new SecuritiesAccount(name, currency, startingAmount, (Customer) customer);
                securitiesAccount.setStocks(stocks);
                account = securitiesAccount;
                account.setBalance(balance);
                break;
            case "Loan":
                Collateral collateral = Collateral.fromJSON((JSONObject) jsonObject.get("collateral"));
                account = new Loan(name, currency, (Customer) customer, collateral);
                account.setBalance(balance);
                break;
            case "PendingLoan":
                Collateral collateral2 = Collateral.fromJSON((JSONObject) jsonObject.get("collateral"));
                SavingsAccount savingsAccount = (SavingsAccount) SavingsAccount.fromJSON((JSONObject) jsonObject.get("savingsAccount"), customer);
                account = new PendingLoan(name, balance, currency, (Customer) customer, savingsAccount, collateral2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        account.setID(ID);
        JSONArray transactionArray = (JSONArray) jsonObject.get("transactions");
        Transactions<Transaction> transactions = new Transactions<>();
        for (Object t:transactionArray) {
            transactions.add(Transaction.fromJSON((JSONObject) t, account, customer));
        }
        account.setTransactions(transactions);
        return account;
    }
}
