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

    private static void writeStockMarketData(StockMarket stockMarket){
        JSONObject jsonObject = stockMarket.toJSON();
        JSONObject toWrite = new JSONObject();
        toWrite.put("StockMarket", jsonObject);
        try (FileWriter file = new FileWriter(path+"StockMarket.json")){
            file.write(toWrite.toJSONString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static StockMarket readStockMarketData() {
        StockMarket stockMarket = null;
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(path+"StockMarket.json"));
            stockMarket = StockMarket.fromJSON((JSONObject) jsonObject.get("StockMarket"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockMarket;
    }

    public static void writeBank() {
        JSONObject jsonObject = Bank.toJSON();
        JSONObject toWrite = new JSONObject();
        toWrite.put("Bank", jsonObject);
        try (FileWriter file = new FileWriter(path+"Bank.json")){
            file.write(toWrite.toJSONString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void readBank() {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(path+"Bank.json"));
            Bank.fromJSON((JSONObject) jsonObject.get("Bank"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}