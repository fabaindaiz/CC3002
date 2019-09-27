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

    private final String name;
    protected final List<IUnit> units = new ArrayList<>();

    /**
     * Crea un jugador para el juego (se ejecuta desde GameController)
     */
    public Tactician(String name) {
        this.name = name;
    }

    /**
     * @return el nombre del Tactician
     */
    public String getName() {
        return name;
    }

    /**
     * @return Una lista con ls unidades del Tactician
     */
    public List<IUnit> getUnits() { return units; }

    /**
     * Añade unidades a un Tactician
     *
     * @param unit unidad o unidades a añadir
     */
    public void addUnit (IUnit... unit) {units.addAll(Arrays.asList(unit));}

    /**
     * Elimina unidades de un Tactician
     *
     * @param unit unidad a eliminar
     */
    public void removeUnit (IUnit unit) {units.remove(unit);}



}
