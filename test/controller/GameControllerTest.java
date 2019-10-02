package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.map.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Tactician;
import model.items.IEquipableItem;
import model.items.otheritem.Staff;
import model.items.weapon.Spear;
import model.map.Field;
import model.units.IUnit;
import model.units.otherunit.Cleric;
import model.units.warrior.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

    private GameController controller;
    private long randomSeed;
    private List<String> testTacticians;

    int sideSquare;
    IUnit unit1;
    IUnit unit2;
    IUnit unit3;
    IEquipableItem item1;
    IEquipableItem item2;

    @BeforeEach
    void setUp() {
        // Se define la semilla como un número aleatorio para generar variedad en los tests
        randomSeed = new Random().nextLong();
        controller = new GameController(4,7);
        testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
    }

    @Test
    void getTacticians() {
        List<Tactician> tacticians = controller.getTacticians();
        assertEquals(4, tacticians.size());
        for (int i = 0; i < tacticians.size(); i++) {
            assertEquals("Player " + i, tacticians.get(i).getName());
        }
    }

    @Test
    void BenchmarkGenerateMap() {
        Field gameMap = controller.getGameMap();
        gameMap.generateMap(1000);
        gameMap.printMap();
        assertEquals(1000, gameMap.getSize());
        assertTrue(controller.getGameMap().isConnected());
        gameMap.generateMap(2000);
        gameMap.printMap();
        assertEquals(2000, gameMap.getSize());
        assertTrue(controller.getGameMap().isConnected());
        gameMap.generateMap(5000);
        gameMap.printMap();
        assertEquals(5000, gameMap.getSize());
        assertTrue(controller.getGameMap().isConnected());
    }

    @Test
    void getGameMap() {
        Field gameMap = controller.getGameMap();
        controller.gameMap.printMap();
        assertEquals(7, gameMap.getSize()); // getSize deben definirlo
        assertTrue(controller.getGameMap().isConnected());
        Random testRandom = new Random(randomSeed);
        gameMap.setSeed(randomSeed);

        // Para testear funcionalidades que dependen de valores aleatorios se hacen 2 cosas:
        //  - Comprobar las invariantes de las estructuras que se crean (en este caso que el mapa tenga
        //    las dimensiones definidas y que sea conexo.
        //  - Setear una semilla para el generador de números aleatorios. Hacer esto hace que la
        //    secuencia de números generada sea siempre la misma, así pueden predecir los
        //    resultados que van a obtener.
        //    Hay 2 formas de hacer esto en Java, le pueden pasar el seed al constructor de Random, o
        //    usar el método setSeed de Random.
        //  ESTO ÚLTIMO NO ESTÁ IMPLEMENTADO EN EL MAPA, ASÍ QUE DEBEN AGREGARLO (!)
    }

    @Test
    void getTurnOwner() {
        Random random = new Random();
        random.setSeed(randomSeed);
        controller.setSeed(randomSeed);
        controller.initGame(-1);

        List<Tactician> tacticiansTurn = new ArrayList<Tactician>();
        List<Tactician> tacticiansTemp;
        Tactician tactician;
        Tactician lastTurn = null;
        int randomNum;

        for (int i = 0; i < 50; i++) {
            do {
                tacticiansTurn.clear();
                tacticiansTemp = controller.getTacticians();
                for (int j = 0; j < controller.getTacticiansGame().size(); j++) {
                    randomNum = (int) (random.nextFloat() * tacticiansTemp.size());
                    tacticiansTurn.add(tacticiansTemp.get(randomNum));
                    tacticiansTemp.remove(randomNum);
                }
            } while ( lastTurn == tacticiansTurn.get(0));
            lastTurn = tacticiansTurn.get(tacticiansTurn.size()-1);

            for (int j = 0; j < controller.getTacticiansGame().size(); j++) {
                tactician = controller.getTurnOwner();
                assertEquals(tacticiansTurn.get(j), tactician);
                controller.endTurn();
            }
        }

    }

    @Test
    void getRoundNumber() {
        controller.initGame(10);
        for (int i = 1; i < 10; i++) {
            assertEquals(i, controller.getRoundNumber());
            for (int j = 0; j < 4; j++) {
                controller.endTurn();
            }
        }
    }

    @Test
    void getMaxRounds() {
        Random randomTurnSequence = new Random();
        IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
            controller.initGame(nextInt);
            System.out.println(nextInt);
            assertEquals(nextInt, controller.getMaxRounds());
            System.out.println(nextInt);
        });
        controller.initEndlessGame();
        assertEquals(-1, controller.getMaxRounds());
    }

    @Test
    void endTurn() {
        Random random = new Random();
        random.setSeed(randomSeed);
        controller.setSeed(randomSeed);
        controller.initGame(-1);
        Tactician firstPlayer = controller.getTurnOwner();

        List<Tactician> tacticiansTemp = controller.getTacticians();
        tacticiansTemp.remove((int) (random.nextFloat() * tacticiansTemp.size()));
        String name = tacticiansTemp.get((int) (random.nextFloat() * tacticiansTemp.size())).getName();
        Tactician secondPlayer = new Tactician(name);
        assertNotEquals(secondPlayer.getName(), firstPlayer.getName());

        controller.endTurn();
        assertNotEquals(firstPlayer.getName(), controller.getTurnOwner().getName());
        assertEquals(secondPlayer.getName(), controller.getTurnOwner().getName());
    }

    @Test
    void removeTactician() {
        assertEquals(4, controller.getTacticians().size());
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

        controller.removeTactician("Player 0");
        assertEquals(3, controller.getTacticians().size());
        controller.getTacticians().forEach(tactician -> assertNotEquals("Player 1", tactician));
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));

        controller.removeTactician("Player 5");
        assertEquals(3, controller.getTacticians().size());
        controller.getTacticians()
                .forEach(tactician -> Assertions.assertTrue(testTacticians.contains(tactician.getName())));
    }

    @Test
    void getWinners() {
        controller.initGame(2);
        IntStream.range(0, 8).forEach(i -> controller.endTurn());
        assertEquals(4, controller.getWinners().size());
        controller.getWinners()
                .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

        controller.initGame(2);
        IntStream.range(0, 4).forEach(i -> controller.endTurn());
        assertNull(controller.getWinners());
        controller.removeTactician("Player 0");
        controller.removeTactician("Player 2");
        IntStream.range(0, 2).forEach(i -> controller.endTurn());
        List<String> winners = controller.getWinners();
        assertEquals(2, winners.size());
        assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

        controller.initEndlessGame();
        for (int i = 0; i < 3; i++) {
            assertNull(controller.getWinners());
            controller.removeTactician("Player " + i);
        }
        assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
    }

    // Desde aquí en adelante, los tests deben definirlos completamente ustedes
    @Test
    void getSelectedUnit() {
        controller.initGame(-1);

        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2, sideSquare/2));

        assertEquals(controller.getTurnOwner().getUnits(), List.of());
        assertEquals(controller.getSelectedUnit(), null);

        controller.addUnit(unit1);
        controller.addUnit(unit1);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));
        assertEquals(controller.getSelectedUnit(), null);

        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));
        assertEquals(controller.getSelectedUnit(), unit1);

        controller.selectUnitIn(0, 0);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));
        assertEquals(controller.getSelectedUnit(), null);

        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        controller.endTurn();
        assertEquals(controller.getTurnOwner().getUnits(), List.of());
        assertEquals(controller.getSelectedUnit(), null);

    }

    @Test
    void selectUnitIn() {
        controller.initGame(-1);

        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2, sideSquare/2));
        unit2 = new Cleric(50, 2, controller.gameMap.getCell(sideSquare, sideSquare));

        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        assertEquals(controller.getSelectedUnit(), null);
        controller.addUnit(unit1);
        controller.addUnit(unit2);
        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        assertEquals(controller.getSelectedUnit(), unit1);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));

        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        assertEquals(controller.getSelectedUnit(), unit1);
        controller.selectUnitIn(sideSquare, sideSquare);
        assertEquals(controller.getSelectedUnit(), null);
    }

    @Test
    void getItems() {
        controller.initGame(-1);

        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2, sideSquare/2));
        item1 = new Spear("Spear", 10, 1,3);
        item2 = new Staff("Staff", 10, 1,3);

        controller.addUnit(unit1);
        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        controller.getSelectedUnit().addItem(item1);
        assertEquals(controller.getItems(), List.of(item1));
        controller.getTurnOwner().getUnits().get(0).addItem(item2);
        assertEquals(controller.getItems(), List.of(item1, item2));
        controller.getTurnOwner().getUnits().get(0).equipItem(item1);
        controller.getTurnOwner().getUnits().get(0).equipItem(null);
        assertEquals(controller.getItems(), List.of(item1, item2));
    }

    @Test
    void equipItem() {
        controller.initGame(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2, sideSquare/2));
        item1 = new Spear("Spear", 10, 1,3);
        item2 = new Staff("Staff", 10, 1,3);
        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);
        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        assertEquals(controller.getItems(), List.of(item1, item2));
        assertEquals(controller.getSelectedUnit().getEquippedItem(), null);
        controller.equipItem(1);
        assertEquals(controller.getSelectedUnit().getEquippedItem(), null);
        controller.equipItem(0);
        assertEquals(controller.getSelectedUnit().getEquippedItem(), item1);
    }

    @Test
    void useItemOn() {
        controller.initGame(-1);
        sideSquare = controller.gameMap.getSideSquare();

        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2, sideSquare/2));
        item1 = new Spear("Spear", 10, 1,3);
        item2 = new Staff("Staff", 10, 1,3);

        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);

    }

    @Test
    void selectItem() {
        controller.initGame(-1);
        sideSquare = controller.gameMap.getSideSquare();

        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2, sideSquare/2));
        item1 = new Spear("Spear", 10, 1,3);
        item2 = new Staff("Staff", 10, 1,3);

        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);
        controller.selectUnitIn(sideSquare/2, sideSquare/2);
        assertEquals(controller.getItems(), List.of(item1, item2));
        assertEquals(controller.getSelectedItem(), null);
        controller.selectItem(2);
        assertEquals(controller.getSelectedItem(), null);
        controller.selectItem(0);
        assertEquals(controller.getSelectedItem(), item1);
        controller.selectItem(1);
        assertEquals(controller.getSelectedItem(), item2);
    }

    @Test
    void giveItemTo() {
        controller = new GameController(4, 100);
        controller.initGame(-1);
        sideSquare = controller.gameMap.getSideSquare();
        if (controller.gameMap.getCell(sideSquare/2-1, sideSquare/2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare/2-1, sideSquare/2));
        if (controller.gameMap.getCell(sideSquare/2+1, sideSquare/2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare/2+1, sideSquare/2));
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2-1, sideSquare/2));
        unit2 = new Cleric(50, 2, controller.gameMap.getCell(sideSquare/2, sideSquare/2));
        unit3 = new Hero(50, 2, controller.gameMap.getCell(sideSquare/2+1, sideSquare/2));

        item1 = new Spear("Spear", 10, 1,3);
        item2 = new Staff("Staff", 10, 1,3);

        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);
        controller.addUnit(unit2);
        controller.addUnit(unit3);

        controller.selectUnitId(1);
        assertEquals(controller.getItems(), List.of());
        controller.selectUnitId(0);
        assertEquals(controller.getItems(), List.of(item1,item2));
        controller.selectItem(0);

        controller.giveItemTo(sideSquare/2, sideSquare/2);
        assertEquals(controller.getItems(), List.of(item2));
        controller.selectUnitId(1);
        assertEquals(controller.getItems(), List.of(item1));
        controller.selectUnitId(0);
        controller.selectItem(0);
        controller.giveItemTo(sideSquare/2, sideSquare/2);
        assertEquals(controller.getItems(), List.of());
        controller.selectUnitId(1);
        assertEquals(controller.getItems(), List.of(item1, item2));
        controller.selectItem(0);
        controller.giveItemTo(sideSquare/2+1, sideSquare/2);
        assertEquals(controller.getItems(), List.of(item1, item2));
    }
}