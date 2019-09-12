package model.units.warrior;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * This class represents a <i>SwordMaster</i> type unit.
 * <p>
 * A <i>SwordMaster</i> is a unit that <b>can only use sword type weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class SwordMaster extends Warrior {

    public SwordMaster(final int hitPoints, final int movement, final Location location,
                       IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    @Override
    public void equipSword(IEquipableItem item) {
        equippedItem = item;
        item.setOwner(this);
    }
}
