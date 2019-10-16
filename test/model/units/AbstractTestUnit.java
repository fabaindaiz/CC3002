package model.units;

import model.items.IEquipableItem;
import model.items.magicbook.Anima;
import model.items.magicbook.Dark;
import model.items.magicbook.Light;
import model.items.otheritem.Staff;
import model.items.weapon.Axe;
import model.items.weapon.Bow;
import model.items.weapon.Spear;
import model.items.weapon.Sword;
import model.map.Field;
import model.map.Location;
import model.units.otherunit.Alpaca;
import model.units.otherunit.Cleric;
import model.units.sorcerer.Sorcerer;
import model.units.warrior.Archer;
import model.units.warrior.Fighter;
import model.units.warrior.Hero;
import model.units.warrior.SwordMaster;
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
        assertEquals(50, getTestUnit().getMaxHitPoints());
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
        unit.addItem(item);
        unit.equipItem(item);
        unit.useItem(targetCounterattack1, true);
        assertEquals(unit.getCurrentHitPoints(), getHP1());
        unit.useItem(targetCounterattack1, true);
        assertEquals(unit.getCurrentHitPoints(), getHP1());
        unit.useItem(targetCounterattack2, true);
        assertEquals(unit.getCurrentHitPoints(), getHP1());
        unit.useItem(targetCounterattack3, true);
        assertEquals(unit.getCurrentHitPoints(), getHP2());
        unit.useItem(targetCounterattack4, true);
        assertEquals(unit.getCurrentHitPoints(), getHP2());
        targetCounterattack2.useItem(unit, true);
        assertEquals(unit.getCurrentHitPoints(), getHP3());
        assertEquals(targetCounterattack2.getCurrentHitPoints(), getHP4());
    }

    @Override
    @Test
    public void healTest() {
        IUnit unit = getTestUnit();
        targetCounterattack2.setMaxAction(3);
        targetCounterattack2.addItem(staff);
        targetCounterattack2.addItem(item4);
        unit.setCurrentHitPoints(20);
        targetCounterattack2.equipItem(item4);
        targetCounterattack2.useItem(unit, false);
        assertEquals(unit.getCurrentHitPoints(), 20);
        targetCounterattack2.equipItem(staff);
        targetCounterattack2.useItem(unit, false);
        assertEquals(unit.getCurrentHitPoints(), 30);
        targetCounterattack2.useItem(unit, true);
        assertEquals(unit.getCurrentHitPoints(), 40);
        assertEquals(targetCounterattack2.getCurrentHitPoints(), 50);
    }

    @Override
    @Test
    public void intercambioTest() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        assertEquals(targetAlpaca.getItems(), List.of());
        unit.addItem(staff);
        unit.addItem(item);
        unit.equipItem(item);
        unit.exchange(targetAlpaca, staff);
        assertEquals(targetAlpaca.getItems(), List.of(staff));
        unit.exchange(targetAlpaca, item);
        assertEquals(targetAlpaca.getItems(), List.of(staff, item));
        assertNull(item.getOwner());
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
    }

    @Override
    @Test
    public void llenarInventarioTest() {
        assertEquals(getTestUnit().getMaxItems(), 3);
        assertEquals(targetIntercambio.getItems(), List.of());
        getTestUnit().addItem(anima);
        assertEquals(getTestUnit().getItems(), List.of(anima));
        getTestUnit().addItem(dark);
        assertEquals(getTestUnit().getItems(), List.of(anima, dark));
        getTestUnit().addItem(light);
        assertEquals(getTestUnit().getItems(), List.of(anima, dark, light));
        getTestUnit().addItem(staff);
        assertEquals(getTestUnit().getItems(), List.of(anima, dark, light));
        getTestUnit().exchange(targetIntercambio, anima);
        assertEquals(getTestUnit().getItems(), List.of(dark, light));
        getTestUnit().exchange(targetIntercambio, dark);
        assertEquals(getTestUnit().getItems(), List.of(light));
        getTestUnit().exchange(targetIntercambio, light);
        assertEquals(getTestUnit().getItems(), List.of());
        getTestUnit().exchange(targetIntercambio, staff);
        assertEquals(targetIntercambio.getItems(), List.of(anima, dark, light));
        getTestUnit().addItem(staff);
        getTestUnit().exchange(targetIntercambio, staff);
        assertEquals(targetIntercambio.getItems(), List.of(anima, dark, light));
        assertEquals(getTestUnit().getItems(), List.of(staff));
        targetIntercambio.exchange(getTestUnit(), staff);
        assertEquals(getTestUnit().getItems(), List.of(staff));
        targetIntercambio.exchange(getTestUnit(), anima);
        assertEquals(getTestUnit().getItems(), List.of(staff, anima));
        getTestUnit().exchange(targetIntercambio, staff);
        assertEquals(getTestUnit().getItems(), List.of(anima));
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
     * @param item to be equipped
     */
    @Override
    public void checkEquippedItem(IEquipableItem item) {
        getTestUnit().equipItem(null);
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        getTestUnit().equipItem(item);
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        getTestUnit().addItem(item);
        getTestUnit().equipItem(item);
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
    }

    @Override
    @Test
    public void equipNullItemTest() {
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        assertEquals(getTestUnit().getNullItem().counterattack(), false);
    }

    @Override
    @Test
    public void attackToNullItem() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), 40);
    }

    @Override
    @Test
    public void equipAnimaTest() {
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getAnima());

    }

    @Override
    @Test
    public void attackToAnima() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        sorcerer.addItem(anima);
        sorcerer.equipItem(anima);
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), getHPanima());
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
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getDark());
    }

    @Override
    @Test
    public void attackToDark() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        sorcerer.addItem(dark);
        sorcerer.equipItem(dark);
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), getHPdark());
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
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getLight());
    }

    @Override
    @Test
    public void attackToLight() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        sorcerer.addItem(light);
        sorcerer.equipItem(light);
        unit.useItem(sorcerer, false);
        assertEquals(sorcerer.getCurrentHitPoints(), getHPlight());
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
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getAxe());
    }

    @Override
    @Test
    public void attackToAxe() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        fighter.addItem(axe);
        fighter.equipItem(axe);
        unit.useItem(fighter, false);
        assertEquals(fighter.getCurrentHitPoints(), getHPaxe());
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
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getSword());
    }

    @Override
    @Test
    public void attackToSword() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        swordMaster.addItem(sword);
        swordMaster.equipItem(sword);
        unit.useItem(swordMaster, false);
        assertEquals(swordMaster.getCurrentHitPoints(), getHPsword());
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
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getSpear());
    }

    @Override
    @Test
    public void attackToSpear() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        hero.addItem(spear);
        hero.equipItem(spear);
        unit.useItem(hero, false);
        assertEquals(hero.getCurrentHitPoints(), getHPspear());
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
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getStaff());
    }

    @Override
    @Test
    public void attackToStaff() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        cleric.addItem(staff);
        cleric.equipItem(staff);
        unit.useItem(cleric, false);
        assertEquals(cleric.getCurrentHitPoints(), getHPstaff());
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
        assertEquals(getTestUnit().getEquippedItem(), getTestUnit().getNullItem());
        checkEquippedItem(getBow());
    }

    @Override
    @Test
    public void attackToBow() {
        IUnit unit = getTestUnit();
        IEquipableItem item = getWeapon();
        unit.addItem(item);
        unit.equipItem(item);
        archer.addItem(bow);
        archer.equipItem(bow);
        unit.useItem(archer, false);
        assertEquals(archer.getCurrentHitPoints(), getHPbow());
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

    @Override
    @Test
    public void testMovementUsed() {
        assertEquals(getTestUnit().getMovementUsed(), false);
        getTestUnit().moveTo(getField().getCell(0, 2));
        assertEquals(getTestUnit().getMovementUsed(), true);
        assertEquals(new Location(0, 2), getTestUnit().getLocation());
        getTestUnit().moveTo(getField().getCell(0, 0));
        assertEquals(getTestUnit().getMovementUsed(), true);
        assertEquals(new Location(0, 2), getTestUnit().getLocation());
        getTestUnit().setNewTurn();
        assertEquals(getTestUnit().getMovementUsed(), false);
        getTestUnit().moveTo(getField().getCell(0, 0));
        assertEquals(getTestUnit().getMovementUsed(), true);
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
        getTestUnit().moveTo(getField().getCell(0, 2));
        assertEquals(getTestUnit().getMovementUsed(), true);
        assertEquals(new Location(0, 0), getTestUnit().getLocation());
    }

    @Override
    @Test
    public void testActionUsed() {
        field.addCells(true, new Location(3, 2), new Location(3, 3));
        IUnit targetAttack = new Alpaca(50, 2, field.getCell(3, 3));
        getTestUnit().setMaxAction(1);
        getTestUnit().addItem(getWeapon());
        getTestUnit().addItem(item1);
        getTestUnit().addItem(item2);
        getTestUnit().equipItem(getWeapon());
        assertEquals(getTestUnit().getActionRemains(), 1);
        getTestUnit().useItem(targetAttack, false);
        assertEquals(getTestUnit().getActionRemains(), 1);
        getTestUnit().useItem(targetCounterattack1, false);
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().setNewTurn();
        assertEquals(getTestUnit().getItems(), List.of(getWeapon(), item1, item2));
        assertEquals(getTestUnit().getActionRemains(), 1);
        getTestUnit().exchange(targetIntercambio, item3);
        assertEquals(getTestUnit().getItems(), List.of(getWeapon(), item1, item2));
        assertEquals(getTestUnit().getActionRemains(), 1);
        getTestUnit().exchange(targetIntercambio, item2);
        assertEquals(getTestUnit().getItems(), List.of(getWeapon(), item1));
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().exchange(targetIntercambio, item1);
        assertEquals(getTestUnit().getItems(), List.of(getWeapon(), item1));
        assertEquals(getTestUnit().getActionRemains(), 0);
        getTestUnit().setNewTurn();
        assertEquals(getTestUnit().getActionRemains(), 1);
        getTestUnit().exchange(targetIntercambio, item1);
        assertEquals(getTestUnit().getItems(), List.of(getWeapon()));
        assertEquals(getTestUnit().getActionRemains(), 0);
    }

    @Override
    @Test
    public void DefeatCondition() {
        assertEquals(getTestUnit().defeatCondition(), false);
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

    @Override
    public int getHP3() {
        return 50;
    }

    @Override
    public int getHP4() {
        return 40;
    }

}
