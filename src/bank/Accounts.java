package bank;

import java.util.*;

public class Accounts<A extends Account> extends ArrayList<A> {

    @Override
    public String toString() {
        if (isEmpty()) return "";
        else return General.listToStringColumn(this);
    }

    public String toStringDetailed() {
        if (isEmpty()) return "";
        else {
            StringBuilder s = new StringBuilder();
            for (A t : this) {
                s.append(t.toStringDetailed()).append("\n");
            }
            return s.toString();
        }
    }
}
