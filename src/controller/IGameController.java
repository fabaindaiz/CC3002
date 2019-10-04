package controller;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.List;

/**
 * This interface represents a Game Controller.
 * <p>
 * Este metodo es usado para agrupar la documentación y para facilitar algunas implementaciones
 *
 * @author Fabián Díaz
 * @since 2.0
 */
public interface IGameController {

    /**
     * @return the maximum number of rounds a match can last
     */
    int getMaxRounds();

    /**
     * @return the number of rounds since the start of the game.
     */
    int getRoundNumber();

    /**
     * @return the list of all the tacticians participating in this specific initiated Game.
     */
    List<Tactician> getTacticians();

    /**
     * @return the map of the current game
     */
    Field getGameMap();

    /**
     * @return the tactician that's currently playing
     */
    Tactician getTurnOwner();

    /**
     * Crea una unidad aleatoria
     */
    IUnit createRandomUnit();

    /**
     * Crea una unidad
     *
     * @param type Tipo de la unidad
     *             Los otros parametros son entregados directamente al constructor de abstractUnit
     */
    /*IUnit createUnit(String type, int hitPoints, int movement, Location location, IEquipableItem... items);*/

    /**
     * Añade una unidad a un tactician antes de inicializar en juego
     *
     * @param tactician Este tactician recibira la unidad
     * @param units     Unidad a añadir
     */
    void addUnit(Tactician tactician, IUnit... units);

    /**
     * Añade una unidad a un tactician ya uniciado el juego
     *
     * @param unit Unidad a añadir
     */
    void addUnit(IUnit... unit);

    /**
     * Establece una semilla para el generador de numero aleatorios
     *
     * @param seed semilla a configurar
     */
    void setSeed(long seed);

    /**
     * Cambia la semilla del mapa para el proximo initGame
     */
    void changeMap();

    /**
     * Starts the game.
     *
     * @param maxTurns the maximum number of turns the game can last
     */
    void initGame(int maxTurns);

    /**
     * Starts a game without a limit of turns.
     */
    void initEndlessGame();

    /**
     * Asigna el orden de los turnos antes de la ronda
     */
    void assignTurns();

    /**
     * Finishes the current player's turn.
     */
    void endTurn();

    /**
     * Removes a tactician and all of it's units from the game.
     *
     * @param tactician the player to be removed
     */
    void removeTactician(String tactician);

    /**
     * @return the winner of this game, if the match ends in a draw returns a list of all the winners
     */
    List<String> getWinners();

    void endGame();

    /**
     * Selects a unit in the game map
     *
     * @param x horizontal position of the unit
     * @param y vertical position of the unit
     */
    void selectUnitIn(int x, int y);

    /**
     * Selectes a unit in the ArrayList unit in Tactician
     *
     * @param index the location of the unit in the inventory.
     */
    void selectUnitId(int index);

    /**
     * @return the current player's selected unit
     */
    IUnit getSelectedUnit();

    /**
     * @return the inventory of the currently selected unit.
     */
    List<IEquipableItem> getItems();

    /**
     * Selects an item from the selected unit's inventory.
     *
     * @param index the location of the item in the inventory.
     */
    void selectItem(int index);

    /**
     * @return El item seleccionado por selectItem(index)
     */
    IEquipableItem getSelectedItem();

    /**
     * Equips an item from the inventory to the currently selected unit.
     *
     * @param index the location of the item in the inventory.
     */
    void equipItem(int index);

    /**
     * Uses the equipped item on a target
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    void useItemOn(int x, int y);

    /**
     * Gives the selected item to a target unit.
     *
     * @param x horizontal position of the target
     * @param y vertical position of the target
     */
    void giveItemTo(int x, int y);

}
