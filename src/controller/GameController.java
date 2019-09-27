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
public class GameController {

    private boolean initiatedGame = false;
    private int maxRounds;
    private int roundNumber;
    private int turnInRound;
    private Random random = new Random();

    protected Tactician lastTurn;
    protected ArrayList<Tactician> turns = new ArrayList<Tactician>();
    protected Tactician turnOwner;
    protected IUnit selectedUnit;
    protected IEquipableItem selectedItem;
    protected Field gameMap = new Field();

    private Map<String, Tactician> tacticiansGame = new TreeMap<>();
    protected Map<String, Tactician> tacticians = new TreeMap<>();

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize) {
        for (int i=0; i<numberOfPlayers; i++)
            tacticiansGame.put("Player " + i, new Tactician("Player " + i));
    }

    public void setSeed (long seed) { random.setSeed(seed); }

    public void assignTurns() {
        if (!initiatedGame) return;
         do {
             generateRandomTurn();
        } while ( lastTurn == turns.get(0) );
        lastTurn = turns.get(turns.size()-1);
        turnOwner = turns.get(0);
    }

    public void generateRandomTurn() {
        turns.clear();
        List<Tactician> tacticiansTemp = getTacticians();
        for (int i = 0; i < tacticians.size(); i++) {
            int generated = (int) random.nextFloat() * tacticiansTemp.size();
            turns.add(tacticiansTemp.get(generated));
            tacticiansTemp.remove(generated);
        }
    }

    /**
     * @return the list of all the tacticians participating in the game.
     */
    public List<Tactician> getTacticians() {
        return initiatedGame ? new ArrayList<Tactician>(tacticians.values()) : new ArrayList<Tactician>(tacticiansGame.values());
    }

    /**
     * @return the map of the current game
     */
    public Field getGameMap() { return gameMap; }

    /**
     * @return the tactician that's currently playing
     */
    public Tactician getTurnOwner() { return turnOwner; }

    /**
     * @return the number of rounds since the start of the game.
     */
    public int getRoundNumber() { return roundNumber +1; }

    /**
     * @return the maximum number of rounds a match can last
     */
    public int getMaxRounds() { return maxRounds; }

    /**
     * Finishes the current player's turn.
     */
    public void endTurn() {
        selectedUnit = null;
        selectedItem = null;
        verificateEndRound();
        turnOwner = turns.get(turnInRound);
    }

    public void verificateEndRound() {
        turnInRound++;
        if (turnInRound == tacticians.size()) {
            turnInRound = 0;
            roundNumber++;
            assignTurns();
        }
    }

    /**
     * Removes a tactician and all of it's units from the game.
     *
     * @param tactician the player to be removed
     */
    public void removeTactician(String tactician) {
        if(!initiatedGame) return;
        tacticians.remove(tactician);
    }

    /**
     * Starts the game.
     *
     * @param maxTurns the maximum number of turns the game can last
     */
    public void initGame(final int maxTurns) {
        tacticians.putAll(tacticiansGame);
        initiatedGame = true;
        assignTurns();

        turnOwner = turns.get(0);
        maxRounds = maxTurns;
        roundNumber = 0;
        turnInRound = 0;
    }

    /**
     * Starts a game without a limit of turns.
     */
    public void initEndlessGame() { initGame(-1); }

    /**
     * @return the winner of this game, if the match ends in a draw returns a list of all the winners
     */
    public List<String> getWinners() {
        if (roundNumber == maxRounds || tacticians.size() == 1) {
            return new ArrayList<String>(tacticians.keySet());
        }
        return null;
    }

    /**
     * @return the current player's selected unit
     */
    public IUnit getSelectedUnit() { return selectedUnit; }

    /**
     * Selects a unit in the game map
     *
     * @param x horizontal position of the unit
     * @param y vertical position of the unit
     */
    public void selectUnitIn(int x, int y) {
        if (standarVerification()) return;
        selectedUnit = gameMap.getCell(x,y).getUnit();
        selectedItem = null;
    }

    /**
     * @return the inventory of the currently selected unit.
     */
    public List<IEquipableItem> getItems() {
        if (standarVerification()) return null;
        else return selectedUnit.getItems();
    }

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index the location of the item in the inventory.
     */
    public void equipItem(int index) {
        if (standarVerification()) return;
        if (selectedUnit.getItems().size() > index)
            selectedUnit.equipItem(selectedUnit.getItems().get(index));
    }

    /**
     * Uses the equipped item on a target
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void useItemOn(int x, int y) {
        if (standarVerification()) return;
        IUnit unit = gameMap.getCell(x,y).getUnit();
        selectedUnit.useItem(unit,true);
    }

    /**
     * Selects an item from the selected unit's inventory.
     *
     * @param index the location of the item in the inventory.
     */
    public void selectItem(int index) {
        if (standarVerification()) return;
        if (selectedUnit.getItems().size() > index)
            selectedItem = selectedUnit.getItems().get(index);
    }

    /**
     * Gives the selected item to a target unit.
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void giveItemTo(int x, int y) {
        if (standarVerification()) return;
        IUnit unit = gameMap.getCell(x,y).getUnit();
        selectedUnit.exchange(unit, selectedItem);
        selectedItem = null;
    }

    public boolean standarVerification() {
        if (!initiatedGame || selectedUnit == null) return true;
        return false;
    }
}