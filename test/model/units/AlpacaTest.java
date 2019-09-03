package model.units;

import model.items.IEquipableItem;
import model.units.otherunit.Alpaca;

/**
 * Test set for the alpaca unit
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public class AlpacaTest extends AbstractTestUnit {

  private Alpaca alpaca;

  @Override
  public void setTestUnit() {
    alpaca = new Alpaca(50, 2, field.getCell(0, 0));
  }

  @Override
  public IEquipableItem getWeapon() {return null;}

  @Override
  public Alpaca getTestUnit() {
    return alpaca;
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