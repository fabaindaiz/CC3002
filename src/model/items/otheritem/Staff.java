package model.items.otheritem;

import model.units.IUnit;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units nut cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends OtherItem {

    /**
     * Creates a new Staff item.
     *
     * @param name     the name of the staff
     * @param power    the healing power of the staff
     * @param minRange the minimum range of the staff
     * @param maxRange the maximum range of the staff
     */
    public Staff(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void useItem(IUnit other, boolean counterAttack) {
        other.getEquippedItem().receiveStaffHeal(this, counterAttack);
    }

    @Override
    public void equipTo(final IUnit unit) {
        unit.equipStaff(this);
    }

}
