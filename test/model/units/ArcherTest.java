package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.IEquipableItem;
import model.units.warrior.Archer;
import org.junit.jupiter.api.Test;

/**
 * Test set for the Archer unit.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class ArcherTest extends AbstractTestUnit {

  private Archer archer;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    archer = new Archer(50, 2, field.getCell(0, 0));
  }

  @Override
  public IEquipableItem getWeapon() {return bow;}

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return archer;
  }

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipBowTest() {
    assertNull(archer.getEquippedItem());
    archer.equipItem(bow);
    assertEquals(bow, archer.getEquippedItem());
  }

  @Override
  public int getHPanima() {return 35;}

  @Override
  public int getHPdark() {return 35;}

  @Override
  public int getHPlight() {return 35;}

  @Override
  public int getHPaxe() {return 40;}

  @Override
  public int getHPspear() {return 40;}

  @Override
  public int getHPsword() {return 40;}

  @Override
  public int getHPstaff() {return 40;}

  @Override
  public int getHPbow() {return 40;}
}