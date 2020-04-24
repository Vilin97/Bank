import java.util.Random;

public class Stock {

    private String name;
    private double price;

    // I need to figure out a way to represent the realized and unrealized profit & open position

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    /**
     * A method that will be used to change the price of each stock to mimic a stock market
     * although the changes are completely arbitrary
     */

    public void artificiallyInflateStock() {
        Random random = new Random();
        int priceChangeDecider = random.nextInt(2);
        if (priceChangeDecider == 0) {
            // Increase the price of the stock by some random value between 0-99
            int randIncrease = random.nextInt(100);
            this.price += randIncrease;
        }
        else {
            // Decrease the price of the stock, but without letting it get to zero
            int randDecrease = random.nextInt((int) this.price);
            this.price -= randDecrease;
        }
    }
}
