package controller;

import controller.observer.AbstractSubject;
import controller.observer.DefeatTacticianObserver;
import controller.observer.DefeatUnitObserver;
import controller.observer.EndGameObserver;
import controller.parameter.IParameter;
import controller.parameter.ItemParameter;
import controller.parameter.UnitParameter;
import model.Tactician;
import model.map.Field;
import model.units.IUnit;

import java.util.*;

/**
 * Objeto que representa un inicializador de la partida
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public abstract class GameInitialization extends AbstractSubject {

    protected final int numPlayers;
    protected Random random = new Random();
    protected long defaultSeed = random.nextLong();
    protected boolean initiatedGame = false;
    protected int defaultMapSize;
    protected GameController gameController;

    protected ArrayList<IParameter> parameters = new ArrayList<IParameter>();
    protected Map<String, Tactician> tacticians = new TreeMap<>();
    protected Field gameMap = new Field();

    protected ArrayList<UnitParameter> predefinedUnits;
    protected ArrayList<ItemParameter> predefinedItems;

    /**
     * Contructor de un inicializador de partidas
     * Por simplicidad, lo relacionado al inicio del juego pesta aqui
     *
     * @param numberOfPlayers numero de Tactician que juegan
     * @param mapSize Tamaño del mapa para el juego
     */
    public GameInitialization(int numberOfPlayers, int mapSize) {
        this.numPlayers = numberOfPlayers;
        this.defaultMapSize = mapSize;
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);

        for (int i = 0; i < numPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i, i, gameMap));
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public List<IParameter> getParameters() { return List.copyOf(parameters); }

    /**
     * Inicializa lo necesario para una nueva partida
     */
    public void initAll() {
        tacticians.clear();
        gameMap.clearMap();
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);

        for (int i = 0; i < numPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i, i, gameMap));

        for (IParameter parameter : parameters) {
            parameter.create(gameMap, new ArrayList<Tactician>(tacticians.values()));
        }

        getObservers().add(new EndGameObserver(gameController));
        for (Tactician tactician : tacticians.values()) {
            tactician.addObserver(new DefeatTacticianObserver(gameController, tactician));
            for (IUnit unit : tactician.getUnits()) {
                unit.addObserver(new DefeatUnitObserver(tactician, unit));
            }
        }


    }

}
