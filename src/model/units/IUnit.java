package model.units;

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
     * Verifica si una unidad esta viva o muerta
     *
     * @return es estado de la unidad
     * TRUE si esta viva
     * FALSE si esta muerta
     */
    boolean getDeathStatus();

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
     * @return max item slots of the unit
     */
    int getMaxItems();

    /**
     * @return the items carried by this unit
     */
    List<IEquipableItem> getItems();

    /**
     * @return the currently equipped item
     */
    IEquipableItem getEquippedItem();

    /**
     * @param item the item to be equipped
     */
    void setEquippedItem(IEquipableItem item);

    /**
     * @return the current location of the unit
     */
    Location getLocation();

    /**
     * Sets a new location for this unit,
     */
    void setLocation(final Location location);

    /**
     * @return the number of cells this unit can move
     */
    int getMovement();

    /**
     * Moves this unit to another location.
     * <p>
     * If the other location is out of this unit's movement range, the unit doesn't move.
     */
    void moveTo(Location targetLocation);

    /**
     * Mata a esta unidad, sacandola del mapa
     * De esta forma nadie puede interactuar con ella.
     * (Despues del SMAAAASH!! unit ha recibido daño mortal)
     */
    void muerte();

    /**
     * Attacks another Unit.
     *
     * @param other Target of the attack.
     */
    void attack(IUnit other, boolean counterattack);

    /**
     * Verifica si el ataque esta fuera de rango
     *
     * @return un valor booleano segun la condicion
     * TRUE es fuera de rango
     * FALSE es dentro de rango
     */
    boolean outOfRange(IUnit unit);

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
     * Añade un item al inventario de la unidad
     *
     * @param item Es el item que se quiere añadir
     * @return si se pudo añadir el item
     * TRUE si se logro
     */
    boolean addItem(IEquipableItem item);

    /**
     * Entrega un item a otra unidad
     *
     * @param unit Unidad con la que se queire intercambiar
     * @param item Item que se quiere entregar
     */
    void exchange(IUnit unit, IEquipableItem item);
}
