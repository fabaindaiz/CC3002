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
        controller.createItem("bow", "Weak Bow", 10, 0, 3, true);
        controller.createItem("bow", "Strong Bow", 50, 0, 3, false);
        controller.endTurn();
        controller.createUnit("cleric", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getSelectedUnit().getEquippedItem().getName(), "Weak Bow");
        controller.useItemOn(7, 7);
        controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(7, 7));
        assertEquals(controller.getSelectedUnit().getCurrentHitPoints(), 40);
        controller.endTurn();
        controller.selectUnitId(0);
        controller.equipItem(1);
        controller.useItemOn(7, 7);
        assertEquals(controller.getTacticians().size(), 1);
    }

}