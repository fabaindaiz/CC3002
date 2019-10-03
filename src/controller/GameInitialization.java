package controller;

import model.Tactician;
import model.map.Field;
import model.units.IUnit;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class GameInitialization {

    protected final int numPlayers;
    protected Random random = new Random();
    protected long defaultSeed = random.nextLong();
    protected boolean initiatedGame = false;
    protected int defaultMapSize;

    protected Map<String, Tactician> tacticiansGame = new TreeMap<>();
    protected Map<String, Tactician> tacticians = new TreeMap<>();
    protected Field gameMap = new Field();

    public GameInitialization(int numberOfPlayers, int mapSize) {
        numPlayers = numberOfPlayers;
        defaultMapSize = mapSize;
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);

        for (int i = 0; i < numPlayers; i++)
            tacticiansGame.put("Player " + i, new Tactician("Player " + i));
    }

    public void initAll() {
        tacticians.clear();
        for (Tactician tactician : tacticiansGame.values()) {
            for (IUnit unit : tactician.getUnits()) {

                IUnit actualUnit = unit.clone();
            }
            Tactician actualTactician =  tactician.clone();
            tacticians.put(tactician.getName(), actualTactician);
        }
    }

}
