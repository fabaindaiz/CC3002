package controller;

import controller.parameter.IParameter;
import model.Tactician;
import model.items.IEquipableItem;
import model.items.otheritem.Staff;
import model.items.weapon.Bow;
import model.items.weapon.Spear;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import model.units.otherunit.Cleric;
import model.units.warrior.Archer;
import model.units.warrior.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ignacio Slater Muñoz
 * @since v2.0
 */
class GameControllerTest {

    int sideSquare;
    IUnit unit1;
    IUnit unit2;
    IUnit unit3;
    IEquipableItem item1;
    IEquipableItem item2;
    private GameController controller;
    private long randomSeed;
    private List<String> testTacticians;

    @BeforeEach
    void setUp() {
        // Se define la semilla como un número aleatorio para generar variedad en los tests
        randomSeed = new Random().nextLong();
        controller = new GameController(4, 7);
        testTacticians = List.of("Player 0", "Player 1", "Player 2", "Player 3");
    }

    @Test
    void BenchmarkGenerateMap() {
        Field gameMap = controller.getGameMap();
        gameMap.generateMap(500);
        gameMap.printMap();
        assertEquals(500, gameMap.getSize());
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
    void initAll() {    //Por terminar
    }

    @Test
    void getMaxRounds() {
        Random randomTurnSequence = new Random();
        IntStream.range(0, 50).map(i -> randomTurnSequence.nextInt() & Integer.MAX_VALUE).forEach(nextInt -> {
            controller.initGameTest(nextInt);
            System.out.println(nextInt);
            assertEquals(nextInt, controller.getMaxRounds());
            System.out.println(nextInt);
        });
        controller.initGameTest(-1);
        assertEquals(-1, controller.getMaxRounds());
    }

    @Test
    void getRoundNumber() {
        controller.initGameTest(10);
        for (int i = 1; i < 10; i++) {
            assertEquals(i, controller.getRoundNumber());
            for (int j = 0; j < 4; j++) {
                controller.endTurn();
            }
        }
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
    void getGameMap() {
        Field gameMap = controller.getGameMap();
        controller.gameMap.printMap();
        assertEquals(7, gameMap.getSize()); // getSize deben definirlo
        assertTrue(controller.getGameMap().isConnected());

        Random testRandom = new Random(randomSeed);
        gameMap.setSeed(randomSeed);
        gameMap.generateMap(250);
        Field testMap = new Field();
        testMap.setSeed(randomSeed);
        testMap.generateMap(250);
        assertEquals(testMap.getSize(), gameMap.getSize());
        assertEquals(testMap.getSideSquare(), gameMap.getSideSquare());
        for (int i = 0; i < 1000; i++) {
            int x = (int) (testRandom.nextFloat() * gameMap.getSideSquare());
            int y = (int) (testRandom.nextFloat() * gameMap.getSideSquare());
            assertEquals(testMap.getCell(x, y), gameMap.getCell(x, y));
        }
    }

    @Test
    void getTurnOwner() {
        Random random = new Random();
        random.setSeed(randomSeed);
        controller.setSeed(randomSeed);
        controller.initGameTest(-1);

        List<Tactician> tacticiansTurn = new ArrayList<Tactician>();
        List<Tactician> tacticiansTemp;
        Tactician tactician;
        Tactician lastTurn = null;
        int randomNum;

        for (int i = 0; i < 50; i++) {
            do {
                tacticiansTurn.clear();
                tacticiansTemp = controller.getTacticians();
                for (int j = 0; j < controller.getTacticians().size(); j++) {
                    randomNum = (int) (random.nextFloat() * tacticiansTemp.size());
                    tacticiansTurn.add(tacticiansTemp.get(randomNum));
                    tacticiansTemp.remove(randomNum);
                }
            } while (lastTurn == tacticiansTurn.get(0));
            lastTurn = tacticiansTurn.get(tacticiansTurn.size() - 1);

            for (int j = 0; j < controller.getTacticians().size(); j++) {
                tactician = controller.getTurnOwner();
                assertEquals(tacticiansTurn.get(j), tactician);
                controller.endTurn();
            }
        }
    }

    @Test
    void createUnit() {
        GameController controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        IParameter parameter = controller.getParameters().get(0);
        assertEquals(parameter.getType(), "archer");
        assertEquals(parameter.getLocation(), controller.getGameMap().getCell(6, 6));
    }

    @Test
    void createItem() {
        GameController controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.selectUnitId(0);
        controller.createItem("bow", "Example Bow", 10, 2, 3, false);
        IParameter parameter = controller.getParameters().get(1);
        assertEquals(parameter.getType(), "bow");
        assertEquals(parameter.getName(), "Example Bow");
    }

    @Test
    void addUnit() {
        GameController controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.initGameTest(-1);
        assertEquals(controller.getTurnOwner().getUnits().size(), 0);
        controller.addUnit(new Archer(50, 2, controller.getGameMap().getCell(6, 6)));
        assertEquals(controller.getTurnOwner().getUnits().size(), 1);
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
    }

    @Test
    void addItem() {
        GameController controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        controller.initGameTest(-1);
        controller.addUnit(new Archer(50, 2, controller.getGameMap().getCell(6, 6)));
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getItems().size(), 0);
        controller.addItem(new Bow("Example Bow", 10, 2, 4));
        assertEquals(controller.getSelectedUnit().getItems().size(), 1);
        controller.selectItem(0);
        assertEquals(controller.getSelectedItem().getName(), "Example Bow");
        assertEquals(controller.getSelectedUnit().getLocation(), controller.getGameMap().getCell(6, 6));
    }

    @Test
    void changeMap() {
        long defaultSeed = controller.defaultSeed;
        controller.changeMap();
        assertNotEquals(defaultSeed, controller.defaultSeed);
        controller.changeMap((long) 12345678);
        assertEquals(12345678, controller.defaultSeed);
    }

    @Test
    void initGame() {
        GameController controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        assertEquals(controller.getInitiatedGameStatus(), false);
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.initGame(10);
        assertEquals(controller.getInitiatedGameStatus(), true);
        assertEquals(controller.getMaxRounds(), 10);
        controller.endGame();
        assertEquals(controller.getInitiatedGameStatus(), false);
    }

    @Test
    void initEndlessGame() {
        GameController controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        assertEquals(controller.getInitiatedGameStatus(), false);
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.initEndlessGame();
        assertEquals(controller.getInitiatedGameStatus(), true);
        assertEquals(controller.getMaxRounds(), -1);
        controller.endGame();
        assertEquals(controller.getInitiatedGameStatus(), false);
    }

    @Test
    void endTurn() {
        Random random = new Random();
        random.setSeed(randomSeed);
        controller.setSeed(randomSeed);
        controller.initGameTest(-1);
        Tactician firstPlayer = controller.getTurnOwner();

        List<Tactician> tacticiansTemp = controller.getTacticians();
        tacticiansTemp.remove((int) (random.nextFloat() * tacticiansTemp.size()));
        String name = tacticiansTemp.get((int) (random.nextFloat() * tacticiansTemp.size())).getName();
        Tactician secondPlayer = new Tactician(name, 2, controller.getGameMap());
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
    void getWinnersCaseWin() {
        controller.initGameTest(2);
        IntStream.range(0, 8).forEach(i -> controller.endTurn());
        assertEquals(4, controller.getWinners().size());
        controller.getWinners()
                .forEach(player -> Assertions.assertTrue(testTacticians.contains(player)));

        controller.initGameTest(2);
        IntStream.range(0, 4).forEach(i -> controller.endTurn());
        assertNull(controller.getWinners());
        controller.removeTactician("Player 0");
        controller.removeTactician("Player 2");
        IntStream.range(0, 2).forEach(i -> controller.endTurn());
        List<String> winners = controller.getWinners();
        assertEquals(2, winners.size());
        assertTrue(List.of("Player 1", "Player 3").containsAll(winners));

        controller.initGameTest(-1);
        for (int i = 0; i < 3; i++) {
            assertNull(controller.getWinners());
            controller.removeTactician("Player " + i);
        }
        assertTrue(List.of("Player 3").containsAll(controller.getWinners()));
    }

    @Test
    void getWinnersCaseTurn() {
        controller = new GameController(2, 50);
        controller.changeMap((long) 1068 + 1067 + 1067);
        while (!controller.getTurnOwner().getName().equals("Player 0"))
            controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(6, 6));
        controller.endTurn();
        controller.createUnit("archer", 50, 2, controller.getGameMap().getCell(7, 7));
        controller.initGame(1);
        controller.endTurn();
        controller.endTurn();
        List<String> winners = controller.getWinners();
        assertEquals(2, winners.size());
        assertTrue(List.of("Player 0", "Player 1").containsAll(winners));
    }

    @Test
    void endGame() {
        assertEquals(controller.getInitiatedGameStatus(), false);
        controller.initGameTest(-1);
        assertEquals(controller.getInitiatedGameStatus(), true);
        controller.endGame();
        assertEquals(controller.getInitiatedGameStatus(), false);
    }

    @Test
    void getUnits() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        if (controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare / 2 - 1, sideSquare / 2));
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        unit2 = new Cleric(50, 2, controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2));
        unit3 = new Hero(50, 2, controller.gameMap.getCell(sideSquare, sideSquare));
        assertEquals(controller.getUnits().size(), 0);
        controller.addUnit(unit1);
        assertEquals(controller.getUnits().size(), 1);
        controller.addUnit(unit2);
        assertEquals(controller.getUnits().size(), 2);
        controller.addUnit(unit3);
        assertEquals(controller.getUnits().size(), 2);
    }

    @Test
    void selectUnitIn() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        unit2 = new Cleric(50, 2, controller.gameMap.getCell(sideSquare, sideSquare));

        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getSelectedUnit(), null);
        controller.addUnit(unit1);
        controller.addUnit(unit2);
        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getSelectedUnit(), unit1);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));

        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getSelectedUnit(), unit1);
        controller.selectUnitIn(sideSquare, sideSquare);
        assertEquals(controller.getSelectedUnit(), null);
    }

    @Test
    void selectUnitId() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        unit2 = new Cleric(50, 2, controller.gameMap.getCell(sideSquare, sideSquare));

        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit(), null);
        controller.addUnit(unit1);
        controller.addUnit(unit2);
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit(), unit1);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));

        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit(), unit1);
        controller.selectUnitId(1);
        assertEquals(controller.getSelectedUnit(), null);
    }

    @Test
    void getSelectedUnit() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));

        assertEquals(controller.getTurnOwner().getUnits(), List.of());
        assertEquals(controller.getSelectedUnit(), null);
        controller.addUnit(unit1);
        controller.addUnit(unit1);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));
        assertEquals(controller.getSelectedUnit(), null);
        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));
        assertEquals(controller.getSelectedUnit(), unit1);
        controller.selectUnitIn(0, 0);
        assertEquals(controller.getTurnOwner().getUnits(), List.of(unit1));
        assertEquals(controller.getSelectedUnit(), null);
        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        controller.endTurn();
        assertEquals(controller.getTurnOwner().getUnits(), List.of());
        assertEquals(controller.getSelectedUnit(), null);
    }

    @Test
    void getItems() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        item1 = new Spear("Spear", 10, 1, 3);
        item2 = new Staff("Staff", 10, 1, 3);

        controller.addUnit(unit1);
        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        controller.getSelectedUnit().addItem(item1);
        assertEquals(controller.getItems(), List.of(item1));
        controller.getTurnOwner().getUnits().get(0).addItem(item2);
        assertEquals(controller.getItems(), List.of(item1, item2));
        controller.getTurnOwner().getUnits().get(0).equipItem(item1);
        controller.getTurnOwner().getUnits().get(0).equipItem(null);
        assertEquals(controller.getItems(), List.of(item1, item2));
    }

    @Test
    void selectItem() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        item1 = new Spear("Spear", 10, 1, 3);
        item2 = new Staff("Staff", 10, 1, 3);

        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);
        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
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
    void getSelectedItem() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        item1 = new Spear("Spear", 10, 1, 3);
        item2 = new Staff("Staff", 10, 1, 3);

        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);
        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getSelectedItem(), null);
        controller.selectItem(0);
        assertEquals(controller.getSelectedItem(), item1);
        controller.selectItem(1);
        assertEquals(controller.getSelectedItem(), item2);
        controller.selectItem(2);
        assertEquals(controller.getSelectedItem(), null);
    }

    @Test
    void equipItem() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        item1 = new Spear("Spear", 10, 1, 3);
        item2 = new Staff("Staff", 10, 1, 3);
        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);
        controller.selectUnitIn(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getItems(), List.of(item1, item2));
        assertEquals(controller.getSelectedUnit().getEquippedItem(), null);
        controller.equipItem(1);
        assertEquals(controller.getSelectedUnit().getEquippedItem(), null);
        controller.equipItem(0);
        assertEquals(controller.getSelectedUnit().getEquippedItem(), item1);
    }

    @Test
    void useItemOn() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        if (controller.gameMap.getCell(sideSquare / 2 + 1, sideSquare / 2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare / 2 + 1, sideSquare / 2));
        unit2 = new Cleric(50, 2, controller.gameMap.getCell(sideSquare / 2 + 1, sideSquare / 2));
        item1 = new Spear("Spear", 10, 1, 3);
        controller.addUnit(unit2);
        controller.endTurn();

        unit1.addItem(item1);
        controller.addUnit(unit1);
        controller.selectUnitId(0);
        controller.equipItem(0);
        assertEquals(unit2.getCurrentHitPoints(), 50);
        controller.useItemOn(sideSquare / 2 + 1, sideSquare / 2);
        assertEquals(unit2.getCurrentHitPoints(), 40);
        controller.useItemOn(sideSquare / 2, sideSquare / 2);
        assertEquals(unit1.getCurrentHitPoints(), 50);
    }

    @Test
    void giveItemTo() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        if (controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare / 2 - 1, sideSquare / 2));
        if (controller.gameMap.getCell(sideSquare / 2 + 1, sideSquare / 2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare / 2 + 1, sideSquare / 2));
        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2));
        unit2 = new Cleric(50, 2, controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
        unit3 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2 + 1, sideSquare / 2));
        item1 = new Spear("Spear", 10, 1, 3);
        item2 = new Staff("Staff", 10, 1, 3);

        unit1.addItem(item1);
        unit1.addItem(item2);
        controller.addUnit(unit1);
        controller.addUnit(unit2);
        controller.addUnit(unit3);
        controller.selectUnitId(1);
        assertEquals(controller.getItems(), List.of());
        controller.selectUnitId(0);
        assertEquals(controller.getItems(), List.of(item1, item2));
        controller.selectItem(1);
        controller.giveItemTo(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getItems(), List.of(item1));
        controller.selectUnitId(1);
        assertEquals(controller.getItems(), List.of(item2));
        controller.selectItem(0);
        controller.giveItemTo(sideSquare / 2 - 1, sideSquare / 2);
        assertEquals(controller.getItems(), List.of());
        controller.selectUnitId(0);
        assertEquals(controller.getItems(), List.of(item1, item2));
        controller.selectItem(0);
        controller.giveItemTo(sideSquare / 2 + 1, sideSquare / 2);
        assertEquals(controller.getItems(), List.of(item1, item2));
    }

    @Test
    void moveUnitTo() {
        controller.initGameTest(-1);
        sideSquare = controller.gameMap.getSideSquare();
        if (controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare / 2 - 1, sideSquare / 2));
        if (controller.gameMap.getCell(sideSquare / 2 + 2, sideSquare / 2).getRow() == -1)
            controller.gameMap.addCells(true, new Location(sideSquare / 2 + 2, sideSquare / 2));

        unit1 = new Hero(50, 2, controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2));
        controller.addUnit(unit1);
        controller.selectUnitId(0);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2));
        controller.moveUnitTo(sideSquare, sideSquare);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2));
        controller.moveUnitTo(sideSquare / 2 + 2, sideSquare / 2);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.gameMap.getCell(sideSquare / 2 - 1, sideSquare / 2));
        controller.moveUnitTo(sideSquare / 2, sideSquare / 2);
        assertEquals(controller.getSelectedUnit().getLocation(), controller.gameMap.getCell(sideSquare / 2, sideSquare / 2));
    }

}