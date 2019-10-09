package controller.parameter;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;

/**
 * Interfaz que representa un parametro de la partida
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public interface IParameter {

    /**
     * @return el tipo de el item o la unidad
     */
    String getType();

    /**
     * @return la ubicación de la unidad
     */
    Location getLocation();

    /**
     * @return el nombre del item
     */
    String getName();

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