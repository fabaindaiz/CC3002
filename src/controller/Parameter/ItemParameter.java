package controller.parameter;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

import java.util.ArrayList;

/**
 * Objeto que representa un parametro de la partida de tipo item
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class ItemParameter extends AbstractParameter {

    private final String type;
    private final String name;
    private final int power;
    private final int minRange;
    private final int maxRange;
    private final int ownerTactician;
    private final int ownerUnit;
    private final boolean equiped;

    /**
     * Contructor de un parametro de tipo item
     *
     * @param type           Tipo del item
     * @param ownerTactician Tactician dueño del item
     * @param ownerUnit      Unidad dueño del item
     * @param equiped        Determina si se equipa al crearse
     *                       <p>
     *                       Los demas parametros son los mismos que en el contructor de AbstractItem
     */
    public ItemParameter(final String type, final String name, final int power, final int minRange, final int maxRange,
                         final int ownerTactician, final int ownerUnit, final boolean equiped) {
        this.type = type;
        this.name = name;
        this.power = power;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.ownerTactician = ownerTactician;
        this.ownerUnit = ownerUnit;
        this.equiped = equiped;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void create(Field gameMap, ArrayList<Tactician> tacticians) {
        IEquipableItem createdItem = createItem(type, name, power, minRange, maxRange);
        if (createdItem != null) {
            IUnit unit = tacticians.get(ownerTactician).getUnits().get(ownerUnit);
            unit.addItem(createdItem);
            if (equiped)
                unit.equipItem(createdItem);
        }
    }

}