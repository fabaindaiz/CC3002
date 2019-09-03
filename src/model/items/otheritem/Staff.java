package model.items.otheritem;

import model.items.IEquipableItem;
import model.items.otheritem.OtherItem;
import model.units.IUnit;

/**
 * This class represents a <i>Staff</i> type item.
 * <p>
 * A staff is an item that can heal other units nut cannot counter any attack
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Staff extends OtherItem {

  /**
   * Creates a new Staff item.
   *
   * @param name
   *     the name of the staff
   * @param power
   *     the healing power of the staff
   * @param minRange
   *     the minimum range of the staff
   * @param maxRange
   *     the maximum range of the staff
   */
  public Staff(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * Performs a staff type attack.
   * {@inheritDoc}
   *
   * @param other
   *     Unit that receives the attack.
   */
  @Override
  public void attack(IUnit other, boolean counterAttack) {
    if(other.getEquippedItem() != null)
      other.getEquippedItem().receiveStaffHeal(this);
    else other.receiveHeal(this);
  }

  @Override
  public void receiveAnimaAttack(IEquipableItem item, boolean counterAttack) { owner.receiveAttack(item, counterAttack); }

  @Override
  public void receiveDarkAttack(IEquipableItem item, boolean counterAttack) { owner.receiveAttack(item, counterAttack); }

  @Override
  public void receiveLightAttack(IEquipableItem item, boolean counterAttack) { owner.receiveAttack(item, counterAttack); }

  @Override
  public void receiveStaffHeal(IEquipableItem item) {
    owner.receiveHeal(item);
  }

  @Override
  public void receiveAxeAttack(IEquipableItem item, boolean counterAttack) {
    owner.receiveAttack(item, counterAttack);
  }

  @Override
  public void receiveSpearAttack(IEquipableItem item, boolean counterAttack) { owner.receiveAttack(item, counterAttack); }

  @Override
  public void receiveSwordAttack(IEquipableItem item, boolean counterAttack) {  owner.receiveAttack(item, counterAttack); }

  @Override
  public void receiveBowAttack(IEquipableItem item, boolean counterAttack) {
    owner.receiveAttack(item, counterAttack);
  }
}
