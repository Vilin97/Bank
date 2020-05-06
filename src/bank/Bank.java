package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.Iterator;

public class Bank {
    private static Customers<Customer> customers;
    private static Manager manager;
    private static StockMarket stockMarket;
    private static LocalDate currentDate; // the current date does not depend on the specific instance of the bank

    public Bank(Customers<Customer> customers, Manager manager, StockMarket stockMarket) {
        Bank.customers = customers;
        Bank.manager = manager;
        Bank.stockMarket = stockMarket;
    }

    public Bank(Manager manager, StockMarket stockMarket, LocalDate currentDate) {
        Bank.manager = manager;
        Bank.stockMarket = stockMarket;
        Bank.currentDate = currentDate;
    }

    public Bank(Manager manager) {
        this(new Customers<Customer>(), manager, new StockMarket());
    }

    public Bank(Customers<Customer> customers, Manager manager) {
        this(customers, manager, new StockMarket());
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        Bank.currentDate = currentDate;
    }

    public static Customers<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(Customers<Customer> customers) {
        Bank.customers = customers;
    }

    public static Manager getManager() {
        return manager;
    }

    public static void setManager(Manager manager) {
        Bank.manager = manager;
    }

    public static StockMarket getStockMarket() {
        return stockMarket;
    }

    public static void setStockMarket(StockMarket stockMarket) {
        Bank.stockMarket = stockMarket;
    }
    
    public boolean containsCustomer(Customer cs){
        return Bank.customers.contains(cs);
    }
    
    public Customer returnCustomer(Customer cs){
        Customer rt = null;
        if(containsCustomer(cs)){
            rt = Bank.customers.getCustomer(cs);
        }else{
            throw new IllegalArgumentException("customer doesnt exisit");
        }
        return rt;
    }
    
    public void addCustomer(Customer cs){
        Bank.customers.add(cs);
    }
    
    public User logIn(String uname,String pw){
        Iterator cIter = Bank.customers.iterator();
        boolean notfound = true;
        User rt = null;
        
        while(cIter.hasNext() && notfound){
            Customer cs = (Customer) cIter.next();
            Credentials csCreds = cs.getCreds();
            //System.out.println(csCreds.toString());
            //System.out.println("Uname tried:"+uname+" pword tried "+pw);
            if(csCreds.login(uname, pw)){
                notfound = false;
                rt = cs;
                
            }
        }
        if(notfound){
            Credentials mCreds = manager.getCreds();
            if(mCreds.login(uname, pw)){
                notfound = false;
                rt = manager;
                
            }
        }
        return rt;
    }

    public static JSONObject toJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("date", General.localDateToJSON(Bank.getCurrentDate()));
        jsonObject.put("stockMarket", Bank.getStockMarket().toJSON());
        jsonObject.put("manager", Bank.getManager().toJSON());
        JSONArray jsonArray = new JSONArray();
        for (Customer c:Bank.getCustomers()) {
            jsonArray.add(c.toJSON());
        }
        jsonObject.put("customers", jsonArray);
        return jsonObject;
    }

    public static Bank fromJSON(JSONObject jsonObject){
        LocalDate date = General.localDateFromJSON((JSONObject) jsonObject.get("date"));
        StockMarket stockMarket = StockMarket.fromJSON((JSONObject) jsonObject.get("stockMarket"));
        Manager manager = Manager.fromJSON((JSONObject) jsonObject.get("manager"));
        Customers<Customer> customers = new Customers<>();
        JSONArray jsonArray = (JSONArray) jsonObject.get("customers");
        for (Object o:jsonArray) {
            Customer c = Customer.fromJSON((JSONObject) o);
            customers.add(c);
        }
        Bank.setCustomers(customers);
        return new Bank(manager, stockMarket, date);
    }
}
