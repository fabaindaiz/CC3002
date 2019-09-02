package model.items;

import model.units.IUnit;

/**
 * This interface represents the <i>weapons</i> that the units of the game can use.
 * <p>
 * The signature for all the common methods of the weapons are defined here. Every weapon have a
 * base damage and is strong or weak against other type of weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface IEquipableItem {

  /**
   * Equips this item to a unit.
   *
   * @param unit
   *     the unit that will be quipped with the item
   */
  void equipTo(IUnit unit);

  /**
   * @return the unit that has currently equipped this item
   */
  IUnit getOwner();

  /**
   * @return the name of the item
   */
  String getName();

  /**
   * @return the power of the item
   */
  int getPower();

  /**
   * @return the minimum range of the item
   */
  int getMinRange();

  /**
   * @return the maximum range of the item
   */
  int getMaxRange();

  /**
   * An attack damages a Unit based on the attack's base damage, it's type and the type of the
   * Item that receives the attack.
   *
   * @param other
   *     Unit that receives the attack.
   */
  void attack(IUnit other);

  /**
   * Receives damage from a Anima attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveAnimaAttack(IEquipableItem item);

  /**
   * Receives damage from a Dark attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveDarkAttack(IEquipableItem item);

  /**
   * Receives damage from a Light attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveLightAttack(IEquipableItem item);

  /**
   * Receives heal from a Staff attack.
   *
   * @param item
   *     Received heal.
   */
  void receiveStaffHeal(IEquipableItem item);

  /**
   * Receives damage from a Axe attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveAxeAttack(IEquipableItem item);

  /**
   * Receives damage from a Spear attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveSpearAttack(IEquipableItem item);

  /**
   * Receives damage from a Sword attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveSwordAttack(IEquipableItem item);

  /**
   * Receives damage from a Bow attack.
   *
   * @param item
   *     Received attack.
   */
  void receiveBowAttack(IEquipableItem item);
}
