package model.units;

import model.items.IEquipableItem;
import model.map.Location;

public class NullUnit extends AbstractUnit {

    public NullUnit() {
        super(0, 0, null, 0);
        super.setMaxAction(0);
    }

    public NullUnit(Location location) {
        super(0, 0, location, 0);
        super.setMaxAction(0);
    }

    @Override
    public void setMaxAction(int maxAction) {
    }

    @Override
    public void moveTo(final Location targetLocation) {}

    @Override
    public boolean addItem(IEquipableItem item) { return false; }

    @Override
    public void useItem(IUnit other, boolean counterattack) {}

    @Override
    public void equipItem(final IEquipableItem item) {}

    @Override
    public void receiveHeal(IEquipableItem item) {}

    @Override
    public void receiveAttack(IEquipableItem item, boolean counterAttack) {}

    @Override
    public void exchange(IUnit unit, IEquipableItem item) {}
}
