package controller.create;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

public interface ICreate {

    /**
     *
     *
     * @param type
     * @param hitPoints
     * @param movement
     * @param location
     * @param items
     * @return
     */
    IUnit createUnit(String type, int hitPoints, int movement, Location location, IEquipableItem... items);

    /**
     *
     *
     * @param type
     * @param name
     * @param power
     * @param minRange
     * @param maxRange
     * @return
     */
    IEquipableItem createItem(String type, String name, int power, int minRange, int maxRange);
}
