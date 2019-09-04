package model.items.magicbook;

import model.items.IEquipableItem;
import model.units.IUnit;

public class Anima extends MagicBook {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public Anima(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    /**
     * Performs a anima type attack.
     * {@inheritDoc}
     *
     * @param other
     *     Unit that receives the attack.
     */
    @Override
    public void attack(IUnit other, boolean counterAttack) {
        if(other.getEquippedItem() != null)
            other.getEquippedItem().receiveAnimaAttack(this, counterAttack);
        else other.receiveAttack(this,false);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Anima;
    }

    @Override
    public void receiveAnimaAttack(IEquipableItem item, boolean counterAttack) { owner.receiveAttack(item, counterAttack); }

    @Override
    public void receiveDarkAttack(IEquipableItem item, boolean counterAttack) { owner.receiveWeaknessAttack(item, counterAttack); }

    @Override
    public void receiveLightAttack(IEquipableItem item, boolean counterAttack) { owner.receiveResistantAttack(item, counterAttack); }

    @Override
    public void receiveStaffHeal(IEquipableItem item) {
        owner.receiveHeal(item);
    }

    @Override
    public void receiveAxeAttack(IEquipableItem item, boolean counterAttack) { owner.receiveWeaknessAttack(item, counterAttack); }

    @Override
    public void receiveSpearAttack(IEquipableItem item, boolean counterAttack) { owner.receiveWeaknessAttack(item, counterAttack); }

    @Override
    public void receiveSwordAttack(IEquipableItem item, boolean counterAttack) {  owner.receiveWeaknessAttack(item, counterAttack); }

    @Override
    public void receiveBowAttack(IEquipableItem item, boolean counterAttack) { owner.receiveWeaknessAttack(item, counterAttack); }
}
