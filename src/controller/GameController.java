package controller;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

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
        initAll();
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
    public IUnit createRandomUnit() {
        return null;
    }

    @Override
    public void addUnit(Tactician tactician, IUnit... units) {
        for (IUnit unit : units) {
            if (unit.getLocation().addUnitToCell(unit))
                tactician.addUnit(unit);
        }
    }

    @Override
    public void addUnit(IUnit... units) {
        for (IUnit unit : units) {
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

    @Override
    public void initGame(final int maxTurns) {
        initiatedGame = true;
        initAll();
        assignTurns();

        turnOwner = turns.get(0);
        maxRounds = maxTurns;
        roundNumber = 0;
        turnInRound = 0;
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
        verificateEndRound();
        turnOwner = turns.get(turnInRound);
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
    public void selectUnitIn(int x, int y) {
        if (initiatedGame) turnOwner.selectUnitIn(x, y);
    }

    @Override
    public void selectUnitId(int index) {
        if (initiatedGame) turnOwner.selectUnitId(index);
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

}