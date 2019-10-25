package model.items.weapon;

import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends Weapon {

    /**
     * Creates a new Axe item
     *
     * @param name     the name of the Axe
     * @param power    the damage of the axe
     * @param minRange the minimum range of the axe
     * @param maxRange the maximum range of the axe
     */
    public Axe(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void useItem(IUnit other, boolean counterAttack) {
        other.getEquippedItem().receiveAxeAttack(this, counterAttack);
    }

    @Override
    public void equipTo(final IUnit unit) {
        unit.equipAxe(this);
    }

    @Override
    public void receiveAxeAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }

    @Override
    public void receiveSpearAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveResistantAttack(item, counterAttack);
    }

    @Override
    public void receiveSwordAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }
}
