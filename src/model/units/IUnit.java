package model.units;

import controller.observer.IObserver;
import model.items.IEquipableItem;
import model.map.Location;

import java.util.List;

/**
 * This interface represents all units in the game.
 * <p>
 * The signature of all the common methods that a unit can execute are defined here. All units
 * except some special ones can carry at most 3 weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IUnit {

    /**
     * Sets the currently equipped item of this unit.
     *
     * @param item the item to equip
     */
    void equipItem(IEquipableItem item);

    /**
     * @return boolean que indica si el Tactician pierde si esta unidad es derrotada
     */
    boolean defeatCondition();

    /**
     * @param observer
     */
    void addObserver(IObserver observer);

    /**
     * @return max hit points of the unit
     */
    int getMaxHitPoints();

    /**
     * @return hit points of the unit
     */
    int getCurrentHitPoints();

    /**
     * Configura la vida de la unidad a una especificada
     */
    void setCurrentHitPoints(int HP);

    /**
     * @return the number of cells this unit can move
     */
    int getMovement();

    /**
     * @return max item slots of the unit
     */
    int getMaxItems();

    /**
     * @return the current location of the unit
     */
    Location getLocation();

    /**
     * @return the items carried by this unit
     */
    List<IEquipableItem> getItems();

    /**
     * @return the currently equipped item
     */
    IEquipableItem getEquippedItem();

    /**
     * Moves this unit to another location.
     * <p>
     * If the other location is out of this unit's movement range, the unit doesn't move.
     */
    void moveTo(Location targetLocation);

    /**
     * Añade un item al inventario de la unidad
     *
     * @param item Es el item que se quiere añadir
     * @return si se pudo añadir el item
     * TRUE si se logro
     */
    boolean addItem(IEquipableItem item);

    /**
     * Attacks another Unit.
     *
     * @param other Target of the attack.
     */
    void useItem(IUnit other, boolean counterattack);

    /**
     * Receives an heal.
     *
     * @param item Received heal.
     */
    void receiveHeal(IEquipableItem item);

    /**
     * Receives an attack.
     *
     * @param item Received attack.
     */
    void receiveAttack(IEquipableItem item, boolean counterAttack);

    /**
     * Receives an attack to which this Weapon is weak.
     *
     * @param item Received attack.
     */
    void receiveWeaknessAttack(IEquipableItem item, boolean counterAttack);

    /**
     * Receives an attack to which this Weapon is resistant.
     *
     * @param item Received attack.
     */
    void receiveResistantAttack(IEquipableItem item, boolean counterAttack);

    /**
     * Entrega un item a otra unidad
     *
     * @param unit Unidad con la que se queire intercambiar
     * @param item Item que se quiere entregar
     */
    void exchange(IUnit unit, IEquipableItem item);

    /**
     * Metodos para equipar las armas
     * Las unidades que pueden ocuparlos hacen Override con sus propios metodos
     */

    /**
     * Equipa un item Magico en la unidad
     *
     * @param item Item a equipar
     */
    void equipMagicBook(IEquipableItem item);

    /**
     * Equipa una hacha a la unidad
     *
     * @param item Item a equipar
     */
    void equipAxe(IEquipableItem item);

    /**
     * Equipa una lanza a la unidad
     *
     * @param item Item a equipar
     */
    void equipSpear(IEquipableItem item);

    /**
     * Equipa una espada a la unidad
     *
     * @param item Item a equipar
     */
    void equipSword(IEquipableItem item);

    /**
     * Equipa un arco a la unidad
     *
     * @param item Item a equipar
     */
    void equipBow(IEquipableItem item);

    /**
     * Equipa un staff a la unidad
     *
     * @param item Item a equipar
     */
    void equipStaff(IEquipableItem item);

}
