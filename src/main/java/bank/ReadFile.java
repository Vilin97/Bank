package bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.namespace.QName;
import java.io.File;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    // A class to read and parse bank related data

    public static void writeUserData(String filename, User user) {
        JSONObject userObject = new JSONObject();
        userObject.put("name", "Simon");
        userObject.put("currency", "USD");
        userObject.put("balance", "5.00");


        JSONArray transactions = new JSONArray();
        // loop through the user's transactions and add them to the array


    }

}
