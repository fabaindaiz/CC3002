package controller.parameter;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;

public interface IParameter {

    /**
     * Crea el objeto dependiendo de sus parametros
     *
     * @param tacticians Lista con los tacticians
     */
    void create(Field gameMap, ArrayList<Tactician> tacticians);

    /**
     * Crea a una unidad especifica
     *
     * @param type Tipo de unidad
     * El resto de los parametros son los mismos que desde AbstractUnit
     *
     * @return la unidad creada
     */
    IUnit createUnit(String type, int hitPoints, int movement, Location location, IEquipableItem... items);

    /**
     * Crea un item específico
     *
     * @param type
     * El resto de los parametros son los mismos que desde AbstractItem

     * @return el item Creado
     */
    IEquipableItem createItem(String type, String name, int power, int minRange, int maxRange);
}