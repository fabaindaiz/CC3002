package model.units;

import model.items.IEquipableItem;
import model.items.weapon.Bow;
import model.units.warrior.Archer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

    private Archer archer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        archer = new Archer(50, 2, field.getCell(0, 0));
        archer.setMaxAction(4);
    }

    @Override
    public IEquipableItem getWeapon() {
        return bow;
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return archer;
    }

    @Override
    @Test
    public void attackToBow() {
        IUnit unit = new Archer(50, 2, field.getCell(1, 1));
        IEquipableItem item = new Bow("Example Bow", 10, 2, 3);
        if (item != null) {
            unit.addItem(item);
            unit.equipItem(item);
            archer.addItem(bow);
            archer.equipItem(bow);
            unit.useItem(archer, false);
            assertEquals(archer.getCurrentHitPoints(), getHPbow());
        }
    }

    /**
     * Checks if the bow is equipped correctly to the unit
     */
    @Test
    @Override
    public void equipBowTest() {
        assertNull(archer.getEquippedItem());
        archer.addItem(bow);
        archer.equipItem(bow);
        assertEquals(bow, archer.getEquippedItem());
    }

    @Override
    public int getHPanima() {
        return 35;
    }

    @Override
    public int getHPdark() {
        return 35;
    }

    @Override
    public int getHPlight() {
        return 35;
    }

    @Override
    public int getHPaxe() {
        return 40;
    }

    @Override
    public int getHPspear() {
        return 40;
    }

    @Override
    public int getHPsword() {
        return 40;
    }

    @Override
    public int getHPstaff() {
        return 40;
    }

    @Override
    public int getHPbow() {
        return 40;
    }

    @Override
    public int getHP1() {
        return 30;
    } //Bow

    @Override
    public int getHP2() {
        return 15;
    } //Light
}