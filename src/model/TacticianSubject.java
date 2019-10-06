package model;

import controller.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class TacticianSubject {

    private List<Observer> observers = new ArrayList<Observer>();

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void attach(Observer observer){
        observers.add(observer);
    }
}
