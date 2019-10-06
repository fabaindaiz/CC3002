package controller.observer;

import controller.GameController;
import model.Tactician;

public class DefeatTacticianObserver extends Observer {

    GameController gameController;
    Tactician tactician;

    public DefeatTacticianObserver(GameController gameController, Tactician tactician){
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
