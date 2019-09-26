package model;

import model.units.IUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Objeto que representa a un jugador
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class Tactician {

    private String name;
    protected final List<IUnit> units = new ArrayList<>();

    /**
     * Crea un jugador para el juego (se ejecuta desde GameController)
     */
    public Tactician() {
    }

    public Tactician(String name) {
        this.name = name;
    }

    public Tactician(String name, final IUnit... units) {
        this.name = name;
        this.units.addAll(Arrays.asList(units));

    }

    public String getName() {
        return name;
    }

    public List<IUnit> getUnits() { return units; }

    public void addUnit (IUnit unit) {units.add(unit);}


}
