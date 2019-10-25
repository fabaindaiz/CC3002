package model.items.magicbook;

import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents a <i>Light</i> type item.
 * <p>
 * Light are strong against Dark and weak against Anima
 *
 * @author Fabian Diaz
 * @since 1.0
 */
public class Light extends MagicBook {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public Light(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void useItem(IUnit other, boolean counterAttack) {
        other.getEquippedItem().receiveLightAttack(this, counterAttack);
    }

    @Override
    public void receiveAnimaAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveDarkAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveResistantAttack(item, counterAttack);
    }

    @Override
    public void receiveLightAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }
}
