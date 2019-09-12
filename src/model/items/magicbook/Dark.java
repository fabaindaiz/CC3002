package model.items.magicbook;

import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents a <i>Dark</i> type item.
 * <p>
 * Dark are strong against Anima and weak against Light
 *
 * @author Fabian Diaz
 * @since 1.0
 */
public class Dark extends MagicBook {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public Dark(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void specificAttack(IUnit other, boolean counterAttack) {
        other.getEquippedItem().receiveDarkAttack(this, counterAttack);
    }

    @Override
    public void receiveAnimaAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveResistantAttack(item, counterAttack);
    }

    @Override
    public void receiveDarkAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }

    @Override
    public void receiveLightAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }
}
