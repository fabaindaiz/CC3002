package model.items.magicbook;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents a Magic Book item.
 * <p>
 * An abstract item is a item that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * item.
 *
 * @author Fabian Diaz
 * @since 1.0
 */
public abstract class MagicBook extends AbstractItem {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public MagicBook(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public boolean counterattack() {
        return true;
    }

    @Override
    public void equipTo(final IUnit unit) {
        unit.equipMagicBook(this);
    }

    @Override
    public void noItemAttack(IUnit other) {
        other.receiveAttack(this, false);
    }

    @Override
    public void receiveAxeAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveSpearAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveSwordAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveBowAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }
}
