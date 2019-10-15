package controller.observer;

import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefeatUnitObserverTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.getGameMap().printMap();
    }

    @Test
    void update() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("bow", "Weak Bow", 10, 0, 3, true);
        controller.createItem("bow", "Strong Bow", 50, 0, 3, false);
        controller.endTurn();
        controller.createUnit("cleric", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.createUnit("cleric", 50, 2, controller.getGameMap().getCell(7, 6));
        controller.initGameTest(-1);
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();

        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getSelectedUnit().getEquippedItem().getName(), "Weak Bow");
        controller.useItemOn(7, 7);
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getSelectedUnit().getCurrentHitPoints(), 50);
        controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(7, 7));
        assertEquals(controller.getSelectedUnit().getCurrentHitPoints(), 40);
        controller.endTurn();
        controller.selectUnitId(0);
        controller.equipItem(1);
        controller.useItemOn(7, 7);
        controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(7, 6));
    }

    @Test
    void updateCaseHero() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("bow", "Bow", 50, 0, 3, true);
        controller.endTurn();
        controller.createUnit("hero", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.createUnit("cleric", 50, 2, controller.getGameMap().getCell(7, 6));
        controller.initGameTest(-1);
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.selectUnitId(0);
        controller.useItemOn(7, 7);
        assertEquals(controller.getInitiatedGameStatus(), false);
        assertEquals(controller.getWinners().size(), 1);
        assertEquals(controller.getWinners().get(0), "Player 0");
    }
}