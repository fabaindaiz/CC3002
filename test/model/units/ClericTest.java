package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import model.items.IEquipableItem;
import model.units.otherunit.Cleric;
import org.junit.jupiter.api.Test;

/**
 * @author Ignacio Slater Muñoz
 */
public class ClericTest extends AbstractTestUnit {

  private Cleric cleric;

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public void setTestUnit() {
    cleric = new Cleric(50, 2, field.getCell(0, 0));
  }

  @Override
  public IEquipableItem getWeapon() {return staff;}

  /**
   * @return the current unit being tested
   */
  @Override
  public IUnit getTestUnit() {
    return cleric;
  }

  @Test
  @Override
  public void equipStaffTest() {
    assertNull(cleric.getEquippedItem());
    cleric.equipItem(staff);
    assertEquals(staff, cleric.getEquippedItem());
  }

  @Override
  public int getHPanima() {return 50;}

  @Override
  public int getHPdark() {return 50;}

  @Override
  public int getHPlight() {return 50;}

  @Override
  public int getHPaxe() {return 50;}

  @Override
  public int getHPspear() {return 50;}

  @Override
  public int getHPsword() {return 50;}

  @Override
  public int getHPstaff() {return 50;}

  @Override
  public int getHPbow() {return 50;}
}