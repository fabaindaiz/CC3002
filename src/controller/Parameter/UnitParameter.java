package controller.parameter;

import model.Tactician;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;

/**
 * Objeto que representa un parametro de la partida de tipo unit
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class UnitParameter extends AbstractParameter {

    private final String type;
    private final int maxHitPoints;
    private final int movement;
    private final Location location;
    private final int ownerTactician;

    /**
     * Constructor de un parametro tipo unit
     *
     * @param type           Tipo de la unidad
     * @param ownerTactician Tactician dueño de la unidad
     *                       <p>
     *                       Los demas parametros son los mismos que en el contructor de AbstractUnit
     */
    public UnitParameter(final String type, final int hitPoints, final int movement, final Location position, final int ownerTactician) {
        this.type = type;
        this.maxHitPoints = hitPoints;
        this.movement = movement;
        this.location = position;
        this.ownerTactician = ownerTactician;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void create(Field gameMap, ArrayList<Tactician> tacticians) {
        IUnit createdUnit = createUnit(type, maxHitPoints, movement, gameMap.getCell(location.getRow(), location.getColumn()));
        if (createdUnit != null) {
            Tactician tactician = tacticians.get(ownerTactician);
            tactician.addUnit(createdUnit);
        }
    }

}