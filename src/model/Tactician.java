package model;

import controller.Observer.Observer;
import model.items.IEquipableItem;
import model.map.Field;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que representa a un jugador
 *
 * @author Fabián Díaz
 * @version 2.0
 * @since 2.0
 */
public class Tactician extends TacticianSubject implements ITactician {

    protected final List<IUnit> units = new ArrayList<>();
    private final Field gameMap;
    private final String name;

    private IUnit selectedUnit;
    private IEquipableItem selectedItem;

    /**
     * Crea un jugador para el juego (se ejecuta desde GameController)
     */
    public Tactician(String name, Field map) {
        this.gameMap = map;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IUnit> getUnits() {
        return units;
    }

    @Override
    public void addUnit(IUnit unit) {
        units.add(unit);
    }

    @Override
    public void removeUnit(IUnit unit) {
        unit.getLocation().setUnit(null);
        units.remove(unit);

        notifyAllObservers();
    }

    @Override
    public void removeAllUnit() {
        for (IUnit unit : units) {
            removeUnit(unit);
        }
    }

    @Override
    public void selectUnitIn(int x, int y) {
        if (units.contains(gameMap.getCell(x, y).getUnit()))
            selectedUnit = gameMap.getCell(x, y).getUnit();
        else selectedUnit = null;
        selectedItem = null;
    }

    @Override
    public void selectUnitId(int index) {
        if (units.size() > index)
            selectedUnit = units.get(index);
        else selectedUnit = null;
        selectedItem = null;
    }

    @Override
    public IUnit getSelectedUnit() {
        return selectedUnit;
    }

    @Override
    public List<IEquipableItem> getItems() {
        if (selectedUnit == null) return null;
        return selectedUnit.getItems();
    }

    @Override
    public void selectItem(int index) {
        if (selectedUnit == null) return;
        if (selectedUnit.getItems().size() > index)
            selectedItem = selectedUnit.getItems().get(index);
    }

    @Override
    public IEquipableItem getSelectedItem() {
        return selectedItem;
    }

    @Override
    public void equipItem(int index) {
        if (selectedUnit == null) return;
        if (selectedUnit.getItems().size() > index)
            selectedUnit.equipItem(selectedUnit.getItems().get(index));
    }

    @Override
    public void useItemOn(int x, int y) {
        if (selectedUnit == null) return;
        IUnit unit = gameMap.getCell(x, y).getUnit();
        selectedUnit.useItem(unit, true);
    }

    @Override
    public void giveItemTo(int x, int y) {
        if (selectedUnit == null || selectedItem == null) return;
        IUnit unit = gameMap.getCell(x, y).getUnit();
        if (unit != null)
            selectedUnit.exchange(unit, selectedItem);
        selectedItem = null;
    }
}
