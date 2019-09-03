package model.units;

import java.util.List;
import model.items.IEquipableItem;
import model.items.otheritem.Staff;
import model.map.Location;

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
   * @param item
   *     the item to equip
   */
  void equipItem(IEquipableItem item);

  /**
   * @return max hit points of the unit
   */
  int getMaxHitPoints();

  /**
   * @return hit points of the unit
   */
  int getCurrentHitPoints();

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
   * @param item
   *     the item to be equipped
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

  void muerte();

  /**
   * Attacks another Unit.
   *
   * @param other
   *     Target of the attack.
   */
  void attack(IUnit other);

  /**
   * Receives an heal.
   *
   * @param item
   *     Received heal.
   */
  void receiveHeal(IEquipableItem item);

  /**
   * Receives an attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveAttack(IEquipableItem item, boolean counterAttack);

  /**
   * Receives an attack to which this Weapon is weak.
   *
   * @param item
   *     Received attack.
   */
  void receiveWeaknessAttack(IEquipableItem item, boolean counterAttack);

  /**
   * Receives an attack to which this Weapon is resistant.
   *
   * @param item
   *     Received attack.
   */
  void receiveResistantAttack(IEquipableItem item, boolean counterAttack);

  boolean addItem(IEquipableItem item);

  void exchange(IUnit unit, IEquipableItem item);
}
