package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;

public class ReadFile {
    // A class to read and write our bank related data

    // A function that adds a user's general data
    public static void addUserInfo(String filename, User user) {
        JSONObject userObject = new JSONObject();
        userObject.put("Name", user.getName());
        userObject.put("UserID", user.getID());
        userObject.put("UName", user.getUName());
    }

    // put all of the user's transactions in one object
    public static void addTransactionToUser(String filename, Transactions<Transaction> transactions) {
        JSONObject userObject = new JSONObject();
        JSONArray userTransactions = new JSONArray();
        for (int i = 1; i < transactions.size() + 1; i++) {
            Transaction currentTransaction = transactions.get(i);
            JSONArray individualTransaction = new JSONArray();
            // add the transaction date, 0
            individualTransaction.add(1, currentTransaction.getDate());
            // add the transaction type, 1
            individualTransaction.add(2, currentTransaction.getClass().getName());
            // add the transaction amount, 2
            individualTransaction.add(3, currentTransaction.amount);
            userTransactions.add(individualTransaction);
        }
        userObject.put("transactions", userTransactions);
        try {
            Files.write(Paths.get(filename), userObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add all of a user's account information to their object
    public static void addAccountsToUser(String filename, Accounts<Account> accounts) {
        JSONObject userObject = new JSONObject();
        JSONArray userAccounts = new JSONArray();
        for (int i = 1; i < accounts.size() + 1; i++) {
            Account currentAccount = accounts.get(i);
            JSONArray individualAccount = new JSONArray();
            JSONArray accountTransactions = new JSONArray();
            // add the account name
            individualAccount.add(1, currentAccount.getName());
            // add the account ID
            individualAccount.add(2, currentAccount.getID());
            // add the account type
            individualAccount.add(3, currentAccount.getClass().getName());
            // add the account balance
            individualAccount.add(4, currentAccount.getBalance());
            // add the account currency
            individualAccount.add(5, currentAccount.getCurrency());

            for (int j = 0; j < currentAccount.getTransactions().size(); j++) {
                accountTransactions.add(0, currentAccount.getTransactions().get(j).getDate());
                accountTransactions.add(1, currentAccount.getTransactions().get(j).getClass().getName());
                accountTransactions.add(2, currentAccount.getTransactions().get(j).amount);
                individualAccount.add(6, accountTransactions);
            }
            userObject.put("transactions", accountTransactions);
            userAccounts.add(individualAccount);
        }
        userObject.put("accounts", userAccounts);
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
        Accounts<CheckingAccount> checkingAccounts = new Accounts<>();
        //Accounts<CreditAccount> creditAccounts = new Accounts<>();
        Accounts<SavingsAccount> savingsAccounts = new Accounts<>();
        Accounts<SecuritiesAccount> securitiesAccounts = new Accounts<>();

        // access accounts
        JSONObject usersAccounts = (JSONObject) currentUserJSON.get("accounts");
        // loop through all of the accounts

        for (int i = 0; i < usersAccounts.size(); i++) {
            JSONObject currentAccount = (JSONObject) usersAccounts.get(i);

            //get the transactions of the account
            JSONObject accountTransactions = (JSONObject) currentAccount.get("transactions");
            Transactions transactionsToAdd = new Transactions();
            // loop through each transaction and create a new one for each type
            for (int j = 0; j < accountTransactions.size(); j++) {
                JSONObject currentTransaction = (JSONObject) accountTransactions.get(j);
                if (currentTransaction.get(1) == "TransactionTransferIn") {
                    TransactionTransferIn newTransaction = new TransactionTransferIn((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2));
                    transactionsToAdd.add(newTransaction);
                }
                if (currentTransaction.get(1) == "TransactionTransferOut") {
                    TransactionTransferOut newTransaction = new TransactionTransferOut((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2));
                    transactionsToAdd.add(newTransaction);
                }
                if (currentTransaction.get(1) == "TransactionWithdrawal") {
                    TransactionWithdrawal newTransaction = new TransactionWithdrawal((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2));
                    transactionsToAdd.add(newTransaction);
                }

                if (currentTransaction.get(1) == "TransactionDeposit") {
                    TransactionDeposit newTransaction = new TransactionDeposit((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2));
                    transactionsToAdd.add(newTransaction);
                }
            }
            // Checking accounts
            if (currentAccount.get("accountName") == "CheckingAccount") {
                // create the account
                CheckingAccount newCheckingAccount = new CheckingAccount(currentAccount.get("accountName").toString(), transactionsToAdd,
                        (double) currentAccount.get("balance"), (Currency) currentAccount.get("currency"));
                checkingAccounts.add(newCheckingAccount);
            }

            if (currentAccount.get("accountName") == "SavingsAccount") {
                // create the account
                SavingsAccount newSavingsAccount = new SavingsAccount(currentAccount.get("accountName").toString(), transactionsToAdd,
                        (double) currentAccount.get("balance"), (Currency) currentAccount.get("currency"));
                savingsAccounts.add(newSavingsAccount);
            }

            if (currentAccount.get("accountName") == "CreditAccount") {
                // create the account
                CreditAccount newCreditAccount = new CreditAccount(currentAccount.get("accountName").toString(), transactionsToAdd,
                        (double) currentAccount.get("balance"), (Currency) currentAccount.get("currency"));
                creditAccounts.add(newCreditAccount);
            }

            if (currentAccount.get("accountName") == "SecuritiesAccount") {
                // create the account
                SecuritiesAccount newSecuritiesAccount = new SecuritiesAccount(currentAccount.get("accountName").toString(), (Currency) currentAccount.get("currency"));
                securitiesAccounts.add(newSecuritiesAccount);
            }

            customer.setCheckingAccounts(checkingAccounts);
            customer.setCreditAccounts(creditAccounts);
            customer.setSavingsAccounts(savingsAccounts);
            customer.setSecuritiesAccounts(securitiesAccounts);
        }
    }
}
