package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.IEquipableItem;
import model.units.warrior.Fighter;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class FighterTest extends AbstractTestUnit {

  private Fighter fighter;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    fighter = new Fighter(50, 2, field.getCell(0, 0));
  }

  @Override
  public IEquipableItem getWeapon() {return axe;}

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return fighter;
  }

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  @Override
  public void equipAxeTest() {
    assertNull(fighter.getEquippedItem());
    fighter.equipItem(axe);
    assertEquals(axe, fighter.getEquippedItem());
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
  public int getHPspear() {return 35;}

  @Override
  public int getHPsword() {return 50;}

  @Override
  public int getHPstaff() {return 50;}

  @Override
  public int getHPbow() {return 40;}
}