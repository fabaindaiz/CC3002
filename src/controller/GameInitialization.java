package controller;

import controller.Parameter.IParameter;
import controller.create.Factory;
import controller.create.ICreate;
import model.Tactician;
import model.map.Field;

import java.util.*;

public abstract class GameInitialization {

    protected final int numPlayers;
    protected Random random = new Random();
    protected long defaultSeed = random.nextLong();
    protected boolean initiatedGame = false;
    protected int defaultMapSize;

    public ICreate factory = new Factory();
    protected ArrayList<IParameter> parameters;

    protected Map<String, Tactician> tacticians = new TreeMap<>();
    protected Field gameMap = new Field();

    public GameInitialization(int numberOfPlayers, int mapSize) {
        numPlayers = numberOfPlayers;
        defaultMapSize = mapSize;
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);

        for (int i = 0; i < numPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i, gameMap));
    }

    public void initAll() {
        tacticians.clear();
        for (int i = 0; i < numPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i, gameMap));
        for (IParameter parameter:parameters) {
            parameter.create(factory, new ArrayList<Tactician>(tacticians.values()));
        }

    }

}
