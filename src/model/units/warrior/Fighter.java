package model.units.warrior;

import model.items.weapon.Axe;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.AbstractUnit;

/**
 * This class represents a fighter type unit.
 * A fighter is a unit that can only use axe type weapons.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class Fighter extends Warrior {

  public Fighter(final int hitPoints, final int movement, final Location location,
      IEquipableItem... items) {
    super(hitPoints, movement, location, 3, items);
  }

  /**
   * Sets the currently equipped item of this unit.
   *
   * @param item
   *     the item to equip
   */
  @Override
  public void equipItem(final IEquipableItem item) {
    if (item instanceof Axe) {
      equippedItem = item;
      item.setOwner(this);
    }
  }
}
