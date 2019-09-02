package model.items.magicbook;

import model.items.IEquipableItem;
import model.items.magicbook.MagicBook;
import model.units.IUnit;

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

    /**
     * Performs a light type attack.
     * {@inheritDoc}
     *
     * @param other
     *     Unit that receives the attack.
     */
    @Override
    public void attack(IUnit other) { other.getEquippedItem().receiveLightAttack(this); }

    @Override
    public void receiveAnimaAttack(IEquipableItem item) { owner.receiveAttack(item); }

    @Override
    public void receiveDarkAttack(IEquipableItem item) {
        owner.receiveWeaknessAttack(item);
    }

    @Override
    public void receiveLightAttack(IEquipableItem item) {
        owner.receiveResistantAttack(item);
    }

    @Override
    public void receiveStaffHeal(IEquipableItem item) {
        owner.receiveHeal(item);
    }

    @Override
    public void receiveAxeAttack(IEquipableItem item) {
        owner.receiveWeaknessAttack(item);
    }

    @Override
    public void receiveSpearAttack(IEquipableItem item) {
        owner.receiveWeaknessAttack(item);
    }

    @Override
    public void receiveSwordAttack(IEquipableItem item) {  owner.receiveWeaknessAttack(item); }

    @Override
    public void receiveBowAttack(IEquipableItem item) {
        owner.receiveWeaknessAttack(item);
    }
}
