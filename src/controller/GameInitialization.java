package controller;

import controller.Parameter.IParameter;
import controller.Parameter.ItemParameter;
import controller.Parameter.UnitParameter;
import model.Tactician;
import model.items.IEquipableItem;
import model.map.Field;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class GameInitialization {

    protected final int numPlayers;
    protected Random random = new Random();
    protected long defaultSeed = random.nextLong();
    protected boolean initiatedGame = false;
    protected int defaultMapSize;

    protected ArrayList<IParameter> parameters = new ArrayList<IParameter>();
    protected Map<String, Tactician> tacticians = new TreeMap<>();
    protected Field gameMap = new Field();

    protected ArrayList<UnitParameter> predefinedUnits;
    protected ArrayList<ItemParameter> predefinedItems;

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
        for (IParameter parameter : parameters) {
            parameter.create(new ArrayList<Tactician>(tacticians.values()));
        }

    }

}
