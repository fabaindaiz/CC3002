package controller.observer;

import java.util.List;

public interface ISubject {

    /**
     *
     */
    void notifyAllObservers();

    /**
     * @param observer
     */
    void attach(IObserver observer);

    /**
     * @return
     */
    List<IObserver> getObservers();
}
