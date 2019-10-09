package controller.observer;

import controller.GameController;
import model.ITactician;

/**
 * Objeto que representa un observer que verifica cuando un juego termina
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class EndGameObserver implements IObserver {

    GameController gameController;

    /**
     * Crea un observer que verifica cuando un juego termina
     *
     * @param gameController controlador del juego que se observa
     */
    public EndGameObserver(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void update() {
        if (gameController.getTacticians().size() == 1 || gameController.getMaxRounds() == gameController.getRoundNumber())
            gameController.endGame();
    }
}
