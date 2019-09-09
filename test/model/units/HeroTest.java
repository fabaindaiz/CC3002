package model.units;

import model.items.IEquipableItem;
import model.items.weapon.Spear;
import model.units.warrior.Hero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Ignacio Slater Muñoz
 */
public class HeroTest extends AbstractTestUnit {

    private Hero hero;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        hero = new Hero(50, 2, field.getCell(0, 0));
    }

    @Override
    public IEquipableItem getWeapon() {
        return spear;
    }

    /**
     * @return the current unit being tested
     */
    @Override
    public IUnit getTestUnit() {
        return hero;
    }

    @Override
    @Test
    public void attackToSpear() {
        IUnit unit = new Hero(50, 2, field.getCell(1, 1));
        IEquipableItem item = new Spear("Spear", 10, 1, 2);
        if (item != null) {
            unit.addItem(item);
            unit.equipItem(item);
            hero.addItem(spear);
            hero.equipItem(spear);
            unit.attack(hero, false);
            assertEquals(hero.getCurrentHitPoints(), getHPspear());
        }
    }

    @Override
    @Test
    public void equipSpearTest() {
        assertNull(hero.getEquippedItem());
        hero.addItem(spear);
        hero.equipItem(spear);
        assertEquals(spear, hero.getEquippedItem());
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
        return 50;
    }

    @Override
    public int getHPspear() {
        return 40;
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