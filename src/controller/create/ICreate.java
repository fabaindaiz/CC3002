package controller.create;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

public interface ICreate {

    IUnit createUnit(String type, int hitPoints, int movement, Location location, IEquipableItem... items);
}
