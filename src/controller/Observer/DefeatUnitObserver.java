package controller.observer;

import model.Tactician;
import model.units.IUnit;

public class DefeatUnitObserver extends Observer {

    Tactician tactician;
    IUnit unit;

    public DefeatUnitObserver (Tactician tactician, IUnit unit){
        this.tactician = tactician;
        this.unit = unit;
    }

    @Override
    public void update() {
        if (unit.getLocation() == null)
            tactician.removeUnit(unit);
    }
}
