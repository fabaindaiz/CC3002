package controller.observer;

import controller.GameController;
import model.Tactician;

/**
 * Objeto que representa un observer que verifica cuando un tactician es derrotado
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class DefeatTacticianObserver implements IObserver {

    private final GameController gameController;
    private final Tactician tactician;

    /**
     * Crea un observer que verifica cuando un tactician es derrotado
     *
     * @param gameController controlador del juego
     * @param tactician      tactician a observar
     */
    public DefeatTacticianObserver(GameController gameController, Tactician tactician) {
        this.gameController = gameController;
        this.tactician = tactician;
        this.tactician.attach(this);
    }

    @Override
    public void update() {
        if (tactician.getUnits().size() == 0) {
            if (gameController.getTurnOwner() == tactician)
                gameController.endTurn();
            gameController.removeTactician(tactician.getName());
        }
    }

}
