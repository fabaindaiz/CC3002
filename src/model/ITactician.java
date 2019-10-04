package model;

import model.items.IEquipableItem;
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

    void removeAllUnit();

    void selectUnitIn(int x, int y);

    void selectUnitId(int index);

    IUnit getSelectedUnit();

    List<IEquipableItem> getItems();

    void selectItem(int index);

    IEquipableItem getSelectedItem();

    void equipItem(int index);

    void useItemOn(int x, int y);

    void giveItemTo(int x, int y);
}
