package bank.gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class EmitterPanel<S>  extends JPanel {
    Collection<Listener<S>> listeners;

    public EmitterPanel() {
        this.listeners = new ArrayList<>();
    }

    public void addListener(Listener<S> listener) {
        listeners.add(listener);
    }
    public void emit(List<S> list){
        for (Listener<S> l:listeners
             ) {
            l.receive(list);
        }
    }

    public void emit(S s){
        for (Listener<S> l:listeners
        ) {
            l.receive(s);
        }
    }
}
