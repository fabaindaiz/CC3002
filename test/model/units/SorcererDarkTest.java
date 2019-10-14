package model.units;

import model.items.IEquipableItem;
import model.items.magicbook.Dark;
import model.units.sorcerer.Sorcerer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class SorcererDarkTest extends AbstractTestUnit {

    private Sorcerer sorcerer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
        sorcerer.setMaxAction(5);
    }

    @Override
    public IEquipableItem getWeapon() {
        return dark;
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }

    @Override
    @Test
    public void attackToDark() {
        IUnit unit = new Sorcerer(50, 2, field.getCell(1, 1));
        IEquipableItem item = new Dark("Example Dark", 10, 2, 3);
        if (item != null) {
            unit.addItem(item);
            unit.equipItem(item);
            sorcerer.addItem(dark);
            sorcerer.equipItem(dark);
            unit.useItem(sorcerer, false);
            assertEquals(sorcerer.getCurrentHitPoints(), getHPdark());
        }
    }

    @Override
    @Test
    public void equipAnimaTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(anima);
        sorcerer.equipItem(anima);
        assertEquals(anima, sorcerer.getEquippedItem());
    }

    @Override
    @Test
    public void equipDarkTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(dark);
        sorcerer.equipItem(dark);
        assertEquals(dark, sorcerer.getEquippedItem());
    }

    @Override
    @Test
    public void equipLightTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.addItem(light);
        sorcerer.equipItem(light);
        assertEquals(light, sorcerer.getEquippedItem());
    }

    @Override
    public int getHPanima() {
        return 35;
    }

    @Override
    public int getHPdark() {
        return 40;
    }

    @Override
    public int getHPlight() {
        return 50;
    }

    @Override
    public int getHPaxe() {
        return 35;
    }

    @Override
    public int getHPspear() {
        return 35;
    }

    @Override
    public int getHPsword() {
        return 35;
    }

    @Override
    public int getHPstaff() {
        return 40;
    }

    @Override
    public int getHPbow() {
        return 35;
    }

    @Override
    public int getHP1() {
        return 20;
    } //Bow

    @Override
    public int getHP2() {
        return 5;
    } //Light
}