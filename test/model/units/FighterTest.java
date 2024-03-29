package model.units;

import model.items.IEquipableItem;
import model.items.weapon.Axe;
import model.units.warrior.Fighter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

    private Fighter fighter;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        fighter = new Fighter(50, 2, field.getCell(0, 0));
        fighter.setMaxAction(4);
    }

    @Override
    public IEquipableItem getWeapon() {
        return axe;
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return fighter;
    }

    @Override
    @Test
    public void attackToAxe() {
        IUnit unit = new Fighter(50, 2, field.getCell(1, 1));
        IEquipableItem item = new Axe("Axe", 10, 1, 2);
        if (item != null) {
            unit.addItem(item);
            unit.equipItem(item);
            fighter.addItem(axe);
            fighter.equipItem(axe);
            unit.useItem(fighter, false);
            assertEquals(fighter.getCurrentHitPoints(), getHPaxe());
        }
    }

    /**
     * Checks if the axe is equipped correctly to the unit
     */
    @Test
    @Override
    public void equipAxeTest() {
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        fighter.addItem(axe);
        fighter.equipItem(axe);
        assertEquals(axe, fighter.getEquippedItem());
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
        return 35;
    }

    @Override
    public int getHPsword() {
        return 50;
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