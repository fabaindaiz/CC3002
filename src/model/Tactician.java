package model;

import controller.observer.AbstractSubject;
import controller.observer.IObserver;
import model.items.IEquipableItem;
import model.items.otheritem.NullItem;
import model.map.Field;
import model.units.IUnit;
import model.units.otherunit.NullUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que representa a un jugador
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class Tactician extends AbstractSubject implements ITactician {

    protected final List<IUnit> units = new ArrayList<>();
    private final Field gameMap;
    private final String name;
    private final int tacticianNumber;

    private final IUnit nullUnit = new NullUnit();
    private final IEquipableItem nullItem = new NullItem();
    private IUnit selectedUnit = nullUnit;
    private IEquipableItem selectedItem = nullItem;

    /**
     * Crea un jugador para el juego (se ejecuta desde GameController)
     */
    public Tactician(String name, int number, Field map) {
        this.gameMap = map;
        this.tacticianNumber = number;
        this.name = name;
    }

    @Override
    public void addObserver(IObserver observer) {
        this.attach(observer);
    }

    @Override
    public void setNewTurn() {
        for (IUnit unit : units)
            unit.setNewTurn();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getTacticianNumber() {
        return tacticianNumber;
    }

    @Override
    public List<IUnit> getUnits() {
        return units;
    }

    @Override
    public void notifyObservers() {
        notifyAllObservers();
    }

    @Override
    public IUnit getNullUnit() {
        return nullUnit;
    }

    @Override
    public IEquipableItem getNullItem() {
        return nullItem;
    }

    @Override
    public void addUnit(IUnit unit) {
        if (unit != null && unit.getLocation().addUnitToCell(unit))
            units.add(unit);
    }

    @Override
    public void removeUnit(IUnit unit) {
        if (unit != null) {
            if (unit.getLocation() != null)
                unit.getLocation().setNullUnit();
            units.remove(unit);
        }

        notifyAllObservers();
    }

    @Override
    public void removeAllUnit() {
        List<IUnit> tempUnits = new ArrayList<IUnit>(units);
        for (IUnit unit : tempUnits) {
            removeUnit(unit);
        }
    }

    @Override
    public void selectUnitIn(int x, int y) {
        if (units.contains(gameMap.getCell(x, y).getUnit()))
            selectedUnit = gameMap.getCell(x, y).getUnit();
        else selectedUnit = nullUnit;
        selectedItem = nullItem;
    }

    @Override
    public void selectUnitId(int index) {
        if (units.size() > index && 0 <= index)
            selectedUnit = units.get(index);
        else selectedUnit = nullUnit;
        selectedItem = nullItem;
    }

    @Override
    public IUnit getSelectedUnit() {
        return selectedUnit;
    }

    @Override
    public List<IEquipableItem> getItems() {
        return selectedUnit.getItems();
    }

    @Override
    public void selectItem(int index) {
        if (selectedUnit.getItems().size() > index)
            selectedItem = selectedUnit.getItems().get(index);
        else selectedItem = nullItem;
    }

    @Override
    public IEquipableItem getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void equipItem(int index) {
        if (selectedUnit.getItems().size() > index)
            selectedUnit.equipItem(selectedUnit.getItems().get(index));
    }

    @Override
    public void useItemOn(int x, int y) {
        IUnit unit = gameMap.getCell(x, y).getUnit();
        if (!units.contains(unit))
            selectedUnit.useItem(unit, true);
    }

    @Override
    public void giveItemTo(int x, int y) {
        IUnit unit = gameMap.getCell(x, y).getUnit();
        if (unit != gameMap.getCell(x, y).getNullUnit() && units.contains(unit))
            selectedUnit.exchange(unit, selectedItem);
        selectedItem = nullItem;
    }

    @Override
    public void moveUnitTo(int x, int y) {
        if (gameMap.getCell(x, y).getUnit() == gameMap.getCell(x, y).getNullUnit())
            selectedUnit.moveTo(gameMap.getCell(x, y));
    }
}
