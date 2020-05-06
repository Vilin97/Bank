package bank;

import org.json.simple.JSONObject;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;

abstract public class Transaction implements Comparable<Transaction>{
    LocalDate date;
    double amount;
    User customer;
    Account account;

    public Transaction(LocalDate date, double amount, User customer, Account account) {
        this.date = date;
        this.amount = amount;
        this.customer = customer;
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{customer="+customer.getCreds().getName()+
                ", account="+account.getClass().getSimpleName()+
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }

    public int compareTo(Transaction o) {
        return date.compareTo(o.getDate());
    }

    public JSONObject toJSON(){
        JSONObject transactionObject = new JSONObject();
        transactionObject.put("date", General.localDateToJSON(getDate()));
        transactionObject.put("type", getClass().getSimpleName());
        transactionObject.put("amount", amount);

        if (this instanceof TransactionSellStock) {
            transactionObject.put("stock", ((TransactionSellStock) this).getStock().toJSON());
        }
        if (this instanceof TransactionBuyStock) {
            transactionObject.put("stock", ((TransactionBuyStock) this).getStock().toJSON());
        }
        return transactionObject;
    }

    public static Transaction fromJSON(JSONObject jsonObject, Account account, User user){
        LocalDate date = General.localDateFromJSON((JSONObject) jsonObject.get("date"));
        double amount = (double) jsonObject.get("amount");
        String type = (String) jsonObject.get("type");
        Transaction transaction;
        switch (type) {
            case "TransactionTransferIn":
                transaction = new TransactionTransferIn(date, amount, user, account);
                break;
            case "TransactionTransferOut":
                transaction = new TransactionTransferOut(date, amount, user, account);
                break;
            case "TransactionDeposit":
                transaction = new TransactionDeposit(date, amount, user, account);
                break;
            case "TransactionWithdrawal":
                transaction = new TransactionWithdrawal(date, amount, user, account);
                break;
            case "TransactionBuyStock":
                Stock stock = Stock.fromJSON((JSONObject) jsonObject.get("stock"));
                transaction = new TransactionBuyStock(date, amount, user, account, stock);
                break;
            case "TransactionSellStock":
                Stock stock1 = Stock.fromJSON((JSONObject) jsonObject.get("stock"));
                transaction = new TransactionSellStock(date, amount, user, account, stock1);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return transaction;
    }
}
