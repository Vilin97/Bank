package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Iterator;

public class ReadFile {
    // A class to read and write our bank related data

    // A function that adds a user's general data
    public static void writeUserData(String username, Customer customer) throws IOException {
        JSONObject userObject = new JSONObject();
        userObject.put("Name", customer.getName());
        userObject.put("UserID", customer.getID());
        userObject.put("UName", customer.getUName());
        userObject.put("Password", customer.getCreds().getPword().pword);

        // loop through the users accounts
        JSONObject userAccounts = new JSONObject();
        for (int i = 0; i < customer.getAllAccounts().size(); i++) {
            Account currentAccount = customer.getAllAccounts().get(i);
            userAccounts.put("accountName", currentAccount.name);
            userAccounts.put("accountID", currentAccount.ID);
            userAccounts.put("accountType", currentAccount.getClass().getName());
            userAccounts.put("balance", currentAccount.balance);
            userAccounts.put("currency", currentAccount.currency);
            if (currentAccount.getClass().getName() == "SecuritiesAccount") {
                JSONObject accountStocks = new JSONObject();
                Stocks<Stock> stocks = customer.getSecuritiesAccounts().getByID(currentAccount.ID).getStocks();
                for (int j = 0; j < stocks.size(); j++) {
                    accountStocks.put("stockName", stocks.getStockByIndex(j).getName());
                    accountStocks.put("stockID", stocks.getStockByIndex(j).getID());
                }
                userObject.put("stocks", accountStocks);
            }

            // loop through the transactions
            JSONObject accountTransactions = new JSONObject();
            Iterator transactionsIter = currentAccount.transactions.iterator();
            while(transactionsIter.hasNext()) {
                System.out.println("test");
                Transaction currentTransaction = (Transaction) transactionsIter.next();
                System.out.println(currentTransaction.getDate().toString());
                accountTransactions.put("transactionDate", currentTransaction.getDate().toString());
                accountTransactions.put("transactionType", currentTransaction.getClass().getName());
                accountTransactions.put("transactionAmount", currentTransaction.amount);
                if (currentTransaction instanceof TransactionSellStock) {
                    accountTransactions.put("stock", ((TransactionSellStock) currentTransaction).getStock());
                }
            }
            System.out.println("size:"+ accountTransactions.size());
            userObject.put("transactions", accountTransactions);
        }
        userObject.put("accounts", userAccounts);
        Files.write(Paths.get(username), userObject.toJSONString().getBytes());
    }


    public static Customer readUserData(String filename) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Customer newCustomer = new Customer();
        try(Reader reader = new FileReader(filename)) {
            JSONObject currentUserJSON = (JSONObject) jsonParser.parse(reader);
            // Store each type of users accounts
            Accounts<CheckingAccount> checkingAccounts = new Accounts<>();
            Accounts<SavingsAccount> savingsAccounts = new Accounts<>();
            Accounts<SecuritiesAccount> securitiesAccounts = new Accounts<>();

            // Store all of the users transactions
            Transactions<Transaction> allTransactions = new Transactions<>();

            // Set user basic credentials
            Name name = (Name) currentUserJSON.get("Name");
            int userID = (int) currentUserJSON.get("UserID");
            UName uname = (UName) currentUserJSON.get("UName");
            String password = (String)currentUserJSON.get("Password");
            newCustomer = new Customer(new Credentials(name.toString(), uname.toString(), password));
            newCustomer.setID(userID);

            // Read Account data
            JSONObject userAccounts = (JSONObject) currentUserJSON.get("accounts");
            for (int i = 0; i < userAccounts.size(); i++) {
                JSONObject currentAccount = (JSONObject) userAccounts.get(i);
                if (currentAccount.get("accountName").toString() == "bank.CheckingAccount") {
                    Transactions<Transaction> accountTransactions = new Transactions<>();
                    CheckingAccount newCheckingAccount = new CheckingAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
                    if (currentAccountTransactions.size() > 0) {
                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
                            if (currentTransaction.get("transactionType") == "TransactionWithdrawal") {
                                TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionWithdrawal);
                                accountTransactions.add(newTransactionWithdrawal);
                            }
                            if (currentTransaction.get("transactionType") == "TransactionTransferIn") {
                                TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionTransferIn);
                                accountTransactions.add(newTransactionTransferIn);
                            }
                            if (currentTransaction.get("transactionType") == "TransactionTransferOut") {
                                TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionTransferOut);
                                accountTransactions.add(newTransactionTransferOut);
                            }
                            if (currentTransaction.get("transactionType") == "TransactionDeposit") {
                                TransactionDeposit newTransactionDeposit = new TransactionDeposit((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionDeposit);
                                accountTransactions.add(newTransactionDeposit);
                            }
                        }
                        newCheckingAccount.setTransactions(accountTransactions);
                    }
                    checkingAccounts.add(newCheckingAccount);
                }

                if (currentAccount.get("accountName").toString() == "bank.SavingsAccount") {
                    Transactions<Transaction> accountTransactions = new Transactions<>();
                    SavingsAccount newSavingsAccount = new SavingsAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
                    if (currentAccountTransactions.size() > 0) {
                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
                            if (currentTransaction.get("transactionType") == "TransactionWithdrawal") {
                                TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
                                accountTransactions.add(newTransactionWithdrawal);
                            }
                            if (currentTransaction.get("transactionType") == "TransactionTransferIn") {
                                TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
                                accountTransactions.add(newTransactionTransferIn);
                            }
                            if (currentTransaction.get("transactionType") == "TransactionTransferOut") {
                                TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
                                accountTransactions.add(newTransactionTransferOut);
                            }
                            if (currentTransaction.get("transactionType") == "TransactionDeposit") {
                                TransactionDeposit newTransactionDeposit = new TransactionDeposit((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
                                allTransactions.add(newTransactionDeposit);
                                accountTransactions.add(newTransactionDeposit);
                            }
                        }
                        savingsAccounts.add(newSavingsAccount);
                        newSavingsAccount.setTransactions(accountTransactions);
                    }
                    savingsAccounts.add(newSavingsAccount);
                }

                if (currentAccount.get("accountName").toString() == "bank.SecuritiesAccount") {
                    Transactions<Transaction> accountTransactions = new Transactions<>();
                    // loop through the stocks in the securities account
                    SecuritiesAccount newSecuritiesAccount = new SecuritiesAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"),
                            (double) currentAccount.get("balance"), newCustomer);

                    JSONObject currentAccountStocks = (JSONObject) currentAccount.get("stocks");
                    Stocks stocksInAccount = new Stocks();
                    if (currentAccountStocks.size() > 0) {
                        for (int j = 0; j < currentAccountStocks.size(); j++) {
                            Stock newStock = new Stock(currentAccountStocks.get("stockName").toString());
                            stocksInAccount.add(newStock);
                        }
                        newSecuritiesAccount.setStocks(stocksInAccount);
                    }

                    // loop through the account's transactions
                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
                    Transactions<Transaction> securityAccountTransactions = new Transactions<>();
                    if (currentAccountTransactions.size() > 0) {
                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
                            if (currentTransaction.get("transactionType") == "TransactionSellStock") {
                                TransactionSellStock newTransactionSellStock = new TransactionSellStock((LocalDate) currentTransaction.get("transactionDate"),
                                        (double) currentTransaction.get("transactionAmount"), newCustomer, newSecuritiesAccount, (Stock) currentTransaction.get("stockName"));
                                accountTransactions.add(newTransactionSellStock);
                                securityAccountTransactions.add(newTransactionSellStock);
                            }

                            if (currentTransaction.get("transactionType") == "TransactionBuyStock") {
                                TransactionBuyStock newTransactionBuyStock = new TransactionBuyStock((LocalDate) currentTransaction.get("transactionDate"),
                                        (double) currentTransaction.get("transactionAmount"), newCustomer, newSecuritiesAccount, (Stock) currentTransaction.get("stockName"));
                                accountTransactions.add(newTransactionBuyStock);
                                securityAccountTransactions.add(newTransactionBuyStock);
                            }
                        }
                        newSecuritiesAccount.setTransactions(securityAccountTransactions);
                    }
                    securitiesAccounts.add(newSecuritiesAccount);
                }
            }
            newCustomer.setSecuritiesAccounts(securitiesAccounts);
            newCustomer.setSavingsAccounts(savingsAccounts);
            newCustomer.setCheckingAccounts(checkingAccounts);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newCustomer;
    }


//    public static void writeUserData(String username, Customer customer) throws IOException {
//        JSONObject userObject = new JSONObject();
//        userObject.put("Name", customer.getName());
//        userObject.put("UserID", customer.getID());
//        userObject.put("UName", customer.getUName());
//        userObject.put("Password", customer.getCreds().getPword().pword);
//
//        // loop through the users accounts
//        JSONArray userAccounts = new JSONArray();
//        for (int i = 0; i < customer.getAllAccounts().size(); i++) {
//            Account currentAccount = customer.getAllAccounts().get(i);
//            userAccounts.add(0, currentAccount.name);
//            userAccounts.add(1, currentAccount.ID);
//            userAccounts.add(2, currentAccount.getClass().getName());
//            userAccounts.add(3, currentAccount.balance);
//            userAccounts.add(4, currentAccount.currency);
//            if (currentAccount.getClass().getName() == "SecuritiesAccount") {
//                JSONArray accountStocks = new JSONArray();
//                Stocks<Stock> stocks = customer.getSecuritiesAccounts().getByID(currentAccount.ID).getStocks();
//                for (int j = 0; j < stocks.size(); j++) {
//                    accountStocks.add(0, stocks.getStockByIndex(j).getName());
//                    accountStocks.add(1, stocks.getStockByIndex(j).getID());
//                }
//                userObject.put("stocks", accountStocks);
//            }
//            // loop through the account's transactions
//            JSONArray accountTransactions = new JSONArray();
//            for (int j = 0; j < currentAccount.getTransactions().size(); j++) {
//                Transaction currentTransaction = currentAccount.getTransactions().get(j);
//                accountTransactions.add(0, currentTransaction.getDate().toString());
//                accountTransactions.add(1, currentTransaction.getClass().getName());
//                accountTransactions.add(2, currentTransaction.amount);
//                if (currentTransaction instanceof TransactionSellStock) {
//                    accountTransactions.add(3, ((TransactionSellStock) currentTransaction).getStock());
//                }
//            }
//            userObject.put("transactions", accountTransactions);
//        }
//        userObject.put("accounts", userAccounts);
//        //String filename = "Bank/src/users/";
//        Files.write(Paths.get(username), userObject.toJSONString().getBytes());
//    }

//    public static Customer readUserData(String filename) throws IOException, ParseException {
//        FileReader reader = new FileReader(filename);
//        JSONParser jsonParser = new JSONParser();
//        JSONObject currentUserJSON = (JSONObject) jsonParser.parse(reader);
//
//        // Store each type of users accounts
//        Accounts<CheckingAccount> checkingAccounts = new Accounts<>();
//        Accounts<SavingsAccount> savingsAccounts = new Accounts<>();
//        Accounts<SecuritiesAccount> securitiesAccounts = new Accounts<>();
//
//        // Store all of the users transactions
//        Transactions<Transaction> allTransactions = new Transactions<>();
//
//        // Set user basic credentials
//        Name name = (Name) currentUserJSON.get("Name");
//        int userID = (int) currentUserJSON.get("UserID");
//        UName uname = (UName) currentUserJSON.get("UName");
//        String password = (String)currentUserJSON.get("Password");
//        Customer newCustomer = new Customer(new Credentials(name.toString(), uname.toString(), password));
//        newCustomer.setID(userID);
//        //customer.setCred(name, uname, userID);
//
//        // Read Account data
//        JSONObject userAccounts = (JSONObject) currentUserJSON.get("accounts");
//        for (int i = 0; i < userAccounts.size(); i++) {
//            JSONObject currentAccount = (JSONObject) userAccounts.get(i);
//            if (currentAccount.get("accountName") == "bank.CheckingAccount") {
//                Transactions<Transaction> accountTransactions = new Transactions<>();
//                CheckingAccount newCheckingAccount = new CheckingAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
//                JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
//                for (int j = 0; j < currentAccountTransactions.size(); j++) {
//                    JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
//                    if (currentTransaction.get(1) == "TransactionWithdrawal") {
//                        TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newCheckingAccount);
//                        allTransactions.add(newTransactionWithdrawal);
//                        accountTransactions.add(newTransactionWithdrawal);
//                    }
//                    if (currentTransaction.get(1) == "TransactionTransferIn") {
//                        TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newCheckingAccount);
//                        allTransactions.add(newTransactionTransferIn);
//                        accountTransactions.add(newTransactionTransferIn);
//                    }
//                    if (currentTransaction.get(1) == "TransactionTransferOut") {
//                        TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newCheckingAccount);
//                        allTransactions.add(newTransactionTransferOut);
//                        accountTransactions.add(newTransactionTransferOut);
//                    }
//                    if (currentTransaction.get(1) == "TransactionDeposit") {
//                        TransactionDeposit newTransactionDeposit = new TransactionDeposit((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newCheckingAccount);
//                        allTransactions.add(newTransactionDeposit);
//                        accountTransactions.add(newTransactionDeposit);
//                    }
//                }
//                newCheckingAccount.setTransactions(accountTransactions);
//                checkingAccounts.add(newCheckingAccount);
//            }
//
//            if (currentAccount.get("accountName") == "bank.SavingsAccount") {
//                Transactions<Transaction> accountTransactions = new Transactions<>();
//                SavingsAccount newSavingsAccount = new SavingsAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
//                JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
//                for (int j = 0; j < currentAccountTransactions.size(); j++) {
//                    JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
//                    if (currentTransaction.get(1) == "TransactionWithdrawal") {
//                        TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newSavingsAccount);
//                        accountTransactions.add(newTransactionWithdrawal);
//                    }
//                    if (currentTransaction.get(1) == "TransactionTransferIn") {
//                        TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newSavingsAccount);
//                        accountTransactions.add(newTransactionTransferIn);
//                    }
//                    if (currentTransaction.get(1) == "TransactionTransferOut") {
//                        TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newSavingsAccount);
//                        accountTransactions.add(newTransactionTransferOut);
//                    }
//                    if (currentTransaction.get(1) == "TransactionDeposit") {
//                        TransactionDeposit newTransactionDeposit = new TransactionDeposit((LocalDate) currentTransaction.get(0), (double) currentTransaction.get(2), newCustomer, newSavingsAccount);
//                        allTransactions.add(newTransactionDeposit);
//                        accountTransactions.add(newTransactionDeposit);
//                    }
//                }
//                newSavingsAccount.setTransactions(accountTransactions);
//                savingsAccounts.add(newSavingsAccount);
//            }
//
//            if (currentAccount.get("accountName") == "bank.SecuritiesAccount") {
//                Transactions<Transaction> accountTransactions = new Transactions<>();
//                // loop through the stocks in the securities account
//                SecuritiesAccount newSecuritiesAccount = new SecuritiesAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"),
//                        (double) currentAccount.get("balance"), newCustomer);
//
//                JSONObject currentAccountStocks = (JSONObject) currentAccount.get("stocks");
//                Stocks stocksInAccount = new Stocks();
//                for (int j = 0; j < currentAccountStocks.size(); j++) {
//                    Stock newStock = new Stock(currentAccountStocks.get(1).toString());
//                    stocksInAccount.add(newStock);
//                }
//
//                newSecuritiesAccount.setStocks(stocksInAccount);
//
//                // loop through the account's transactions
//                JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
//                Transactions<Transaction> securityAccountTransactions = new Transactions<>();
//                for (int j = 0; j < currentAccountTransactions.size(); j++) {
//                    JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
//                    if (currentTransaction.get(1) == "TransactionSellStock") {
//                        TransactionSellStock newTransactionSellStock = new TransactionSellStock((LocalDate) currentTransaction.get(0),
//                                (double) currentTransaction.get(2), newCustomer, newSecuritiesAccount, (Stock) currentTransaction.get(4));
//                        accountTransactions.add(newTransactionSellStock);
//                        securityAccountTransactions.add(newTransactionSellStock);
//                    }
//
//                    if (currentTransaction.get(1) == "TransactionBuyStock") {
//                        TransactionBuyStock newTransactionBuyStock = new TransactionBuyStock((LocalDate) currentTransaction.get(0),
//                                (double) currentTransaction.get(2), newCustomer, newSecuritiesAccount, (Stock) currentTransaction.get(4));
//                        accountTransactions.add(newTransactionBuyStock);
//                        securityAccountTransactions.add(newTransactionBuyStock);
//                    }
//
//                }
//                newSecuritiesAccount.setTransactions(securityAccountTransactions);
//                securitiesAccounts.add(newSecuritiesAccount);
//            }
//        }
//        newCustomer.setSecuritiesAccounts(securitiesAccounts);
//        newCustomer.setSavingsAccounts(savingsAccounts);
//        newCustomer.setCheckingAccounts(checkingAccounts);
//        return newCustomer;
//    }
}