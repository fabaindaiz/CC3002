package controller;

import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullParameterTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(1, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.getGameMap().printMap();
    }

    @Test
    void getLocation() {
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("bow", null, 10, 1, 3, false);
        assertEquals(controller.getParameters().get(1).getLocation(), null);
    }

    @Test
    void getName() {
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getParameters().get(0).getName(), null);
    }

    @Test
    void createUnit() {
        controller.createUnit(null, 50, 2, controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getParameters().size(), 0);
        controller.createUnit("null", 50, 2, controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getParameters().size(), 1);
        controller.initGameTest(-1);
        assertEquals(controller.getTurnOwner().getUnits().size(), 0);
    }

    @Test
    void createItem() {
        controller.createUnit(null, 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem(null, null, 10, 1, 3, true);
        assertEquals(controller.getParameters().size(), 0);
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("null", null, 10, 1, 3, true);
        assertEquals(controller.getParameters().size(), 2);
        controller.initGameTest(-1);
        controller.selectUnitId(0);
        assertEquals(controller.getItems().size(), 0);
    }

    @Test
    void create() {
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getParameters().size(), 1);
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getParameters().size(), 2);
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(15, 15));
        assertEquals(controller.getParameters().size(), 3);
        controller.initGameTest(-1);
        assertEquals(controller.getUnits().size(), 1);
    }

}
