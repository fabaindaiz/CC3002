package controller.observer;

import model.Tactician;
import model.units.IUnit;

/**
 * Objeto que representa un observer que verifica cuando una unidad es derrotado
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class DefeatUnitObserver implements IObserver {

    Tactician tactician;
    IUnit unit;

    /**
     * Crea un observer que verifica cuando una unidad es derrotado
     *
     * @param tactician dueño de la unidad a observar
     * @param unit      unidad a observar
     */
    public DefeatUnitObserver(Tactician tactician, IUnit unit) {
        this.tactician = tactician;
        this.unit = unit;
    }

    @Override
    public void update() {
        if (unit.getLocation() == null)
            if (unit.defeatCondition()) {
                tactician.removeAllUnit();
                tactician.notifyObservers();
            } else
                tactician.removeUnit(unit);
    }
}
