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
     * Configura la unidad para los test de contraataque
     */
    void setTargetCounterattack();

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
     * Creates a set of testing warriors
     */
    void setWarriors();

    /**
     * Checks that the constructor works properly.
     */
    @Test
    void constructorTest();

    /**
     * Test de contraataque
     * (no testea debilidades, eso esta en otros test)
     */
    @Test
    void counterattackTest();

    /**
     * Test de curación
     */
    @Test
    void healTest();

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
    void equipNullItemTest();

    @Test
    void attackToNullItem();

    /**
     * Checks if the anima is equipped correctly to the unit
     */
    @Test
    void equipAnimaTest();

    @Test
    void attackToAnima();

    /**
     * @return the test anima
     */
    Anima getAnima();

    /**
     * Checks if the dark is equipped correctly to the unit
     */
    @Test
    void equipDarkTest();

    @Test
    void attackToDark();

    /**
     * @return the test dark
     */
    Dark getDark();

    /**
     * Checks if the light is equipped correctly to the unit
     */
    @Test
    void equipLightTest();

    @Test
    void attackToLight();

    /**
     * @return the test light
     */
    Light getLight();

    /**
     * Tries to equip a weapon to the alpaca and verifies that it was not equipped
     *
     * @param item to be equipped
     */
    void checkEquippedItem(IEquipableItem item);

    /**
     * Checks if the axe is equipped correctly to the unit
     */
    @Test
    void equipAxeTest();

    @Test
    void attackToAxe();

    /**
     * @return the test axe
     */
    Axe getAxe();

    /**
     * Checks if the sword is equipped correctly to the unit
     */
    @Test
    void equipSwordTest();

    @Test
    void attackToSword();

    /**
     * @return the test sword
     */
    Sword getSword();

    /**
     * Checks if the spear is equipped correctly to the unit
     */
    @Test
    void equipSpearTest();

    @Test
    void attackToSpear();

    /**
     * @return the test spear
     */
    Spear getSpear();

    /**
     * Checks if the staff is equipped correctly to the unit
     */
    @Test
    void equipStaffTest();

    @Test
    void attackToStaff();

    /**
     * @return the test staff
     */
    Staff getStaff();

    /**
     * Checks if the bow is equipped correctly to the unit
     */
    @Test
    void equipBowTest();

    @Test
    void attackToBow();

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
     * Verifica que la unidad solo se pueda mover una vez por turno
     */
    @Test
    void testMovementUsed();

    /**
     * Verifica que la unidad solo se pueda realizar la cantidad de acciones permitidas
     */
    @Test
    void testActionUsed();

    /**
     * Verifica si la unidad tiene vien configurada la condición de derrota del tactician
     */
    @Test
    void DefeatCondition();

    /**
     * @return the test field
     */
    Field getField();

    /**
     * @return the target Alpaca
     */
    Alpaca getTargetAlpaca();

    /**
     * @return expected HP after anima attack
     */
    int getHPanima();

    /**
     * @return expected HP after dark attack
     */
    int getHPdark();

    /**
     * @return expected HP after light attack
     */
    int getHPlight();

    /**
     * @return expected HP after axe attack
     */
    int getHPaxe();

    /**
     * @return expected HP after spear attack
     */
    int getHPspear();

    /**
     * @return expected HP after sword attack
     */
    int getHPsword();

    /**
     * @return expected HP after staff attack
     */
    int getHPstaff();

    /**
     * @return expected HP after bow attack
     */
    int getHPbow();

    /**
     * @return expected HP after counterattack test en case 1
     */
    int getHP1();

    /**
     * @return expected HP after counterattack test en case 2
     */
    int getHP2();
}
