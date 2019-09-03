package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.units.sorcerer.Sorcerer;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class SorcererTest extends AbstractTestUnit {

    private Sorcerer sorcerer;

    /**
     * Set up the main unit that's going to be tested in the test set
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(50, 2, field.getCell(0, 0));
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
    public void equipAnimaTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(anima);
        assertEquals(anima, sorcerer.getEquippedItem());
    }

    @Override
    @Test
    public void equipDarkTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(dark);
        assertEquals(dark, sorcerer.getEquippedItem());
    }

    @Override
    @Test
    public void equipLightTest() {
        assertNull(sorcerer.getEquippedItem());
        sorcerer.equipItem(light);
        assertEquals(light, sorcerer.getEquippedItem());
    }
}