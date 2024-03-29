package model;

import controller.observer.IObserver;
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
     * Añade un observador al Tactician
     *
     * @param observer Observador a añadir
     */
    void addObserver(IObserver observer);

    /**
     * Restaura las variables que indican si un turno fue usado para todas las unidades del Tactician
     */
    void setNewTurn();

    /**
     * @return el nombre del Tactician
     */
    String getName();

    /**
     * @return el nombre de la unidad
     */
    int getTacticianNumber();

    /**
     * @return Una lista con ls unidades del Tactician
     */
    List<IUnit> getUnits();

    /**
     * Notifica a todos los observadores
     */
    void notifyObservers();

    /**
     * @return la unidad nula a usar
     */
    IUnit getNullUnit();

    /**
     * @return el item nulo a usar
     */
    IEquipableItem getNullItem();

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

    /**
     * Elimina todas las unidades del Tactician
     */
    void removeAllUnit();

    /**
     * Selects a unit in the game map
     *
     * @param x horizontal position of the unit
     * @param y vertical position of the unit
     */
    void selectUnitIn(int x, int y);

    /**
     * Selectes a unit in the ArrayList unit in Tactician
     *
     * @param index the location of the unit in the inventory.
     */
    void selectUnitId(int index);

    /**
     * @return the current player's selected unit
     */
    IUnit getSelectedUnit();

    /**
     * @return the inventory of the currently selected unit.
     */
    List<IEquipableItem> getItems();

    /**
     * Selects an item from the selected unit's inventory.
     *
     * @param index the location of the item in the inventory.
     */
    void selectItem(int index);

    /**
     * @return El item seleccionado por selectItem(index)
     */
    IEquipableItem getSelectedItem();

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index the location of the item in the inventory.
     */
    void equipItem(int index);

    /**
     * Uses the equipped item on a target
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    void useItemOn(int x, int y);

    /**
     * Gives the selected item to a target unit.
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    void giveItemTo(int x, int y);

    /**
     * Mueve a la unidad selecionada al destino
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    void moveUnitTo(int x, int y);
}
