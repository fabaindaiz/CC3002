package model.items.weapon;

import model.items.IEquipableItem;
import model.items.weapon.Weapon;
import model.units.IUnit;

/**
 * This class represents a sword type item.
 * <p>
 * Swords are strong against axes and weak against spears.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Sword extends Weapon {

  /**
   * Creates a new Sword.
   *
   * @param name
   *     the name that identifies the weapon
   * @param power
   *     the base damage pf the weapon
   * @param minRange
   *     the minimum range of the weapon
   * @param maxRange
   *     the maximum range of the weapon
   */
  public Sword(final String name, final int power, final int minRange, final int maxRange) {
    super(name, power, minRange, maxRange);
  }

  /**
   * Performs a sword type attack.
   * {@inheritDoc}
   *
   * @param other
   *     Unit that receives the attack.
   */
  @Override
  public void attack(IUnit other) {
    if(other.getEquippedItem() != null)
    other.getEquippedItem().receiveSwordAttack(this);
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
    owner.receiveResistantAttack(item);
  }

  @Override
  public void receiveSpearAttack(IEquipableItem item) {
    owner.receiveWeaknessAttack(item);
  }

  @Override
  public void receiveSwordAttack(IEquipableItem item) {  owner.receiveAttack(item); }

  @Override
  public void receiveBowAttack(IEquipableItem item) {
    owner.receiveAttack(item);
  }
}
