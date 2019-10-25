package model.units.sorcerer;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a cleric type unit. A cleric can only use staff type weapons, which means
 * that it can receive attacks but can't counter attack any of those.
 *
 * @author Fabián Díaz
 * @since 1.0
 */
public class Sorcerer extends MagicUnit {

    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     */
    public Sorcerer(final int hitPoints, final int movement, final Location location, IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    @Override
    public void equipMagicBook(IEquipableItem item) {
        this.equippedItem = item;
        item.setOwner(this);
    }
}
