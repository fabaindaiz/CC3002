package model;

import model.items.IEquipableItem;
import model.items.otheritem.Staff;
import model.items.weapon.Spear;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import model.units.otherunit.Cleric;
import model.units.warrior.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TacticianTest {
    Field gameMap;
    Tactician tactician1;
    Tactician tactician2;
    IUnit unit1;
    IUnit unit2;
    IUnit unit3;
    IEquipableItem item1;
    IEquipableItem item2;

    @BeforeEach
    void setUp() {
        gameMap = new Field();
        gameMap.addCells(true, new Location(0, 0));
        gameMap.addCells(true, new Location(0, 1));
        gameMap.addCells(true, new Location(1, 0));
        gameMap.addCells(true, new Location(1, 1));

        tactician1 = new Tactician ("ExamplePlayer", gameMap);
        tactician2 = new Tactician ("Player0", gameMap);
        unit1 = new Hero(50, 2, gameMap.getCell(0, 0));
        unit2 = new Cleric(50, 2, gameMap.getCell(1, 1));
        unit3 = new Hero(50, 2, gameMap.getCell(1, 0));
        item1 = new Spear("Spear", 10, 1, 3);
        item2 = new Staff("Staff", 10, 1, 3);
        unit1.addItem(item1);
        unit1.addItem(item2);
        tactician1.addUnit(unit1);
        tactician1.addUnit(unit2);
        tactician2.addUnit(unit3);

    }

    @Test
    void getName() {
        assertEquals(tactician1.getName(), "ExamplePlayer");
        assertEquals(tactician2.getName(), "Player0");
    }

    @Test
    void getUnits() {
        assertEquals(tactician1.getUnits(), List.of(unit1, unit2));
        assertEquals(tactician2.getUnits(), List.of(unit3));
    }

    @Test
    void addUnit() {
        unit3 = new Hero(50, 2, gameMap.getCell(0, 1));
        assertEquals(tactician1.getUnits(), List.of(unit1, unit2));
        tactician1.addUnit(null);
        assertEquals(tactician1.getUnits(), List.of(unit1, unit2));
        tactician1.addUnit(unit3);
        assertEquals(tactician1.getUnits(), List.of(unit1, unit2, unit3));
    }

    @Test
    void removeUnit() {
        assertEquals(tactician1.getUnits(), List.of(unit1, unit2));
        tactician1.removeUnit(unit2);
        assertEquals(tactician1.getUnits(), List.of(unit1));
        tactician1.removeUnit(null);
        assertEquals(tactician1.getUnits(), List.of(unit1));
    }

    @Test
    void removeAllUnit() {
        assertEquals(tactician1.getUnits(), List.of(unit1, unit2));
        assertEquals(unit1.getLocation().getUnit(),unit1);
        assertEquals(unit2.getLocation().getUnit(),unit2);
        tactician1.removeAllUnit();
        assertEquals(tactician1.getUnits(), List.of());
        assertEquals(unit1.getLocation().getUnit(),null);
        assertEquals(unit2.getLocation().getUnit(),null);
    }

    @Test
    void selectUnitIn() {
        assertEquals(tactician1.getSelectedUnit(), null);
        tactician1.selectUnitIn(0,0);
        assertEquals(tactician1.getSelectedUnit(), unit1);
        tactician1.selectUnitIn(1,0);
        assertEquals(tactician1.getSelectedUnit(), null);
        tactician1.selectUnitIn(0,1);
        assertEquals(tactician1.getSelectedUnit(), null);
        tactician1.selectUnitIn(1,1);
        assertEquals(tactician1.getSelectedUnit(), unit2);
        tactician1.selectUnitIn(2,2);
        assertEquals(tactician1.getSelectedUnit(), null);
    }

    @Test
    void selectUnitId() {
        assertEquals(tactician1.getSelectedUnit(), null);
        tactician1.selectUnitId(0);
        assertEquals(tactician1.getSelectedUnit(), unit1);
        tactician1.selectUnitId(1);
        assertEquals(tactician1.getSelectedUnit(), unit2);
        tactician1.selectUnitId(2);
        assertEquals(tactician1.getSelectedUnit(), null);
    }

    @Test
    void getSelectedUnit() {
        assertEquals(tactician1.getSelectedUnit(), null);
        tactician1.selectUnitId(0);
        assertEquals(tactician1.getSelectedUnit(), unit1);
        tactician1.selectUnitId(1);
        assertEquals(tactician1.getSelectedUnit(), unit2);
        tactician1.selectUnitId(2);
        assertEquals(tactician1.getSelectedUnit(), null);
    }

    @Test
    void getItems() {
        assertEquals(tactician1.getItems(), null);
        tactician1.selectUnitId(0);
        assertEquals(tactician1.getItems(), List.of(item1,item2));
        tactician1.selectUnitId(1);
        assertEquals(tactician1.getItems(), List.of());
        tactician1.selectUnitId(2);
        assertEquals(tactician1.getItems(), null);
        assertEquals(tactician2.getItems(), null);
        tactician2.selectUnitId(0);
        assertEquals(tactician2.getItems(), List.of());
        tactician2.selectUnitId(1);
        assertEquals(tactician2.getItems(), null);
    }

    @Test
    void selectItem() {
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectItem(0);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectUnitId(0);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectItem(0);
        assertEquals(tactician1.getSelectedItem(), item1);
        tactician1.selectItem(1);
        assertEquals(tactician1.getSelectedItem(), item2);
        tactician1.selectItem(2);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectUnitId(1);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectItem(0);
        assertEquals(tactician1.getSelectedItem(), null);
    }

    @Test
    void getSelectedItem() {    //Cambiar esto
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectItem(0);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectUnitId(0);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectItem(0);
        assertEquals(tactician1.getSelectedItem(), item1);
        tactician1.selectItem(1);
        assertEquals(tactician1.getSelectedItem(), item2);
        tactician1.selectItem(2);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectUnitId(1);
        assertEquals(tactician1.getSelectedItem(), null);
        tactician1.selectItem(0);
        assertEquals(tactician1.getSelectedItem(), null);
    }

    @Test
    void equipItem() {
        tactician1.selectUnitId(0);
        assertEquals(tactician1.getSelectedUnit().getEquippedItem(),null);
        tactician1.equipItem(2);
        assertEquals(tactician1.getSelectedUnit().getEquippedItem(),null);
        tactician1.equipItem(1);
        assertEquals(tactician1.getSelectedUnit().getEquippedItem(),null);
        tactician1.equipItem(0);
        assertEquals(tactician1.getSelectedUnit().getEquippedItem(),item1);
        tactician1.selectUnitId(1);
        assertEquals(tactician1.getSelectedUnit().getEquippedItem(),null);
        tactician1.equipItem(0);
        assertEquals(tactician1.getSelectedUnit().getEquippedItem(),null);
    }

    @Test
    void useItemOn() {
        tactician1.selectUnitId(0);
        tactician1.useItemOn(1,1);
        assertEquals(unit3.getCurrentHitPoints(), 50);
        tactician1.equipItem(0);
        tactician1.useItemOn(1,0);
        assertEquals(unit3.getCurrentHitPoints(), 40);
    }

    @Test
    void giveItemTo() {
        tactician1.selectUnitId(0);
        tactician1.giveItemTo(1,1);
        assertEquals(unit1.getItems(), List.of(item1,item2));
        assertEquals(unit2.getItems(), List.of());
        tactician1.selectItem(0);
        tactician1.giveItemTo(1,1);
        assertEquals(unit1.getItems(), List.of(item1,item2));
        assertEquals(unit2.getItems(), List.of());
        tactician1.moveUnitTo(0,1);
        tactician1.selectItem(0);
        tactician1.giveItemTo(1,1);
        assertEquals(unit1.getItems(), List.of(item2));
        assertEquals(unit2.getItems(), List.of(item1));
    }

    @Test
    void moveUnitTo() {
        tactician1.selectUnitId(0);
        assertEquals(tactician1.getSelectedUnit().getLocation(),gameMap.getCell(0, 0));
        tactician1.moveUnitTo(0,2);
        assertEquals(tactician1.getSelectedUnit().getLocation(),gameMap.getCell(0, 0));
        tactician1.moveUnitTo(0,1);
        assertEquals(tactician1.getSelectedUnit().getLocation(),gameMap.getCell(0, 1));
        tactician1.moveUnitTo(1,1);
        assertEquals(tactician1.getSelectedUnit().getLocation(),gameMap.getCell(0, 1));
    }
}