package controller;

import controller.parameter.IParameter;
import controller.parameter.ItemParameter;
import controller.parameter.UnitParameter;
import model.ITactician;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;
import model.units.sorcerer.Sorcerer;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController extends GameInitialization implements IGameController {

    protected Tactician turnOwner;
    private int turnInRound;
    private Tactician lastTurn;
    private ArrayList<Tactician> turns = new ArrayList<Tactician>();
    private int maxRounds;
    private int roundNumber;

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize) {
        super(numberOfPlayers, mapSize);
        setGameController(this);
        assignTurns();
        turnOwner = turns.get(0);
    }

    @Override
    public boolean getInitiatedGameStatus() { return initiatedGame; }

    @Override
    public int getMaxRounds() {
        return maxRounds;
    }

    @Override
    public int getRoundNumber() {
        return roundNumber + 1;
    }

    @Override
    public List<Tactician> getTacticians() {
        return new ArrayList<Tactician>(tacticians.values());
    }

    @Override
    public Field getGameMap() {
        return gameMap;
    }

    @Override
    public Tactician getTurnOwner() {
        return turnOwner;
    }

    /*@Override
    public void createRandomUnit() {
    }

    @Override
    public void CreatePredefinedUnit() {
    }*/

    @Override
    public IParameter createUnit(String type, int hitPoints, int movement, Location location, IEquipableItem... items) {
        turnOwner.addUnit(new Sorcerer(hitPoints, movement, location));
        IParameter parameter = new UnitParameter(type, hitPoints, movement, location, turnOwner.getTacticianNumber());
        if (parameter.getType() != null)
            parameters.add(parameter);
        return parameter;
    }

    /*@Override
    public void createRandomItem() {
    }

    @Override
    public void createPredefinedItem() {
    }*/

    @Override
    public IParameter createItem(String type, final String name, final int power, final int minRange, final int maxRange, boolean equiped) {
        IParameter parameter = new ItemParameter(type, name, power, minRange, maxRange, turnOwner.getTacticianNumber(),
                turnOwner.getUnits().indexOf(turnOwner.getSelectedUnit()), equiped);
        if (parameter.getType() != null)
            parameters.add(parameter);
        return parameter;
    }

    @Override
    public void addUnit(IUnit... units) {
        for (IUnit unit : units) {
            turnOwner.addUnit(unit);
        }
    }

    @Override
    public void addItem(IEquipableItem... items) {
        for (IEquipableItem item : items) {
            turnOwner.getSelectedUnit().addItem(item);
        }
    }

    @Override
    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    @Override
    public void changeMap(Long... seed) {
        if (seed.length > 0)
            defaultSeed = seed[0];
        else
            defaultSeed = random.nextLong();
        gameMap.clearMap();
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);
    }

    @Override
    public void initGame(final int maxTurns) {
        initGameTest(maxTurns);

        List<Tactician> tacticians = getTacticians();
        for (Tactician tactician : tacticians)
            tactician.notifyObservers();
    }

    @Override
    public void initGameTest(final int maxTurns) {
        initiatedGame = true;
        initAll();
        assignTurns();

        turnOwner = turns.get(0);
        maxRounds = maxTurns;
        roundNumber = 0;
        turnInRound = 0;
        notifyAllObservers();
    }

    @Override
    public void initEndlessGame() {
        initGame(-1);
    }

    @Override
    public void assignTurns() {
        do {
            generateRandomTurn();
        } while (lastTurn == turns.get(0));
        lastTurn = turns.get(turns.size() - 1);
        turnOwner = turns.get(0);
    }

    /**
     * Genera los turnos de forma aleatoria (se ejecuta desde assignTurns)
     */
    private void generateRandomTurn() {
        int generated;
        turns.clear();
        List<Tactician> tacticiansTemp = getTacticians();
        for (int i = 0; i < tacticians.size(); i++) {
            generated = (int) (random.nextFloat() * tacticiansTemp.size());
            turns.add(tacticiansTemp.get(generated));
            tacticiansTemp.remove(generated);
        }
    }

    @Override
    public void endTurn() {
        turnOwner.selectUnitId(-1);
        verificateEndRound();
        turnOwner = turns.get(turnInRound);
        turnOwner.setNewTurn();
    }

    /**
     * Verifica si la ronda termino al finalizar un turno (se ejecuta desde endTurn)
     */
    private void verificateEndRound() {
        turnInRound++;
        if (turnInRound >= tacticians.size()) {
            turnInRound = 0;
            roundNumber++;
            assignTurns();
        }
    }

    @Override
    public void removeTactician(String tactician) {
        Tactician tacticianTemp = tacticians.get(tactician);
        if (tacticianTemp != null) {
            tacticianTemp.removeAllUnit();
            turns.remove(tacticianTemp);
            tacticians.remove(tactician);
        }
        notifyAllObservers();
    }

    @Override
    public List<String> getWinners() {
        if (roundNumber == maxRounds || tacticians.size() == 1) {
            int maxUnits = 0;
            ArrayList<String> winners = new ArrayList<String>();

            for (Tactician tactician : tacticians.values()) {
                if (tactician.getUnits().size() > maxUnits) {
                    maxUnits = tactician.getUnits().size();
                    winners.clear();
                    winners.add(tactician.getName());
                } else if (tactician.getUnits().size() == maxUnits)
                    winners.add(tactician.getName());
            }
            return winners;
        }
        return null;
    }

    @Override
    public void endGame() {
        initiatedGame = false;
    }

    @Override
    public List<IUnit> getUnits() {return turnOwner.getUnits(); }

    @Override
    public void selectUnitIn(int x, int y) {
        turnOwner.selectUnitIn(x, y);
    }

    @Override
    public void selectUnitId(int index) {
        turnOwner.selectUnitId(index);
    }

    @Override
    public IUnit getSelectedUnit() {
        return turnOwner.getSelectedUnit();
    }

    @Override
    public List<IEquipableItem> getItems() {
        return turnOwner.getItems();
    }

    @Override
    public void selectItem(int index) {
        if (initiatedGame) turnOwner.selectItem(index);
    }

    @Override
    public IEquipableItem getSelectedItem() {
        return turnOwner.getSelectedItem();
    }

    @Override
    public void equipItem(int index) {
        if (initiatedGame) turnOwner.equipItem(index);
    }

    @Override
    public void useItemOn(int x, int y) {
        if (initiatedGame) turnOwner.useItemOn(x, y);
    }

    @Override
    public void giveItemTo(int x, int y) {
        if (initiatedGame) turnOwner.giveItemTo(x, y);
    }

    @Override
    public void moveUnitTo(int x, int y) {
        if (initiatedGame) turnOwner.moveUnitTo(x, y);
    }

}