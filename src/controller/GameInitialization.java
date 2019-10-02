package controller;

import model.Tactician;
import model.map.Field;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class GameInitialization {

    protected Random random = new Random();
    protected long defaultSeed = random.nextLong();

    protected boolean initiatedGame = false;
    protected final int numPlayers;
    protected int defaultMapSize;

    protected Map<String, Tactician> tacticians = new TreeMap<>();
    protected Field gameMap = new Field();

    public GameInitialization(int numberOfPlayers, int mapSize){
        numPlayers = numberOfPlayers;
        defaultMapSize = mapSize;
    }

    public void initAll() {
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);
        for (int i = 0; i < numPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i));
    }

}
