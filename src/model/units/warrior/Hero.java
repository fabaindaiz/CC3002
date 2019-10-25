package model.units.warrior;

import model.items.IEquipableItem;
import model.map.Location;

/**
 * A <i>Hero</i> is a special kind of unit, the player that defeats this unit wins the game.
 * <p>
 * This unit <b>can only use spear weapons</b>.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Hero extends Warrior {

    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     */
    public Hero(final int hitPoints, final int movement, final Location location, IEquipableItem... items) {
        super(hitPoints, movement, location, 3, items);
    }

    @Override
    public boolean defeatCondition() {
        return true;
    }

    @Override
    public void equipSpear(IEquipableItem item) {
        this.equippedItem = item;
        item.setOwner(this);
    }
}
