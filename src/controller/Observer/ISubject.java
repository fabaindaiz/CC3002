package controller.observer;

import java.util.List;

public interface ISubject {

    /**
     * Avisa a todos los Observer de un cambio
     */
    void notifyAllObservers();

    /**
     * Añade un Observer al sujeto
     *
     * @param observer
     */
    void attach(IObserver observer);

    /**
     * @return la lista de Observers
     */
    List<IObserver> getObservers();
}
