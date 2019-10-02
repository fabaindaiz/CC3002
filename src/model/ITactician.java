package model;

import model.units.IUnit;

import java.util.List;

/**
 * This interface represents a Tactician.
 * <p>
 * Este metodo es usado para agrupar la documentación y para facilitar algunas implementaciones
 *
 * @author Fabián Díaz
 * @since 2.0
 */
public interface ITactician {

    /**
     * @return el nombre del Tactician
     */
    String getName();

    /**
     * @return Una lista con ls unidades del Tactician
     */
    List<IUnit> getUnits();

    /**
     * Añade unidades a un Tactician
     * Si ya hay una unidad en ese Location, la unidad no se añade
     *
     * @param unit unidad o unidades a añadir
     */
    void addUnit(IUnit unit);

    /**
     * Elimina unidades de un Tactician
     *
     * @param unit unidad a eliminar
     */
    void removeUnit(IUnit unit);
}