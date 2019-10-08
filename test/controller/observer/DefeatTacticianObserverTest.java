package controller.observer;

import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefeatTacticianObserverTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.getGameMap().printMap();
    }

    @Test
    void create() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("bow", "Weak Bow", 30, 0, 3, true);
        controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.selectUnitId(0);
        controller.createItem("bow", "Weak Bow", 0, 0, 3, true);
        controller.initGameTest(-1);
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();

        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getSelectedUnit().getEquippedItem().getName(), "Weak Bow");
        controller.useItemOn(7, 7);
        controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(7, 7));
        assertEquals(controller.getSelectedUnit().getCurrentHitPoints(), 20);
        controller.useItemOn(6, 6);
        assertEquals(controller.getTacticians().size(), 1);
    }

}