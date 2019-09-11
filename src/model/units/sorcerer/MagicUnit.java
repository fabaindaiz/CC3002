package model.units.sorcerer;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.AbstractUnit;

public abstract class MagicUnit extends AbstractUnit {
    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems
     * @param items
     */
    protected MagicUnit(int hitPoints, int movement, Location location, int maxItems, IEquipableItem... items) {
        super(hitPoints, movement, location, maxItems, items);
    }
}
