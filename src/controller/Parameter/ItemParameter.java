package controller.parameter;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

import java.util.ArrayList;

public class ItemParameter extends AbstractParameter {

    private final String type;
    private final String name;
    private final int power;
    private final int minRange;
    private final int maxRange;
    private final int ownerTactician;
    private final int ownerUnit;
    private final boolean equiped;


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
    public void create(Field gameMap, ArrayList<Tactician> tacticians) {
        IEquipableItem createdItem = createItem(type, name, power, minRange, maxRange);
        IUnit unit = tacticians.get(ownerTactician).getUnits().get(ownerUnit);
        unit.addItem(createdItem);
        if (equiped)
            unit.equipItem(createdItem);
    }

}