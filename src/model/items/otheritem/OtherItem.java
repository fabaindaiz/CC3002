package model.items.otheritem;

import model.items.AbstractItem;

public abstract class OtherItem extends AbstractItem {
    /**
     * Constructor for a default item without any special behaviour.
     *
     * @param name     the name of the item
     * @param power    the power of the item (this could be the amount of damage or healing the item does)
     * @param minRange the minimum range of the item
     * @param maxRange
     */
    public OtherItem(String name, int power, int minRange, int maxRange) {
        super(name, power, minRange, maxRange);
    }

    @Override
    public boolean counterattack(){
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OtherItem;
    }
}
