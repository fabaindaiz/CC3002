package controller;

import controller.Observer.DefeatTacticianObserver;
import controller.Observer.DefeatUnitObserver;
import controller.Observer.EndGameObserver;
import controller.Observer.Observer;
import controller.Parameter.IParameter;
import controller.Parameter.ItemParameter;
import controller.Parameter.UnitParameter;
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
            tacticians.put("Player " + i, new Tactician("Player " + i, gameMap));
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
        for (int i = 0; i < numPlayers; i++)
            tacticians.put("Player " + i, new Tactician("Player " + i, gameMap));

        for (IParameter parameter : parameters) {
            parameter.create(new ArrayList<Tactician>(tacticians.values()));
        }

        new EndGameObserver (gameController);
        for (Tactician tactician : tacticians.values()) {
            new DefeatTacticianObserver(gameController, tactician);
            for (IUnit unit : tactician.getUnits()) {
                new DefeatUnitObserver(tactician, unit);
            }
        }


    }

}
