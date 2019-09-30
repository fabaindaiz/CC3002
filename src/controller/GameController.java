package controller;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

import java.util.*;

/**
 * Controller of the game.
 * The controller manages all the input received from de game's GUI.
 *
 * @author Ignacio Slater Muñoz
 * @version 2.0
 * @since 2.0
 */
public class GameController implements IGameController {

    private Random random = new Random();
    private long defaultSeed = random.nextLong();
    private boolean initiatedGame = false;
    private int defaultMapSize;
    private int turnInRound;
    private Tactician lastTurn;

    private Map<String, Tactician> tacticiansGame = new TreeMap<>();
    private Map<String, Tactician> tacticians = new TreeMap<>();
    private ArrayList<Tactician> turns = new ArrayList<Tactician>();

    private int maxRounds;
    private int roundNumber;

    protected Field gameMap = new Field();
    private Tactician turnOwner;
    private IUnit selectedUnit;
    private IEquipableItem selectedItem;

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize) {
        for (int i = 0; i < numberOfPlayers; i++)
            tacticiansGame.put("Player " + i, new Tactician("Player " + i));
        initTacticians(tacticiansGame, tacticians);
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(mapSize);
        defaultMapSize = mapSize;
    }

    @Override
    public int getMaxRounds() {
        return maxRounds;
    }

    @Override
    public int getRoundNumber() {
        return roundNumber + 1;
    }

    @Override
    public List<Tactician> getTacticiansGame() {
        return new ArrayList<Tactician>(tacticiansGame.values());
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

    @Override
    public void addUnit(IUnit... units) {
        for (IUnit unit:units) {
            if (unit.getLocation().addUnitToCell(unit))
                turnOwner.addUnit(unit);
        }
    }

    @Override
    public void setSeed(long seed) {
        random.setSeed(seed);
    }

    @Override
    public void changeMap() {
        defaultSeed = random.nextLong();
    }

    /**
     * Verificación estandar antes de realizar una accion con una selectedUnit
     *
     * @return true: algo va mal, no ejecuta de la función que se llamo | false: continua normal
     */
    private boolean standardVerification() {
        if (!initiatedGame || selectedUnit == null) return true;
        return false;
    }

    @Override
    public void initGame(final int maxTurns) {
        tacticians.clear();
        initTacticians(tacticiansGame, tacticians);
        initiatedGame = true;
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);
        assignTurns();

        turnOwner = turns.get(0);
        maxRounds = maxTurns;
        roundNumber = 0;
        turnInRound = 0;
    }

    @Override
    public void initEndlessGame() { initGame(-1); }

    private void initTacticians(Map<String, Tactician> tacticiansGame, Map<String, Tactician> tacticians) {
        tacticians.clear();
        for (Tactician tactician:tacticiansGame.values()) {
            tacticians.put(tactician.getName(),tactician.clone());
        }
    }

    @Override
    public void assignTurns() {
        if (!initiatedGame) return;
        do {
            generateRandomTurn();
        } while ( lastTurn == turns.get(0) );
        lastTurn = turns.get(turns.size()-1);
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
        selectedUnit = null;
        selectedItem = null;
        verificateEndRound();
        turnOwner = turns.get(turnInRound);
    }

    /**
     * Verifica si la ronda termino al finalizar un turno (se ejecuta desde endTurn)
     */
    private void verificateEndRound() {
        turnInRound++;
        if (turnInRound == tacticians.size()) {
            turnInRound = 0;
            roundNumber++;
            assignTurns();
        }
    }

    @Override
    public void removeTactician(String tactician) {
        tacticians.remove(tactician);
    }

    @Override
    public List<String> getWinners() {
        if (roundNumber == maxRounds || tacticians.size() == 1) {
            int maxUnits = 0;
            ArrayList<String> winners = new ArrayList<String>();
            for (Tactician tactician:tacticians.values()) {
                if (tactician.getUnits().size() > maxUnits) {
                    maxUnits = tactician.getUnits().size();
                    winners.clear();
                    winners.add(tactician.getName());
                }
                else if (tactician.getUnits().size() == maxUnits)
                    winners.add(tactician.getName());
            }
            return winners;
        }
        return null;
    }

    @Override
    public void selectUnitIn(int x, int y) {
        if (turnOwner.getUnits().contains(gameMap.getCell(x,y).getUnit()))
            selectedUnit = gameMap.getCell(x, y).getUnit();
        else
            selectedUnit = null;
        selectedItem = null;
    }

    @Override
    public void selectUnitId(int index){
        if (turnOwner.getUnits().size() > index)
            selectedUnit = turnOwner.getUnits().get(index);
        else
            selectedUnit = null;
        selectedItem = null;
    }

    @Override
    public IUnit getSelectedUnit() { return selectedUnit; }

    @Override
    public List<IEquipableItem> getItems() {
        if (standardVerification()) return null;
        else return selectedUnit.getItems();
    }

    @Override
    public void selectItem(int index) {
        if (standardVerification()) return;
        if (selectedUnit.getItems().size() > index)
            selectedItem = selectedUnit.getItems().get(index);
    }

    @Override
    public IEquipableItem getSelectedItem() { return selectedItem; }

    @Override
    public void equipItem(int index) {
        if (standardVerification()) return;
        if (selectedUnit.getItems().size() > index)
            selectedUnit.equipItem(selectedUnit.getItems().get(index));
    }

    @Override
    public void useItemOn(int x, int y) {
        if (standardVerification()) return;
        IUnit unit = gameMap.getCell(x,y).getUnit();
        selectedUnit.useItem(unit,true);
    }

    @Override
    public void giveItemTo(int x, int y) {
        if (standardVerification() || selectedItem == null) return;
        IUnit unit = gameMap.getCell(x,y).getUnit();
        if (unit != null)
            selectedUnit.exchange(unit, selectedItem);
        selectedItem = null;
    }

}