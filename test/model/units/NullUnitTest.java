package model.units;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.otherunit.Alpaca;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NullUnitTest extends AbstractTestUnit {

    private NullUnit nullUnit;

    @Override
    public void setTestUnit() {
        nullUnit = field.getCell(0, 0).getNullUnit();
    }

    @Override
    @Test
    public void constructorTest() {
        assertEquals(0, getTestUnit().getCurrentHitPoints());
        assertEquals(0, getTestUnit().getMaxHitPoints());
        assertEquals(0, getTestUnit().getMovement());
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
        assertTrue(getTestUnit().getItems().isEmpty());
    }

    @Override
    @Test
    public void healTest() {
        IUnit unit = getTestUnit();
        targetCounterattack2.setMaxAction(3);
        targetCounterattack2.addItem(staff);
        targetCounterattack2.addItem(item4);
        unit.setCurrentHitPoints(20);
        targetCounterattack2.equipItem(item4);
        targetCounterattack2.useItem(unit, false);
        assertEquals(unit.getCurrentHitPoints(), 20);
        targetCounterattack2.equipItem(staff);
        targetCounterattack2.useItem(unit, false);
        assertEquals(unit.getCurrentHitPoints(), 20);
        targetCounterattack2.useItem(unit, true);
        assertEquals(unit.getCurrentHitPoints(), 20);
        assertEquals(targetCounterattack2.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void testMovementUsed() {
        assertEquals(getTestUnit().getMovementUsed(), false);
        getTestUnit().moveTo(getField().getCell(0, 2));
        assertEquals(getTestUnit().getMovementUsed(), false);
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
        getTestUnit().moveTo(getField().getCell(0, 0));
        assertEquals(getTestUnit().getMovementUsed(), false);
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
        getTestUnit().setNewTurn();
        assertEquals(getTestUnit().getMovementUsed(), false);
    }

    @Override
    @Test
    public void attackToNullItem() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void testMovement() {
        getTestUnit().moveTo(getField().getCell(2, 2));
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
        getTestUnit().moveTo(getField().getCell(0, 2));
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
        getField().getCell(0, 1).setUnit(getTargetAlpaca());
        getTestUnit().moveTo(getField().getCell(0, 1));
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
    }

    @Override
    @Test
    public void llenarInventarioTest() {
        assertEquals(getTestUnit().getMaxItems(), 0);
        assertEquals(targetIntercambio.getItems(), List.of());
        getTestUnit().addItem(anima);
        assertEquals(getTestUnit().getItems(), List.of());
        getTestUnit().addItem(dark);
        assertEquals(getTestUnit().getItems(), List.of());
        getTestUnit().addItem(light);
        assertEquals(getTestUnit().getItems(), List.of());
        getTestUnit().exchange(targetIntercambio, anima);
        assertEquals(getTestUnit().getItems(), List.of());
        getTestUnit().exchange(targetIntercambio, dark);
        assertEquals(getTestUnit().getItems(), List.of());
        getTestUnit().exchange(targetIntercambio, light);
        assertEquals(getTestUnit().getItems(), List.of());
    }

    @Override
    @Test
    public void intercambioTest() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        assertEquals(targetAlpaca.getItems(), List.of());
        unit.addItem(staff);
        unit.addItem(item);
        unit.equipItem(item);
        unit.exchange(targetAlpaca, staff);
        assertEquals(targetAlpaca.getItems(), List.of());
        unit.exchange(targetAlpaca, item);
        assertEquals(targetAlpaca.getItems(), List.of());
        assertEquals(item.getOwner(), getTestUnit());
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
    }

    @Override
    @Test
    public void testActionUsed() {
        field.addCells(true, new Location(3, 2), new Location(3, 3));
        IUnit targetAttack = new Alpaca(50, 2, field.getCell(3, 3));
        getTestUnit().setMaxAction(1);
        getTestUnit().addItem(getWeapon());
        getTestUnit().addItem(item2);
        getTestUnit().equipItem(getWeapon());
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().useItem(targetAttack, false);
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().useItem(targetCounterattack1, false);
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().setNewTurn();
        assertEquals(getTestUnit().getItems(), List.of());
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().exchange(targetIntercambio, item3);
        assertEquals(getTestUnit().getItems(), List.of());
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().exchange(targetIntercambio, item2);
        assertEquals(getTestUnit().getItems(), List.of());
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().setNewTurn();
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().exchange(targetIntercambio, item1);
        assertEquals(getTestUnit().getItems(), List.of());
        assertEquals(getTestUnit().getActionRemains(), 0);
    }

    @Override
    public IEquipableItem getWeapon() { return nullUnit.getNullItem(); }

    @Override
    public IUnit getTestUnit() { return nullUnit; }

    @Override
    public int getHPanima() { return 50; }

    @Override
    public int getHPdark() { return 50; }

    @Override
    public int getHPlight() { return 50; }

    @Override
    public int getHPaxe() { return 50; }

    @Override
    public int getHPspear() { return 50; }

    @Override
    public int getHPsword() { return 50; }

    @Override
    public int getHPstaff() { return 50; }

    @Override
    public int getHPbow() { return 50; }

    @Override
    public int getHP1() { return 0; }

    @Override
    public int getHP2() { return 0; }

    @Override
    public int getHP3() { return 0; }

    @Override
    public int getHP4() { return 50; }
}
