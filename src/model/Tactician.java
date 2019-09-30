package model;

import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que representa a un jugador
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class Tactician implements ITactician, Cloneable {

    private final String name;
    protected final List<IUnit> units = new ArrayList<>();

    /**
     * Crea un jugador para el juego (se ejecuta desde GameController)
     */
    public Tactician(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IUnit> getUnits() { return units; }

    @Override
    public Tactician clone(){
        Tactician clon = null;
        try { clon=(Tactician) super.clone();
        } catch (CloneNotSupportedException ex) {}
        return clon;
    }

    @Override
    public void addUnit(IUnit unit) {
        units.add(unit);
    }

    @Override
    public void removeUnit(IUnit unit) {
        unit.getLocation().setUnit(null);
        units.remove(unit);
    }

}
