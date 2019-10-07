package controller.observer;

import controller.GameController;

public class EndGameObserver extends AbstractObserver {

    GameController gameController;

    public EndGameObserver(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void update() {
        if (gameController.getTacticians().size() == 1 || gameController.getMaxRounds() == gameController.getRoundNumber())
            gameController.endGame();
    }
}
