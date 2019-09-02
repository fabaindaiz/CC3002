package model.items.magicbook;

import model.items.IEquipableItem;
import model.items.magicbook.MagicBook;
import model.units.IUnit;

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

    /**
     * Performs a dark type attack.
     * {@inheritDoc}
     *
     * @param other
     *     Unit that receives the attack.
     */
    @Override
    public void attack(IUnit other) {
        if(other.getEquippedItem() != null)
            other.getEquippedItem().receiveDarkAttack(this);
        else other.receiveAttack(this);
    }

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
