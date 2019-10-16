package model.items;

import model.items.otheritem.NullItem;
import model.map.Location;
import model.units.IUnit;
import model.units.otherunit.Cleric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullItemTest extends AbstractTestItem {

    private Cleric cleric;

    @Override
    public void setTestItem() {
        expectedName = null;
        expectedMinRange = 1;
        expectedMaxRange = 1;
    }

    /**
     * Sets up an item with wrong ranges setted.
     */
    @Override
    public void setWrongRangeItem() {
    }

    /**
     * Sets the unit that will be equipped with the test item
     */
    @Override
    public void setTestUnit() {
        cleric = new Cleric(10, 5, new Location(0, 0));
    }

    @Override
    public IEquipableItem getWrongTestItem() {
        return new NullItem();
    }

    @Override
    public IEquipableItem getTestItem() {
        return cleric.getNullItem();
    }

    /**
     * @return a unit that can equip the item being tested
     */
    @Override
    public IUnit getTestUnit() {
        return cleric;
    }

    @Override
    @Test
    public void equippedToTest() {
        assertEquals(getTestItem().getOwner(), getTestUnit());
        IUnit unit = getTestUnit();
        getTestItem().equipTo(unit);
        assertEquals(unit, getTestItem().getOwner());
    }
}
