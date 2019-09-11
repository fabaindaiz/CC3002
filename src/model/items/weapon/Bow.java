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

    /**
     * Performs a bow type attack.
     * {@inheritDoc}
     *
     * @param other Unit that receives the attack.
     */
    @Override
    public void attack(IUnit other, boolean counterAttack) {
        if (other.getEquippedItem() != null)
            other.getEquippedItem().receiveBowAttack(this, counterAttack);
        else other.receiveAttack(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Bow;
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
