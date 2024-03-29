package model.items.weapon;

import model.items.AbstractItem;
import model.items.IAttackItem;
import model.items.IEquipableItem;
import model.items.INoAttackItem;
import model.units.IUnit;

/**
 * This class represents a weapon item.
 * <p>
 * An abstract item is a item that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * item.
 *
 * @author Fabian Diaz
 * @since 1.0
 */
public abstract class Weapon extends AbstractItem implements IAttackItem {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public Weapon(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
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
    public void receiveBowAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }
}
