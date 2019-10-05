package controller.create;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

public abstract class AbstractFactory implements ICreate {

    public IUnit createUnit(String type, int hitPoints, int movement, Location location, IEquipableItem... items) {
        return null;
    }
}
