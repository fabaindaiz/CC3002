package model.units;

import model.items.IEquipableItem;
import model.items.otheritem.Staff;
import model.units.otherunit.Cleric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    @Override
    public void equipStaffTest() {
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        cleric.addItem(staff);
        cleric.equipItem(staff);
        assertEquals(staff, cleric.getEquippedItem());
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

    @Override
    public int getHP3() {
        return 50;
    }

    @Override
    public int getHP4() {
        return 50;
    }
}