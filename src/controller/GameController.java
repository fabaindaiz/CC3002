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

    private int maxRounds;
    private int roundNumber = 0;
    protected Tactician turnOwner;
    protected IUnit selectedUnit;
    protected IEquipableItem selectedItem;
    protected Field gameMap = new Field();
    protected Map<String, Tactician> tacticians = new TreeMap<>();

    /**
     * Creates the controller for a new game.
     *
     * @param numberOfPlayers the number of players for this game
     * @param mapSize         the dimensions of the map, for simplicity, all maps are squares
     */
    public GameController(int numberOfPlayers, int mapSize) {
        for (int i=0; i<numberOfPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i));

    }

    /**
     * @return the list of all the tacticians participating in the game.
     */
    public List<Tactician> getTacticians() { return new ArrayList<Tactician>(tacticians.values());
    }

    /**
     * @return the map of the current game
     */
    public Field getGameMap() {
        return gameMap;
    }

    /**
     * @return the tactician that's currently playing
     */
    public Tactician getTurnOwner() {
        return turnOwner;
    }

    /**
     * @return the number of rounds since the start of the game.
     */
    public int getRoundNumber() {
        return roundNumber;
    }

    /**
     * @return the maximum number of rounds a match can last
     */
    public int getMaxRounds() {
        return maxRounds;
    }

    /**
     * Finishes the current player's turn.
     */
    public void endTurn() {

    }

    /**
     * Removes a tactician and all of it's units from the game.
     *
     * @param tactician the player to be removed
     */
    public void removeTactician(String tactician) {
        tacticians.remove(tactician);
    }

    /**
     * Starts the game.
     *
     * @param maxTurns the maximum number of turns the game can last
     */
    public void initGame(final int maxTurns) {
        maxRounds = maxTurns;
    }

    /**
     * Starts a game without a limit of turns.
     */
    public void initEndlessGame() { //Cambiar --------------------------------------------------------------------------
        maxRounds = -1;
    }

    /**
     * @return the winner of this game, if the match ends in a draw returns a list of all the winners
     */
    public List<String> getWinners() {
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
        selectedUnit = gameMap.getCell(x,y).getUnit();
    }

    /**
     * @return the inventory of the currently selected unit.
     */
    public List<IEquipableItem> getItems() {
        if (unitNotNull(selectedUnit))
            return selectedUnit.getItems();
        else return null;
    }

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index the location of the item in the inventory.
     */
    public void equipItem(int index) {
        if (unitNotNull(selectedUnit) && selectedUnit.getItems().size() > index)
            selectedUnit.equipItem(selectedUnit.getItems().get(index));
    }

    /**
     * Uses the equipped item on a target
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void useItemOn(int x, int y) {
        IUnit unit = gameMap.getCell(x,y).getUnit();
        if (unitNotNull(selectedUnit, unit))
            selectedUnit.useItem(unit,true);
    }

    /**
     * Selects an item from the selected unit's inventory.
     *
     * @param index the location of the item in the inventory.
     */
    public void selectItem(int index) {
        if (unitNotNull(selectedUnit) && selectedUnit.getItems().size() > index)
            selectedItem = selectedUnit.getItems().get(index);
    }

    /**
     * Gives the selected item to a target unit.
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    public void giveItemTo(int x, int y) {
        IUnit unit = gameMap.getCell(x,y).getUnit();
        if (unitNotNull(selectedUnit, unit)) {
            selectedUnit.exchange(unit, selectedItem);
            selectedItem = null;
        }
    }

    public boolean unitNotNull(IUnit... units){
        for (IUnit unit : units)
            if (unit == null)
                return false;
        return true;
    }
}