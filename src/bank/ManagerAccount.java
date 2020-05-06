package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Currency;

public class ManagerAccount extends Account {
    public ManagerAccount(String name, Transactions<Transaction> transactions, double balance, Currency currency) {
        super(name, transactions, balance, currency, Bank.getManager());
        this.withdrawBehavior = new CanWithdrawBehavior(this);
        this.depositBehavior = new CanDepositBehavior(this);
        this.transferBehavior = new UncheckedTransferBehavior(this);
        this.endOfMonthBehavior = new EndOfMonthNoBehavior(this);
    }

    public ManagerAccount(String name, Currency currency) {
        this(name, new Transactions<>(), 0, currency);
    }
    public ManagerAccount(Currency currency) {
        this("Manager's account", currency);
    }

    public static ManagerAccount fromJSON(JSONObject jsonObject, User manager){
        String name = (String) jsonObject.get("name");
        int ID = (int) jsonObject.get("ID");
        double balance = (double) jsonObject.get("balance");
        Currency currency = Currency.getInstance((String) jsonObject.get("currency"));
        ManagerAccount account = new ManagerAccount(name, currency);
        account.setBalance(balance);
        account.setID(ID);
        JSONArray transactionArray = (JSONArray) jsonObject.get("transactions");
        Transactions<Transaction> transactions = new Transactions<>();
        for (Object t:transactionArray) {
            transactions.add(Transaction.fromJSON((JSONObject) t, account, manager));
        }
        account.setTransactions(transactions);
        return account;
    }
}
