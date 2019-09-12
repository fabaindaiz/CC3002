package model.items.weapon;

import model.items.IEquipableItem;
import model.units.IUnit;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends Weapon {

    /**
     * Creates a new Sword.
     *
     * @param name     the name that identifies the weapon
     * @param power    the base damage pf the weapon
     * @param minRange the minimum range of the weapon
     * @param maxRange the maximum range of the weapon
     */
    public Sword(final String name, final int power, final int minRange, final int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public void specificAttack(IUnit other, boolean counterAttack) {
        other.getEquippedItem().receiveSwordAttack(this, counterAttack);
    }

    @Override
    public void equipTo(final IUnit unit) {
        unit.equipSword(this);
    }

    @Override
    public void receiveAxeAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveResistantAttack(item, counterAttack);
    }

    @Override
    public void receiveSpearAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveWeaknessAttack(item, counterAttack);
    }

    @Override
    public void receiveSwordAttack(IEquipableItem item, boolean counterAttack) {
        owner.receiveAttack(item, counterAttack);
    }
}
