package model.items.weapon;

import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * @author Ignacio Slater Muñoz
 * @since
 */
public class Bow extends Weapon {

    /**
     * Creates a new bow.
     * <p>
     * Bows are weapons that can't attack adjacent units, so it's minimum range must me greater than
     * one.
     *
     * @param name     the name of the bow
     * @param power    the damage power of the bow
     * @param minRange the minimum range of the bow
     * @param maxRange the maximum range of the bow
     */
    public Bow(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
        this.minRange = Math.max(minRange, 2);
        this.maxRange = Math.max(maxRange, this.minRange);
    }

    @Override
    public void useItem(IUnit other, boolean counterAttack) {
        other.getEquippedItem().receiveBowAttack(this, counterAttack);
    }

    @Override
    public void equipTo(final IUnit unit) {
        unit.equipBow(this);
    }

    @Override
    public void receiveAxeAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }

    @Override
    public void receiveSpearAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }

    @Override
    public void receiveSwordAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }
}
