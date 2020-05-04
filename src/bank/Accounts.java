package bank;

import java.util.*;

public class Accounts<A extends Account> extends ArrayList<A> {

    @Override
    public String toString() {
        if (isEmpty()) return "";
        else return super.toString()+"\n";
    }
}
