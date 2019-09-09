package model.items.weapon;

import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents a <i>spear</i>.
 * <p>
 * Spears are strong against swords and weak against axes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Spear extends Weapon {

    /**
     * Creates a new Axe item
     *
     * @param name     the name of the Axe
     * @param power    the damage of the axe
     * @param minRange the minimum range of the axe
     * @param maxRange the maximum range of the axe
     */
    public Spear(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    /**
     * Performs a spear type attack.
     * {@inheritDoc}
     *
     * @param other Unit that receives the attack.
     */
    @Override
    public void attack(IUnit other, boolean counterAttack) {
        if (other.getEquippedItem() != null)
            other.getEquippedItem().receiveSpearAttack(this, counterAttack);
        else other.receiveAttack(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Spear;
    }

    @Override
    public void receiveAnimaAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveDarkAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveLightAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveStaffHeal(IEquipableItem item) {
        owner.receiveHeal(item);
    }

    @Override
    public void receiveAxeAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveSpearAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }

    @Override
    public void receiveSwordAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveResistantAttack(item, counterAttack);
    }

    @Override
    public void receiveBowAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }
}
