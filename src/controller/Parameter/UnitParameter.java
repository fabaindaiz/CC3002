package controller.Parameter;

import controller.create.ICreate;
import model.Tactician;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;

public class UnitParameter extends AbstractParameter {

    private final String type;
    private final int maxHitPoints;
    private final int movement;
    private final Location location;
    private final int ownerTactician;

    public UnitParameter(final String type, final int hitPoints, final int movement, final Location position, final int ownerTactician) {
        this.type = type;
        this.maxHitPoints = hitPoints;
        this.movement = movement;
        this.location = position;
        this.ownerTactician = ownerTactician;
    }

    @Override
    public void create(ICreate factory, ArrayList<Tactician> tacticians) {
        IUnit createdUnit = factory.createUnit(type, maxHitPoints, movement, location, null);
        Tactician tactician = tacticians.get(ownerTactician);
        tactician.addUnit(createdUnit);
    }

}