package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadFile {
    // A class to read and write our bank related data

    // A function that adds a user's general data
    public static void addUserInfo(String filename, User user) {
        JSONObject userObject = new JSONObject();
        userObject.put("Name", user.getName());
        userObject.put("UserID", user.getID());
        userObject.put("UName", user.getUName());
    }

    // Add all of a user's account information to their object
    public static void addAccountsToUser(String filename, Accounts<Account> accounts) {
        JSONObject userObject = new JSONObject();
        JSONArray userAccounts = new JSONArray();
        for (int i = 0; i < accounts.size(); i++) {
            Account currentAccount = accounts.getAccount(i);
            JSONArray individualAccount = new JSONArray();
            // add the account name
            individualAccount.add(currentAccount.getName());
            // add the account ID
            individualAccount.add(currentAccount.getID());
            // add the account type
            individualAccount.add(currentAccount.toString());
            // add the account balance
            individualAccount.add(currentAccount.getBalance());
            // add the account currency
            individualAccount.add(currentAccount.getCurrency());

            userAccounts.add(individualAccount);
        }
        userObject.put("accounts", userAccounts);
        try {
            Files.write(Paths.get(filename), userObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // loop through the user's transactions and add them to the trans
    public static void addTransactionToUser(String filename, Transactions<Transaction> transactions) {
        JSONObject userObject = new JSONObject();
        JSONArray userTransactions = new JSONArray();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction currentTransaction = transactions.getTransaction(i);
            JSONArray individualTransaction = new JSONArray();
            // add the transaction date
            individualTransaction.add(currentTransaction.getDate());
            // add the transaction type
            individualTransaction.add(currentTransaction.toString());
            // add the transaction amount
            individualTransaction.add(currentTransaction.amount);
            userTransactions.add(individualTransaction);
        }
        userObject.put("transactions", userTransactions);
        try {
            Files.write(Paths.get(filename), userObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read & set user's general data
    public static void readUserData(String filename, User user) throws IOException, ParseException {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        JSONObject currentUserJSON = (JSONObject) jsonParser.parse(reader);
        Name username = (Name) currentUserJSON.get("Name");
        int userID = (int) currentUserJSON.get("UserID");
        UName uname = (UName) currentUserJSON.get("UName");
        user.setCred(username, uname, userID);
    }

    // Read & set user's account data
    public static void readUserAccountData(String filename, Customer customer) throws IOException, ParseException {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        JSONObject currentUserJSON = (JSONObject) jsonParser.parse(reader);
        ArrayList<CheckingAccount> checkingAccounts = new ArrayList<>();
        ArrayList<CreditAccount> creditAccounts = new ArrayList<>();
        ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();
        ArrayList<SecuritiesAccount> securitiesAccounts = new ArrayList<>();
        JSONArray usersAccounts = (JSONArray) currentUserJSON.get("accounts");

        // loop through all of the accounts, find each account type and add it to the proper arraylist
        for (int i = 0; i < usersAccounts.size(); i++) {
            if (usersAccounts.get(i)[2] == ) {

            }
        }

    }


}
