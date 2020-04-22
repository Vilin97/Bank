import java.util.Date;

public class Bank {
    private static Date currentDate; // the current date does not depend on the specific instance of the bank

    public static Date getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(Date currentDate) {
        Bank.currentDate = currentDate;
    }
}
