package model.items.weapon;

import model.items.IEquipableItem;
import model.items.weapon.Weapon;
import model.units.IUnit;

/**
 * This class represents an Axe.
 * <p>
 * Axes are strong against spears but weak agains swords.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Axe extends Weapon {

  /**
   * Creates a new Axe item
   *
   * @param name
   *     the name of the Axe
   * @param power
   *     the damage of the axe
   * @param minRange
   *     the minimum range of the axe
   * @param maxRange
   *     the maximum range of the axe
   */
  public Axe(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * Performs a axe type attack.
   * {@inheritDoc}
   *
   * @param other
   *     Unit that receives the attack.
   */
  @Override
  public void attack(IUnit other) {
    if(other.getEquippedItem() != null)
      other.getEquippedItem().receiveAxeAttack(this);
    else other.receiveAttack(this);
  }

  @Override
  public void receiveAnimaAttack(IEquipableItem item) { owner.receiveWeaknessAttack(item); }

  @Override
  public void receiveDarkAttack(IEquipableItem item) {
    owner.receiveWeaknessAttack(item);
  }

  @Override
  public void receiveLightAttack(IEquipableItem item) {
    owner.receiveWeaknessAttack(item);
  }

  @Override
  public void receiveStaffHeal(IEquipableItem item) {
    owner.receiveHeal(item);
  }

  @Override
  public void receiveAxeAttack(IEquipableItem item) {
    owner.receiveAttack(item);
  }

  @Override
  public void receiveSpearAttack(IEquipableItem item) { owner.receiveWeaknessAttack(item); }

  @Override
  public void receiveSwordAttack(IEquipableItem item) {  owner.receiveResistantAttack(item); }

  @Override
  public void receiveBowAttack(IEquipableItem item) {
    owner.receiveAttack(item);
  }
}
