package observer;

import java.util.ArrayList;

public class Observable {
    private final ArrayList<Observer> OBSERVERS;

    public Observable() {
        OBSERVERS = new ArrayList<>();
    }

    // adds an observer
    public void addObserver(Observer o) {
        OBSERVERS.add(o);
    }

    // notifies each observer
    public void notifyObservers(Object args) {
        for(Observer o : OBSERVERS) {
            o.update(this, args);
        }
    }
}
