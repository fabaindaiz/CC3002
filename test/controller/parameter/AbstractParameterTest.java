package controller.parameter;

import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractParameterTest implements IParameterTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.getGameMap().printMap();
    }

    @Test
    void createUnit() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("bow", "Example Bow", 10, 1, 3, true);
        controller.endTurn();
        controller.createUnit("cleric", 50, 2, controller.getGameMap().getCell(6, 7));

        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
        controller.selectItem(0);
        assertEquals(controller.getSelectedItem().getName(), "Example Bow");
        controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 7));
        controller.selectItem(0);
        assertEquals(controller.getSelectedItem(), null);
    }

    @Test
    void createItem() {
    }

    @Test
    void Parameter() {

    }

}