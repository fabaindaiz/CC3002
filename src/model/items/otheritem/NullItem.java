package model.items.otheritem;

import model.items.AbstractItem;
import model.items.IEquipableItem;
import model.units.IUnit;

public class NullItem extends OtherItem {

    public NullItem() {
        super(null, 0, 1, 1);
    }

    @Override
    public void equipTo(IUnit unit) {
        unit.equipNullItem();
    }

    @Override
    public void specificAttack(IUnit other, boolean counterAttack) {}
}
