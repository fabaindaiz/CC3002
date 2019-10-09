package controller.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que representa a un jugador
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class AbstractSubject implements ISubject {

    private List<IObserver> observers = new ArrayList<IObserver>();

    @Override
    public void notifyAllObservers() {
        for (IObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public List<IObserver> getObservers() {
        return observers;
    }

}
