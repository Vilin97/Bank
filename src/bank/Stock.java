package bank;

import java.util.Objects;

public class Stock {
    private int ID;
    private String name;
    private static int nextID = 0;

    public Stock(String name) {
        this.ID = nextID;
        this.name = name;
        nextID += 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return getID() == stock.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stock " + name;
    }
}
