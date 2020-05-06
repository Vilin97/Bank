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
        JSONObject userAccount = new JSONObject();

        for (int i = 0; i < customer.getAllAccounts().size(); i++) {
            userAccount = new JSONObject();
            Account currentAccount = customer.getAllAccounts().get(i);
            userAccount.put("accountName", currentAccount.name);
            userAccount.put("accountID", currentAccount.ID);
            userAccount.put("accountType", currentAccount.getClass().getName());
            userAccount.put("balance", currentAccount.balance);
            userAccount.put("currency", currentAccount.currency.toString());
//            if (currentAccount.getClass().getName().equals("SecuritiesAccount")) {
//                JSONObject accountStocks = new JSONObject();
//                Stocks<Stock> stocks = customer.getSecuritiesAccounts().
//                //Stocks<Stock> stocks = customer.getSecuritiesAccounts().getByID(currentAccount.ID).getStocks();
//                for (int j = 0; j < stocks.size(); j++) {
//                    accountStocks.put("stockName", stocks.getStockByIndex(j).getName());
//                    accountStocks.put("stockID", stocks.getStockByIndex(j).getID());
//                }
//                userObject.put("stocks", accountStocks);
//            }

            // loop through the transactions
            JSONObject accountTransactions = new JSONObject();
            Iterator transactionsIter = currentAccount.transactions.iterator();
            while(transactionsIter.hasNext()) {
                Transaction currentTransaction = (Transaction) transactionsIter.next();
                JSONObject accTransaction = new JSONObject();
                accTransaction.put("transactionDate", currentTransaction.getDate().toString());
                accTransaction.put("transactionType", currentTransaction.getClass().getName());
                accTransaction.put("transactionAmount", currentTransaction.amount);
                if (currentTransaction instanceof TransactionSellStock) {
                    accTransaction.put("stock", ((TransactionSellStock) currentTransaction).getStock());
                }
                accountTransactions.put("accountTransactions", accTransaction);
            }
            userAccount.put("transactions", accountTransactions);
            userAccounts.put(currentAccount.getID(), userAccount);
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
            String getname = (String)currentUserJSON.get("Name");
            String[] parsed = getname.split(" ");
            String firstname = parsed[0];
            String lastname = parsed[1];
            Name name = new Name(firstname, lastname);
            Long lon = (Long)currentUserJSON.get("UserID");
            int userID = lon.intValue();
            String temp = (String)currentUserJSON.get("UName");
            UName uname = new UName(temp);
            String password = (String)currentUserJSON.get("Password");
            newCustomer = new Customer(new Credentials(name.toString(), uname.toString(), password));
            newCustomer.setID(userID);

            // Read Account data
            JSONObject userAccounts = (JSONObject) currentUserJSON.get("accounts");
            for (int i = 0; i < userAccounts.size(); i++) {
                JSONObject currentAccount = (JSONObject) userAccounts.get(i);
                System.out.println("current account var length: "+ currentAccount.size());
                if (currentAccount.get("accountType").toString().equals("bank.CheckingAccount")) {
                    Transactions<Transaction> accountTransactions = new Transactions<>();
                    CheckingAccount newCheckingAccount = new CheckingAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
                    if (currentAccountTransactions.size() > 0) {
                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
                            if (currentTransaction.get("transactionType").equals("TransactionWithdrawal") ) {
                                TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionWithdrawal);
                                accountTransactions.add(newTransactionWithdrawal);
                            }
                            if (currentTransaction.get("transactionType").equals("TransactionTransferIn")) {
                                TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionTransferIn);
                                accountTransactions.add(newTransactionTransferIn);
                            }
                            if (currentTransaction.get("transactionType").equals("TransactionTransferOut")) {
                                TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionTransferOut);
                                accountTransactions.add(newTransactionTransferOut);
                            }
                            if (currentTransaction.get("transactionType").equals("TransactionDeposit")) {
                                TransactionDeposit newTransactionDeposit = new TransactionDeposit((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
                                allTransactions.add(newTransactionDeposit);
                                accountTransactions.add(newTransactionDeposit);
                            }
                        }
                        newCheckingAccount.setTransactions(accountTransactions);
                    }
                    checkingAccounts.add(newCheckingAccount);
                }

                if (currentAccount.get("accountType").toString().equalsIgnoreCase("bank.SavingsAccount")) {
                    Transactions<Transaction> accountTransactions = new Transactions<>();
                    SavingsAccount newSavingsAccount = new SavingsAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
                    if (currentAccountTransactions.size() > 0) {
                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
                            if (currentTransaction.get("transactionType").equals("TransactionWithdrawal")) {
                                TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
                                accountTransactions.add(newTransactionWithdrawal);
                            }
                            if (currentTransaction.get("transactionType").equals("TransactionTransferIn")) {
                                TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
                                accountTransactions.add(newTransactionTransferIn);
                            }
                            if (currentTransaction.get("transactionType").equals("TransactionTransferOut")) {
                                TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
                                accountTransactions.add(newTransactionTransferOut);
                            }
                            if (currentTransaction.get("transactionType").equals("TransactionDeposit")) {
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

                if (currentAccount.get("accountType").toString().equalsIgnoreCase("bank.SecuritiesAccount")) {
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
                            if (currentTransaction.get("transactionType").equals("TransactionSellStock")) {
                                TransactionSellStock newTransactionSellStock = new TransactionSellStock((LocalDate) currentTransaction.get("transactionDate"),
                                        (double) currentTransaction.get("transactionAmount"), newCustomer, newSecuritiesAccount, (Stock) currentTransaction.get("stockName"));
                                accountTransactions.add(newTransactionSellStock);
                                securityAccountTransactions.add(newTransactionSellStock);
                            }

                            if (currentTransaction.get("transactionType").equals("TransactionBuyStock")) {
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

}