package model.items;

import controller.parameter.IParameter;
import model.units.IUnit;

/**
 * Abstract class that defines some common information and behaviour between all items.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractItem implements IEquipableItem {

    private final String name;
    private final int power;
    protected int minRange;
    protected int maxRange;
    protected IUnit owner;
    private IParameter parameter;

    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange the maximum range of the item
     */
    protected AbstractItem(final String name, final int power, final int minRange, final int maxRange) {
        this.name = name;
        this.power = power;
        this.minRange = Math.max(minRange, 1);
        this.maxRange = Math.max(maxRange, this.minRange);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public int getMinRange() {
        return minRange;
    }

    @Override
    public int getMaxRange() {
        return maxRange;
    }

    @Override
    public IUnit getOwner() {
        return owner;
    }

    @Override
    public void setOwner(IUnit unit) {
        owner = unit;
    }

    @Override
    public void useItem(IUnit other, boolean counterAttack) { specificAttack(other, counterAttack); }

    @Override
    public void receiveStaffHeal(IEquipableItem item, boolean counterAttack) { owner.receiveHeal(item); }

}
