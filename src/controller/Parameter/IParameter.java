package controller.Parameter;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;

public interface IParameter {

    /**
     * Crea el objeto dependiendo de sus parametros
     *
     * @param tacticians Lista con los tacticians
     */
    void create(ArrayList<Tactician> tacticians);

    /**
     * @param type
     * @param hitPoints
     * @param movement
     * @param location
     * @param items
     * @return
     */
    IUnit createUnit(String type, int hitPoints, int movement, Location location, IEquipableItem... items);

    /**
     * @param type
     * @param name
     * @param power
     * @param minRange
     * @param maxRange
     * @return
     */
    IEquipableItem createItem(String type, String name, int power, int minRange, int maxRange);
}