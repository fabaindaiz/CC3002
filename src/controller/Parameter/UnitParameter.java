package controller.Parameter;

import model.Tactician;
import model.map.Location;
import model.units.IUnit;

public class UnitParameter extends AbstractParameter {

    private final int maxHitPoints;
    private final int movement;
    private final Location location;
    private final Tactician owner;

    public UnitParameter(final int hitPoints, final int movement, final Location position, final Tactician owner) {
        this.maxHitPoints = hitPoints;
        this.movement = movement;
        this.location = position;
        this.owner = owner;
    }

    @Override
    public void create(Tactician tactician, IUnit unit) {

    }

}
