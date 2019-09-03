package model.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.items.*;
import model.items.magicbook.Anima;
import model.items.magicbook.Dark;
import model.items.magicbook.Light;
import model.items.weapon.Axe;
import model.items.weapon.Spear;
import model.items.weapon.Sword;
import model.items.otheritem.Staff;
import model.items.weapon.Bow;
import model.map.Field;
import model.map.Location;
import model.units.otherunit.Alpaca;
import model.units.warrior.Archer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Archer targetIntercambio;
  protected Field field;
  protected Anima anima;
  protected Dark dark;
  protected Light light;
  protected Axe axe;
  protected Sword sword;
  protected Spear spear;
  protected Staff staff;
  protected Bow bow;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetIntercambio() {
    targetIntercambio = new Archer(50, 2, field.getCell(1, 0));
  }

  /**
   * Sets up the units and weapons to be tested
   */
  @BeforeEach
  public void setUp() {
    setField();
    setTestUnit();
    setTargetAlpaca();
    setTargetIntercambio();
    setWeapons();
  }

  /**
   * Set up the game field
   */
  @Override
  public void setField() {
    this.field = new Field();
    this.field.addCells(true, new Location(0, 0), new Location(0, 1), new Location(0, 2),
        new Location(1, 0), new Location(1, 1), new Location(1, 2), new Location(2, 0),
        new Location(2, 1), new Location(2, 2));
  }

  /**
   * Set up the main unit that's going to be tested in the test set
   */
  @Override
  public abstract void setTestUnit();

  /**
   * Creates a set of testing weapons
   */
  @Override
  public void setWeapons() {
    this.anima = new Anima("Anima", 10, 2, 3);
    this.dark = new Dark("Dark", 10, 2, 3);
    this.light = new Light("Light", 10, 2, 3);
    this.axe = new Axe("Axe", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.spear = new Spear("Spear", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
  }

  /**
   * Checks that the constructor works properly.
   */
  @Override
  @Test
  public void constructorTest() {
    assertEquals(50, getTestUnit().getCurrentHitPoints());
    assertEquals(2, getTestUnit().getMovement());
    assertEquals(new Location(0, 0), getTestUnit().getLocation());
    assertTrue(getTestUnit().getItems().isEmpty());
  }

  @Override
  @Test
  public void intercambioTest() {
    assertEquals(targetAlpaca.getItems(), List.of());
    getTestUnit().addItem(staff);
    getTestUnit().exchange(targetAlpaca,staff);
    assertEquals(targetAlpaca.getItems(), List.of(staff));
  }

  @Override
  @Test
  public void llenarInventarioTest() {
    assertEquals(targetIntercambio.getItems(), List.of());
    getTestUnit().addItem(anima);
    assertEquals(getTestUnit().getItems(), List.of(anima));
    getTestUnit().addItem(dark);
    assertEquals(getTestUnit().getItems(), List.of(anima,dark));
    getTestUnit().addItem(light);
    assertEquals(getTestUnit().getItems(), List.of(anima,dark,light));
    getTestUnit().addItem(staff);
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of(anima,dark,light));
    else assertEquals(getTestUnit().getItems(), List.of(anima,dark,light,staff));
    getTestUnit().exchange(targetIntercambio,anima);
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of(dark,light));
    else assertEquals(getTestUnit().getItems(), List.of(dark,light,staff));
    getTestUnit().exchange(targetIntercambio,dark);
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of(light));
    else assertEquals(getTestUnit().getItems(), List.of(light,staff));
    getTestUnit().exchange(targetIntercambio,light);
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of());
    else assertEquals(getTestUnit().getItems(), List.of(staff));
    getTestUnit().exchange(targetIntercambio,staff);
    assertEquals(targetIntercambio.getItems(), List.of(anima,dark,light));
    getTestUnit().addItem(staff);
    getTestUnit().exchange(targetIntercambio,staff);
    assertEquals(targetIntercambio.getItems(), List.of(anima,dark,light));
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of(staff));
    else assertEquals(getTestUnit().getItems(), List.of(staff,staff));
    targetIntercambio.exchange(getTestUnit(),staff);
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of(staff));
    else assertEquals(getTestUnit().getItems(), List.of(staff,staff));
    targetIntercambio.exchange(getTestUnit(),anima);
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of(staff,anima));
    else assertEquals(getTestUnit().getItems(), List.of(staff,staff,anima));
    getTestUnit().exchange(targetIntercambio,staff);
    if(getTestUnit().getMaxItems() == 3)
      assertEquals(getTestUnit().getItems(), List.of(anima));
    else assertEquals(getTestUnit().getItems(), List.of(staff,anima));
  }

  /**
   * @return the current unit being tested
   */
  @Override
  public abstract IUnit getTestUnit();

  /**
   * Tries to equip a weapon to the alpaca and verifies that it was not equipped
   *
   * @param item
   *     to be equipped
   */
  @Override
  public void checkEquippedItem(IEquipableItem item) {
    assertNull(getTestUnit().getEquippedItem());
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  @Override
  @Test
  public void equipAnimaTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAnima());
  }

  /**
   * @return the test anima
   */
  @Override
  public Anima getAnima() {
    return anima;
  }

  @Override
  @Test
  public void equipDarkTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getDark());
  }

  /**
   * @return the test axe
   */
  @Override
  public Dark getDark() {
    return dark;
  }

  @Override
  @Test
  public void equipLightTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getLight());
  }

  /**
   * @return the test axe
   */
  @Override
  public Light getLight() {
    return light;
  }

  @Override
  @Test
  public void equipAxeTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAxe());
  }

  /**
   * @return the test axe
   */
  @Override
  public Axe getAxe() {
    return axe;
  }

  @Override
  @Test
  public void equipSwordTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getSword());
  }

  /**
   * @return the test sword
   */
  @Override
  public Sword getSword() {
    return sword;
  }

  @Override
  @Test
  public void equipSpearTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getSpear());
  }

  /**
   * @return the test spear
   */
  @Override
  public Spear getSpear() {
    return spear;
  }

  @Override
  @Test
  public void equipStaffTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getStaff());
  }

  /**
   * @return the test staff
   */
  @Override
  public Staff getStaff() {
    return staff;
  }

  @Override
  @Test
  public void equipBowTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getBow());
  }

  /**
   * @return the test bow
   */
  @Override
  public Bow getBow() {
    return bow;
  }

  /**
   * Checks if the unit moves correctly
   */
  @Override
  @Test
  public void testMovement() {
    getTestUnit().moveTo(getField().getCell(2, 2));
    assertEquals(new Location(0, 0), getTestUnit().getLocation());

    getTestUnit().moveTo(getField().getCell(0, 2));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());

    getField().getCell(0, 1).setUnit(getTargetAlpaca());
    getTestUnit().moveTo(getField().getCell(0, 1));
    assertEquals(new Location(0, 2), getTestUnit().getLocation());
  }

  /**
   * @return the test field
   */
  @Override
  public Field getField() {
    return field;
  }

  /**
   * @return the target Alpaca
   */
  @Override
  public Alpaca getTargetAlpaca() {
    return targetAlpaca;
  }
}
