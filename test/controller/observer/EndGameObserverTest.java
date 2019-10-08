package controller.observer;

import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndGameObserverTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
    }

    @Test
    void update() {
        controller.initGameTest(-1);
        assertEquals(controller.getInitiatedGameStatus(), true);
        controller.removeTactician("Player 0");
        assertEquals(controller.getInitiatedGameStatus(), false);
    }

    @Test
    void updateCaseNormal() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("bow", "Bow", 50, 0, 3, true);
        controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.initGameTest(-1);
        assertEquals(controller.getInitiatedGameStatus(), true);
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.selectUnitId(0);
        controller.useItemOn(7, 7);
        assertEquals(controller.getInitiatedGameStatus(), false);
    }
}