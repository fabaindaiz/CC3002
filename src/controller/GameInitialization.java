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

    protected Map<String, Tactician> tacticiansMaster = new TreeMap<>();
    protected Map<String, Tactician> tacticians = new TreeMap<>();
    protected Field gameMapMaster = new Field();
    protected Field gameMap = new Field();

    public GameInitialization(int numberOfPlayers, int mapSize) {
        numPlayers = numberOfPlayers;
        defaultMapSize = mapSize;
        gameMapMaster.setSeed(defaultSeed);
        gameMapMaster.generateMap(defaultMapSize);

        for (int i = 0; i < numPlayers; i++)
            tacticiansMaster.put("Player " + i, new Tactician("Player " + i));
    }

    public void initAll() {
        tacticians.clear();
        for (Tactician tactician : tacticiansMaster.values()) {
            /**for (IUnit unit : tactician.getUnits()) {
                IUnit actualUnit = unit.clone();
            }**/
            Tactician actualTactician =  tactician.clone();
            tacticians.put(tactician.getName(), actualTactician);
        }

    }

}
