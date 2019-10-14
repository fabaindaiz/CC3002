package model.units;

import model.items.IEquipableItem;
import model.units.otherunit.Alpaca;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

    private Alpaca alpaca;

    @Override
    public void setTestUnit() {
        alpaca = new Alpaca(50, 2, field.getCell(0, 0));
        alpaca.setMaxAction(4);
    }

    @Override
    @Test
    public void counterattackTest() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        targetCounterattack1.addItem(item1);
        targetCounterattack2.addItem(item2);
        targetCounterattack3.addItem(item3);
        targetCounterattack1.equipItem(item1);
        targetCounterattack2.equipItem(item2);
        targetCounterattack3.equipItem(item3);
        unit.addItem(item);
        unit.equipItem(item);
        unit.useItem(targetCounterattack1, true);
        assertEquals(unit.getCurrentHitPoints(), getHP1());
        unit.useItem(targetCounterattack2, true);
        assertEquals(unit.getCurrentHitPoints(), getHP1());
        unit.useItem(targetCounterattack3, true);
        assertEquals(unit.getCurrentHitPoints(), getHP2());
        unit.useItem(targetCounterattack4, true);
        assertEquals(unit.getCurrentHitPoints(), getHP2());
    }

    @Override
    @Test
    public void intercambioTest() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        assertEquals(targetAlpaca.getItems(), List.of());
        unit.addItem(staff);
        unit.exchange(targetAlpaca, staff);
        assertEquals(targetAlpaca.getItems(), List.of(staff));
        assertNull(unit.getEquippedItem());
    }

    @Override
    @Test
    public void llenarInventarioTest() {
        assertEquals(getTestUnit().getMaxItems(), Integer.MAX_VALUE);
        assertEquals(targetIntercambio.getItems(), List.of());
        getTestUnit().addItem(anima);
        assertEquals(getTestUnit().getItems(), List.of(anima));
        getTestUnit().addItem(dark);
        assertEquals(getTestUnit().getItems(), List.of(anima, dark));
        getTestUnit().addItem(light);
        assertEquals(getTestUnit().getItems(), List.of(anima, dark, light));
        getTestUnit().addItem(staff);
        assertEquals(getTestUnit().getItems(), List.of(anima, dark, light, staff));
        getTestUnit().exchange(targetIntercambio, anima);
        assertEquals(getTestUnit().getItems(), List.of(dark, light, staff));
        getTestUnit().exchange(targetIntercambio, dark);
        assertEquals(getTestUnit().getItems(), List.of(light, staff));
        getTestUnit().exchange(targetIntercambio, light);
        assertEquals(getTestUnit().getItems(), List.of(staff));
        getTestUnit().exchange(targetIntercambio, staff);
        assertEquals(targetIntercambio.getItems(), List.of(anima, dark, light));
        getTestUnit().addItem(staff);
        getTestUnit().exchange(targetIntercambio, staff);
        assertEquals(targetIntercambio.getItems(), List.of(anima, dark, light));
        assertEquals(getTestUnit().getItems(), List.of(staff, staff));
        targetIntercambio.exchange(getTestUnit(), staff);
        assertEquals(getTestUnit().getItems(), List.of(staff, staff));
        targetIntercambio.exchange(getTestUnit(), anima);
        assertEquals(getTestUnit().getItems(), List.of(staff, staff, anima));
        getTestUnit().exchange(targetIntercambio, staff);
        assertEquals(getTestUnit().getItems(), List.of(staff, anima));
    }

    @Override
    @Test
    public void attackToAnima() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void attackToDark() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void attackToLight() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void attackToAxe() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(fighter, false);
        assertEquals(fighter.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void attackToSword() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(swordMaster, false);
        assertEquals(swordMaster.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void attackToSpear() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(hero, false);
        assertEquals(hero.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void attackToStaff() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(cleric, false);
        assertEquals(cleric.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void attackToBow() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.useItem(archer, false);
        assertEquals(archer.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void testActionUsed() {
        

    }

    @Override
    public IEquipableItem getWeapon() {
        return null;
    }

    @Override
    public Alpaca getTestUnit() {
        return alpaca;
    }

    @Override
    public int getHPanima() {
        return 50;
    }

    @Override
    public int getHPdark() {
        return 50;
    }

    @Override
    public int getHPlight() {
        return 50;
    }

    @Override
    public int getHPaxe() {
        return 50;
    }

    @Override
    public int getHPspear() {
        return 50;
    }

    @Override
    public int getHPsword() {
        return 50;
    }

    @Override
    public int getHPstaff() {
        return 50;
    }

    @Override
    public int getHPbow() {
        return 50;
    }

    @Override
    public int getHP1() {
        return 50;
    } //Bow

    @Override
    public int getHP2() {
        return 50;
    } //Light
}