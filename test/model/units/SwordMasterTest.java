package model.units;

import model.items.IEquipableItem;
import model.items.weapon.Sword;
import model.units.warrior.SwordMaster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SwordMasterTest extends AbstractTestUnit {

    private SwordMaster swordMaster;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        swordMaster = new SwordMaster(50, 2, field.getCell(0, 0));
    }

    @Override
    public IEquipableItem getWeapon() {
        return sword;
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return swordMaster;
    }

    @Override
    @Test
    public void attackToSword() {
        IUnit unit = new SwordMaster(50, 2, field.getCell(1, 1));
        IEquipableItem item = new Sword("Sword", 10, 1, 2);
        if (item != null) {
            unit.addItem(item);
            unit.equipItem(item);
            swordMaster.addItem(sword);
            swordMaster.equipItem(sword);
            unit.attack(swordMaster, false);
            assertEquals(swordMaster.getCurrentHitPoints(), getHPsword());
        }
    }

    @Override
    public void equipSwordTest() {
        assertNull(swordMaster.getEquippedItem());
        swordMaster.addItem(sword);
        swordMaster.equipItem(sword);
        assertEquals(sword, swordMaster.getEquippedItem());
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
        return 35;
    }

    @Override
    public int getHPspear() {
        return 50;
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