package bank;

import java.util.Collections;
import java.util.List;

public interface Listener<S> {
    void receive(List<S> strings);
    default void receive(S s){
        receive(Collections.singletonList(s));
    }
}
