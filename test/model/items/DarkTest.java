package model.items;

import model.items.magicbook.Dark;
import model.map.Location;
import model.units.IUnit;
import model.units.sorcerer.Sorcerer;

/**
 * Test set for Axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
class DarkTest extends AbstractTestItem {

    private Dark dark;
    private Dark wrongDark;
    private Sorcerer sorcerer;

    @Override
    public void setTestItem() {
        expectedName = "Common dark";
        expectedPower = 10;
        expectedMinRange = 1;
        expectedMaxRange = 2;
        dark = new Dark(expectedName, expectedPower, expectedMinRange, expectedMaxRange);
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
        wrongDark = new Dark("Wrong dark", 0, -1, -2);
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        sorcerer = new Sorcerer(10, 5, new Location(0, 0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return wrongDark;
    }

    @Override
    public IEquipableItem getTestItem() {
        return dark;
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return sorcerer;
    }
}