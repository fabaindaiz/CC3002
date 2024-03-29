package controller.parameter;

import controller.GameController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class AbstractParameterTest implements IAbstractParameterTest {

    GameController controller;

    @BeforeEach
    void setUp() {
        controller = new GameController(2, 50);
        controller.changeMap((long) 168 + 167 + 167);
        controller.getGameMap().printMap();
    }

    @Test
    void getUnitType() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit(getTypeUnit(), 50, 2, controller.getGameMap().getCell(6, 6));
        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        assertEquals(controller.getParameters().get(0).getType(), getTypeUnit());
    }

    @Test
    void getItemType() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit(getTypeUnit(), 50, 2, controller.getGameMap().getCell(6, 6));
        if (getTypeItem() != null) {
            controller.selectUnitId(0);
            controller.createItem(getTypeItem(), getNameItem(), 10, 1, 3, true);
        }
        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        if (getTypeItem() != null)
            assertEquals(controller.getParameters().get(1).getType(), getTypeItem());
    }

    @Test
    void getLocation() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit(getTypeUnit(), 50, 2, controller.getGameMap().getCell(6, 6));
        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        assertEquals(controller.getParameters().get(0).getLocation(), controller.getGameMap().getCell(6, 6));
    }

    @Test
    void getName() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit(getTypeUnit(), 50, 2, controller.getGameMap().getCell(6, 6));
        if (getTypeItem() != null) {
            controller.selectUnitId(0);
            controller.createItem(getTypeItem(), getNameItem(), 10, 1, 3, true);
        }
        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        if (getTypeItem() != null)
            assertEquals(controller.getParameters().get(1).getName(), getNameItem());
    }

    @Test
    void createUnit() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit(getTypeUnit(), 50, 2, controller.getGameMap().getCell(6, 6));
        if (getTypeItem() != null) {
            controller.selectUnitId(0);
            controller.createItem(getTypeItem(), getNameItem(), 10, 1, 3, true);
        }
        controller.endTurn();
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(6, 7));

        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
        assertEquals(controller.getSelectedUnit().getCurrentHitPoints(), 50);
        assertEquals(controller.getSelectedUnit().getMovement(), 2);
        controller.selectItem(0);
        if (getTypeItem() != null) {
            assertEquals(controller.getSelectedItem().getName(), getNameItem());
            assertEquals(controller.getSelectedUnit().getEquippedItem().getName(), getNameItem());
        } else
            assertEquals(controller.getSelectedItem(), controller.getTurnOwner().getNullItem());
        controller.endTurn();
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 7));
        assertEquals(controller.getSelectedUnit().getCurrentHitPoints(), 50);
        assertEquals(controller.getSelectedUnit().getMovement(), 2);
        controller.selectItem(0);
        assertEquals(controller.getSelectedItem(), controller.getTurnOwner().getNullItem());
    }

    @Test
    void createItem() {
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit(getTypeUnit(), 50, 2, controller.getGameMap().getCell(6, 6));
        if (getTypeItem() != null) {
            controller.selectUnitId(0);
            controller.createItem(getTypeItem(), getNameItem(), 10, 1, 3, true);
        }
        controller.endTurn();
        controller.createUnit("alpaca", 50, 2, controller.getGameMap().getCell(6, 7));

        controller.initEndlessGame();
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.selectUnitId(0);
        controller.selectItem(0);
        if (getTypeItem() != null) {
            assertEquals(controller.getSelectedItem().getName(), getNameItem());
            assertEquals(controller.getSelectedItem().getPower(), 10);
            assertEquals(controller.getSelectedUnit().getEquippedItem().getName(), getNameItem());
        } else
            assertEquals(controller.getSelectedItem(), controller.getTurnOwner().getNullItem());
        controller.endTurn();
        controller.selectUnitId(0);
        controller.selectItem(0);
        assertEquals(controller.getSelectedItem(), controller.getTurnOwner().getNullItem());
    }

}