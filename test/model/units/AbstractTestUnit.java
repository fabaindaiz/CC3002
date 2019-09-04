package model.units;

import model.items.IEquipableItem;
import model.items.magicbook.*;
import model.items.otheritem.*;
import model.items.weapon.*;
import model.map.Field;
import model.map.Location;
import model.units.otherunit.*;
import model.units.sorcerer.*;
import model.units.warrior.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractTestUnit implements ITestUnit {

  protected Alpaca targetAlpaca;
  protected Archer targetIntercambio;
  protected Archer targetCounterattack1;
  protected Cleric targetCounterattack2;
  protected Sorcerer targetCounterattack3;
  protected Alpaca targetCounterattack4;
  protected Bow item1;
  protected Staff item2;
  protected Light item3;
  protected Staff item4;
  protected Field field;
  protected Anima anima;
  protected Dark dark;
  protected Light light;
  protected Axe axe;
  protected Sword sword;
  protected Spear spear;
  protected Staff staff;
  protected Bow bow;
  protected Sorcerer sorcerer;
  protected Fighter fighter;
  protected Hero hero;
  protected SwordMaster swordMaster;
  protected Archer archer;
  protected Cleric cleric;

  @Override
  public void setTargetAlpaca() {
    targetAlpaca = new Alpaca(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetIntercambio() {
    targetIntercambio = new Archer(50, 2, field.getCell(1, 0));
  }

  @Override
  public void setTargetCounterattack() {
    item1 = new Bow("Bow", 20, 2, 3);
    item2 = new Staff("Staff", 50, 2, 3);
    item3 = new Light("Light", 10, 2, 3);
    item4 = new Staff("Staff", 0, 2, 3);
    targetCounterattack1 = new Archer(20, 2, field.getCell(1, 1));
    targetCounterattack2 = new Cleric(50, 2, field.getCell(2, 0));
    targetCounterattack3 = new Sorcerer(50, 2, field.getCell(0, 2));
    targetCounterattack4 = new Alpaca(50, 2, field.getCell(2, 2));
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
    setTargetCounterattack();
    setWeapons();
    setWarriors();
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
    this.spear = new Spear("Spear", 10, 1, 2);
    this.sword = new Sword("Sword", 10, 1, 2);
    this.staff = new Staff("Staff", 10, 1, 2);
    this.bow = new Bow("Bow", 10, 2, 3);
  }

  @Override
  public void setWarriors() {
    this.sorcerer = new Sorcerer(50, 2, field.getCell(1, 1));
    this.fighter = new Fighter(50, 2, field.getCell(1, 1));
    this.hero = new Hero(50, 2, field.getCell(1, 1));
    this.swordMaster = new SwordMaster(50, 2, field.getCell(1, 1));
    this.archer = new Archer(50, 2, field.getCell(1, 1));
    this.cleric = new Cleric(50, 2, field.getCell(1, 1));
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
  public void counterattackTest() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    targetCounterattack1.addItem(item1);
    targetCounterattack2.addItem(item2);
    targetCounterattack3.addItem(item3);
    targetCounterattack1.equipItem(item1);
    targetCounterattack2.equipItem(item2);
    targetCounterattack3.equipItem(item3);
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      unit.attack(targetCounterattack1, true);
      assertEquals(unit.getCurrentHitPoints(), getHP1());
      unit.attack(targetCounterattack1, true);
      assertEquals(unit.getCurrentHitPoints(), getHP1());
      unit.attack(targetCounterattack2, true);
      assertEquals(unit.getCurrentHitPoints(), getHP1());
      unit.attack(targetCounterattack3, true);
      assertEquals(unit.getCurrentHitPoints(), getHP2());
      unit.attack(targetCounterattack4, true);
      assertEquals(unit.getCurrentHitPoints(), getHP2());
      targetCounterattack2.attack(unit, true);
      assertEquals(unit.getCurrentHitPoints(), 50);
      if(item != staff)
        assertEquals(targetCounterattack2.getCurrentHitPoints(), 40);
      else
        assertEquals(targetCounterattack2.getCurrentHitPoints(), 50);
    }
  }

  @Override
  @Test
  public void healTest() {
    IUnit unit = getTestUnit();
    targetCounterattack2.addItem(staff);
    targetCounterattack2.addItem(item4);
    unit.setCurrentHitPoints(20);
    targetCounterattack2.equipItem(item4);
    targetCounterattack2.attack(unit,false);
    assertEquals(unit.getCurrentHitPoints(),20);
    targetCounterattack2.equipItem(staff);
    targetCounterattack2.attack(unit,false);
    assertEquals(unit.getCurrentHitPoints(),30);
    targetCounterattack2.attack(unit,true);
    assertEquals(unit.getCurrentHitPoints(),40);
    assertEquals(targetCounterattack2.getCurrentHitPoints(),50);
  }

  @Override
  @Test
  public void intercambioTest() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    assertEquals(targetAlpaca.getItems(), List.of());
    unit.addItem(staff);
    if (item != null) {
      unit.addItem(item);
      unit.equipItem(item);
    }
    unit.exchange(targetAlpaca,staff);
    assertEquals(targetAlpaca.getItems(), List.of(staff));
    if (item != null) {
      unit.exchange(targetAlpaca,item);
      assertEquals(targetAlpaca.getItems(), List.of(staff,item));
      assertEquals(item.getOwner(),null);
    }
    assertEquals(unit.getEquippedItem(),null);
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

  public abstract IEquipableItem getWeapon();

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
    getTestUnit().addItem(item);
    getTestUnit().equipItem(item);
    assertNull(getTestUnit().getEquippedItem());
  }

  @Override
  @Test
  public void equipAnimaTest() {
    assertNull(getTestUnit().getEquippedItem());
    checkEquippedItem(getAnima());

  }

  @Override
  @Test
  public void attackToAnima() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      sorcerer.addItem(anima);
      sorcerer.equipItem(anima);
      unit.attack(sorcerer, false);
      assertEquals(sorcerer.getCurrentHitPoints(), getHPanima());
    }
    else {
      unit.attack(sorcerer, false);
      assertEquals(sorcerer.getCurrentHitPoints(), 50);
    }
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

  @Override
  @Test
  public void attackToDark() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      sorcerer.addItem(dark);
      sorcerer.equipItem(dark);
      unit.attack(sorcerer, false);
      assertEquals(sorcerer.getCurrentHitPoints(), getHPdark());
    }
    else {
      unit.attack(sorcerer, false);
      assertEquals(sorcerer.getCurrentHitPoints(), 50);
    }
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

  @Override
  @Test
  public void attackToLight() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      sorcerer.addItem(light);
      sorcerer.equipItem(light);
      unit.attack(sorcerer, false);
      assertEquals(sorcerer.getCurrentHitPoints(), getHPlight());
    }
    else {
      unit.attack(sorcerer, false);
      assertEquals(sorcerer.getCurrentHitPoints(), 50);
    }
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

  @Override
  @Test
  public void attackToAxe() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      fighter.addItem(axe);
      fighter.equipItem(axe);
      unit.attack(fighter, false);
      assertEquals(fighter.getCurrentHitPoints(), getHPaxe());
    }
    else {
      unit.attack(fighter, false);
      assertEquals(fighter.getCurrentHitPoints(), 50);
    }
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

  @Override
  @Test
  public void attackToSword() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      swordMaster.addItem(sword);
      swordMaster.equipItem(sword);
      unit.attack(swordMaster, false);
      assertEquals(swordMaster.getCurrentHitPoints(), getHPsword());
    }
    else {
      unit.attack(swordMaster, false);
      assertEquals(swordMaster.getCurrentHitPoints(), 50);
    }
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

  @Override
  @Test
  public void attackToSpear() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      hero.addItem(spear);
      hero.equipItem(spear);
      unit.attack(hero, false);
      assertEquals(hero.getCurrentHitPoints(), getHPspear());
    }
    else {
      unit.attack(hero, false);
      assertEquals(hero.getCurrentHitPoints(), 50);
    }
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

  @Override
  @Test
  public void attackToStaff() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      cleric.addItem(staff);
      cleric.equipItem(staff);
      unit.attack(cleric, false);
      assertEquals(cleric.getCurrentHitPoints(), getHPstaff());
    }
    else {
      unit.attack(cleric, false);
      assertEquals(cleric.getCurrentHitPoints(), 50);
    }
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

  @Override
  @Test
  public void attackToBow() {
    IUnit unit = getTestUnit();
    IEquipableItem item = getWeapon();
    if(item != null) {
      unit.addItem(item);
      unit.equipItem(item);
      archer.addItem(bow);
      archer.equipItem(bow);
      unit.attack(archer, false);
      assertEquals(archer.getCurrentHitPoints(), getHPbow());
    }
    else {
      unit.attack(archer, false);
      assertEquals(archer.getCurrentHitPoints(), 50);
    }
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
