package model.units;

import model.items.IEquipableItem;
import model.items.otheritem.Staff;
import model.units.otherunit.Cleric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

    private Cleric cleric;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        cleric = new Cleric(50, 2, field.getCell(0, 0));
        cleric.setMaxAction(4);
    }

    @Override
    public IEquipableItem getWeapon() {
        return staff;
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return cleric;
    }

    @Override
    @Test
    public void attackToStaff() {
        IUnit unit = new Cleric(50, 2, field.getCell(0, 0));
        IEquipableItem item = new Staff("Staff", 10, 1, 2);
        if (item != null) {
            unit.addItem(item);
            unit.equipItem(item);
            cleric.addItem(staff);
            cleric.equipItem(staff);
            unit.useItem(cleric, false);
            assertEquals(cleric.getCurrentHitPoints(), getHPstaff());
        }
    }

    @Test
    @Override
    public void equipStaffTest() {
        assertNull(cleric.getEquippedItem());
        cleric.addItem(staff);
        cleric.equipItem(staff);
        assertEquals(staff, cleric.getEquippedItem());
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
        unit.useItem(targetCounterattack1, true);
        assertEquals(unit.getCurrentHitPoints(), getHP1());
        unit.useItem(targetCounterattack2, true);
        assertEquals(unit.getCurrentHitPoints(), getHP1());
        unit.useItem(targetCounterattack3, true);
        assertEquals(unit.getCurrentHitPoints(), getHP2());
        unit.useItem(targetCounterattack4, true);
        assertEquals(unit.getCurrentHitPoints(), getHP2());
        targetCounterattack2.useItem(unit, true);
        assertEquals(unit.getCurrentHitPoints(), 50);
        assertEquals(targetCounterattack2.getCurrentHitPoints(), 50);
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