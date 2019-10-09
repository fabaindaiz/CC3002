package controller.observer;

import java.util.List;

/**
 * Interfaz que representa a un sujeto observable
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
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
