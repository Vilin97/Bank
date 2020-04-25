package bank;

import java.time.LocalDate;

public class Bank {
    private static LocalDate currentDate; // the current date does not depend on the specific instance of the bank

    public static LocalDate getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(LocalDate currentDate) {
        Bank.currentDate = currentDate;
    }
}
