package model.units;


import model.items.*;
import model.items.magicbook.Anima;
import model.items.magicbook.Dark;
import model.items.magicbook.Light;
import model.items.otheritem.Staff;
import model.items.weapon.Axe;
import model.items.weapon.Bow;
import model.items.weapon.Spear;
import model.items.weapon.Sword;
import model.map.Field;
import model.units.otherunit.Alpaca;
import org.junit.jupiter.api.Test;

/**
 * Interface that defines the common behaviour of all the test for the units classes
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public interface ITestUnit {

  /**
   * Configura la unidad para los test de intercambio
   */
  void setTargetIntercambio();

  /**
   * Set up the game field
   */
  void setField();

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  void setTestUnit();

  /**
   * Configura la alpaca para los test
   */
  void setTargetAlpaca();

  /**
   * Creates a set of testing weapons
   */
  void setWeapons();

  /**
   * Checks that the constructor works properly.
   */
  @Test
  void constructorTest();

  /**
   * Prueba el funcionamiento basico del intercambio
   */
  @Test
  void intercambioTest();

  /**
   * Prueba casos borde de intercambio
   * Casos:
   * - Llenar un inventario
   * - Transferir un objeto inexistente
   * - Tener varios del mismo objeto
   * - Transferir un objeto duplicado
   */
  @Test
  void llenarInventarioTest();

  /**
   * @return the current unit being tested
   */
  IUnit getTestUnit();

  /**
   * Checks if the anima is equipped correctly to the unit
   */
  @Test
  void equipAnimaTest();

  /**
   * @return the test anima
   */
  Anima getAnima();

  /**
   * Checks if the dark is equipped correctly to the unit
   */
  @Test
  void equipDarkTest();

  /**
   * @return the test dark
   */
  Dark getDark();

  /**
   * Checks if the light is equipped correctly to the unit
   */
  @Test
  void equipLightTest();

  /**
   * @return the test light
   */
  Light getLight();

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  void checkEquippedItem(IEquipableItem item);

  /**
   * Checks if the axe is equipped correctly to the unit
   */
  @Test
  void equipAxeTest();

  /**
   * @return the test axe
   */
  Axe getAxe();

  /**
   * Checks if the sword is equipped correctly to the unit
   */
  @Test
  void equipSwordTest();

  /**
   * @return the test sword
   */
  Sword getSword();

  /**
   * Checks if the spear is equipped correctly to the unit
   */
  @Test
  void equipSpearTest();

  /**
   * @return the test spear
   */
  Spear getSpear();

  /**
   * Checks if the staff is equipped correctly to the unit
   */
  @Test
  void equipStaffTest();

  /**
   * @return the test staff
   */
  Staff getStaff();

  /**
   * Checks if the bow is equipped correctly to the unit
   */
  @Test
  void equipBowTest();

  /**
   * @return the test bow
   */
  Bow getBow();

  /**
   * Checks if the unit moves correctly
   */
  @Test
  void testMovement();

  /**
   * @return the test field
   */
  Field getField();

  /**
   * @return the target Alpaca
   */
  Alpaca getTargetAlpaca();
}
