package controller;

import controller.observer.DefeatTacticianObserver;
import controller.observer.DefeatUnitObserver;
import controller.observer.EndGameObserver;
import controller.observer.Observer;
import controller.parameter.IParameter;
import controller.parameter.ItemParameter;
import controller.parameter.UnitParameter;
import model.Tactician;
import model.map.Field;
import model.units.IUnit;

import java.util.*;

public abstract class GameInitialization {

    private List<Observer> observers = new ArrayList<Observer>();

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

    public GameInitialization(int numberOfPlayers, int mapSize) {
        this.numPlayers = numberOfPlayers;
        this.defaultMapSize = mapSize;
        gameMap.setSeed(defaultSeed);
        gameMap.generateMap(defaultMapSize);

        for (int i = 0; i < numPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i, i, gameMap));

    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void setGameController (GameController gameController) {
        this.gameController = gameController;
    }

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

        observers.add(new EndGameObserver (gameController));
        for (Tactician tactician : tacticians.values()) {
            tactician.addObserver(new DefeatTacticianObserver(gameController, tactician));
            for (IUnit unit : tactician.getUnits()) {
                unit.addObserver(new DefeatUnitObserver(tactician, unit));
            }
        }


    }

}
