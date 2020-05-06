package bank;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSONTools {
    // A class to read and write our bank related data
    private final static String path = "BankState/";
    private final static String usersPath = "BankState/Users/";


    public static void writeUserData(User user){
        if (user instanceof Customer){
            writeCustomerData((Customer) user);
        } else if (user instanceof Manager){
            writeManagerData((Manager) user);
        }
    }

    private static void writeManagerData(Manager manager) {
        JSONObject userObject = manager.toJSON();
        JSONObject toWrite = new JSONObject();
        toWrite.put("User", userObject);
        try (FileWriter file = new FileWriter(usersPath+manager.getUName()+".json")){
            file.write(toWrite.toJSONString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void writeCustomerData(Customer customer) {
        JSONObject userObject = customer.toJSON();
        JSONObject toWrite = new JSONObject();
        toWrite.put("User", userObject);
        try (FileWriter file = new FileWriter(usersPath+customer.getUName()+".json")){
            file.write(toWrite.toJSONString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static User readUserData(String uname) {
        User user = null;
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(usersPath+uname+".json"));
            user = User.fromJSON((JSONObject) jsonObject.get("User"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }




//    public static Customer readUserData(String filename) {
//        JSONParser jsonParser = new JSONParser();
//        try(Reader reader = new FileReader(filename)) {
//            JSONObject currentUserJSON = (JSONObject) jsonParser.parse(reader);
//            // Store each type of users accounts
//            Accounts<CheckingAccount> checkingAccounts = new Accounts<>();
//            Accounts<SavingsAccount> savingsAccounts = new Accounts<>();
//            Accounts<SecuritiesAccount> securitiesAccounts = new Accounts<>();
//
//            // Store all of the users transactions
//            Transactions<Transaction> allTransactions = new Transactions<>();
//
//            // Set user basic credentials
//            String getname = (String)currentUserJSON.get("Name");
//            String[] parsed = getname.split(" ");
//            String firstname = parsed[0];
//            String lastname = parsed[1];
//            Name name = new Name(firstname, lastname);
//            Long lon = (Long)currentUserJSON.get("UserID");
//            int userID = lon.intValue();
//            String temp = (String)currentUserJSON.get("UName");
//            UName uname = new UName(temp);
//            String password = (String)currentUserJSON.get("Password");
//            newCustomer = new Customer(new Credentials(name.toString(), uname.toString(), password));
//            newCustomer.setID(userID);
//
//            // Read Account data
//            JSONObject userAccounts = (JSONObject) currentUserJSON.get("accounts");
//            for (int i = 0; i < userAccounts.size(); i++) {
//                JSONObject currentAccount = (JSONObject) userAccounts.get(i);
//                System.out.println("current account var length: "+ currentAccount.size());
//                if (currentAccount.get("accountType").toString().equals("bank.CheckingAccount")) {
//                    Transactions<Transaction> accountTransactions = new Transactions<>();
//                    CheckingAccount newCheckingAccount = new CheckingAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
//                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
//                    if (currentAccountTransactions.size() > 0) {
//                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
//                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
//                            if (currentTransaction.get("transactionType").equals("TransactionWithdrawal") ) {
//                                TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
//                                allTransactions.add(newTransactionWithdrawal);
//                                accountTransactions.add(newTransactionWithdrawal);
//                            }
//                            if (currentTransaction.get("transactionType").equals("TransactionTransferIn")) {
//                                TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
//                                allTransactions.add(newTransactionTransferIn);
//                                accountTransactions.add(newTransactionTransferIn);
//                            }
//                            if (currentTransaction.get("transactionType").equals("TransactionTransferOut")) {
//                                TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
//                                allTransactions.add(newTransactionTransferOut);
//                                accountTransactions.add(newTransactionTransferOut);
//                            }
//                            if (currentTransaction.get("transactionType").equals("TransactionDeposit")) {
//                                TransactionDeposit newTransactionDeposit = new TransactionDeposit((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newCheckingAccount);
//                                allTransactions.add(newTransactionDeposit);
//                                accountTransactions.add(newTransactionDeposit);
//                            }
//                        }
//                        newCheckingAccount.setTransactions(accountTransactions);
//                    }
//                    checkingAccounts.add(newCheckingAccount);
//                }
//
//                if (currentAccount.get("accountType").toString().equalsIgnoreCase("bank.SavingsAccount")) {
//                    Transactions<Transaction> accountTransactions = new Transactions<>();
//                    SavingsAccount newSavingsAccount = new SavingsAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"), newCustomer);
//                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
//                    if (currentAccountTransactions.size() > 0) {
//                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
//                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
//                            if (currentTransaction.get("transactionType").equals("TransactionWithdrawal")) {
//                                TransactionWithdrawal newTransactionWithdrawal = new TransactionWithdrawal((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
//                                accountTransactions.add(newTransactionWithdrawal);
//                            }
//                            if (currentTransaction.get("transactionType").equals("TransactionTransferIn")) {
//                                TransactionTransferIn newTransactionTransferIn = new TransactionTransferIn((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
//                                accountTransactions.add(newTransactionTransferIn);
//                            }
//                            if (currentTransaction.get("transactionType").equals("TransactionTransferOut")) {
//                                TransactionTransferOut newTransactionTransferOut = new TransactionTransferOut((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
//                                accountTransactions.add(newTransactionTransferOut);
//                            }
//                            if (currentTransaction.get("transactionType").equals("TransactionDeposit")) {
//                                TransactionDeposit newTransactionDeposit = new TransactionDeposit((LocalDate) currentTransaction.get("transactionDate"), (double) currentTransaction.get("transactionAmount"), newCustomer, newSavingsAccount);
//                                allTransactions.add(newTransactionDeposit);
//                                accountTransactions.add(newTransactionDeposit);
//                            }
//                        }
//                        savingsAccounts.add(newSavingsAccount);
//                        newSavingsAccount.setTransactions(accountTransactions);
//                    }
//                    savingsAccounts.add(newSavingsAccount);
//                }
//
//                if (currentAccount.get("accountType").toString().equalsIgnoreCase("bank.SecuritiesAccount")) {
//                    Transactions<Transaction> accountTransactions = new Transactions<>();
//                    // loop through the stocks in the securities account
//                    SecuritiesAccount newSecuritiesAccount = new SecuritiesAccount((String) currentAccount.get("accountName"), (Currency) currentAccount.get("currency"),
//                            (double) currentAccount.get("balance"), newCustomer);
//
//                    JSONObject currentAccountStocks = (JSONObject) currentAccount.get("stocks");
//                    Stocks stocksInAccount = new Stocks();
//                    if (currentAccountStocks.size() > 0) {
//                        for (int j = 0; j < currentAccountStocks.size(); j++) {
//                            Stock newStock = new Stock(currentAccountStocks.get("stockName").toString());
//                            stocksInAccount.add(newStock);
//                        }
//                        newSecuritiesAccount.setStocks(stocksInAccount);
//                    }
//
//                    // loop through the account's transactions
//                    JSONObject currentAccountTransactions = (JSONObject) currentAccount.get("transactions");
//                    Transactions<Transaction> securityAccountTransactions = new Transactions<>();
//                    if (currentAccountTransactions.size() > 0) {
//                        for (int j = 0; j < currentAccountTransactions.size(); j++) {
//                            JSONObject currentTransaction = (JSONObject) currentAccountTransactions.get(j);
//                            if (currentTransaction.get("transactionType").equals("TransactionSellStock")) {
//                                TransactionSellStock newTransactionSellStock = new TransactionSellStock((LocalDate) currentTransaction.get("transactionDate"),
//                                        (double) currentTransaction.get("transactionAmount"), newCustomer, newSecuritiesAccount, (Stock) currentTransaction.get("stockName"));
//                                accountTransactions.add(newTransactionSellStock);
//                                securityAccountTransactions.add(newTransactionSellStock);
//                            }
//
//                            if (currentTransaction.get("transactionType").equals("TransactionBuyStock")) {
//                                TransactionBuyStock newTransactionBuyStock = new TransactionBuyStock((LocalDate) currentTransaction.get("transactionDate"),
//                                        (double) currentTransaction.get("transactionAmount"), newCustomer, newSecuritiesAccount, (Stock) currentTransaction.get("stockName"));
//                                accountTransactions.add(newTransactionBuyStock);
//                                securityAccountTransactions.add(newTransactionBuyStock);
//                            }
//                        }
//                        newSecuritiesAccount.setTransactions(securityAccountTransactions);
//                    }
//                    securitiesAccounts.add(newSecuritiesAccount);
//                }
//            }
//            newCustomer.setSecuritiesAccounts(securitiesAccounts);
//            newCustomer.setSavingsAccounts(savingsAccounts);
//            newCustomer.setCheckingAccounts(checkingAccounts);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return newCustomer;
//    }

}