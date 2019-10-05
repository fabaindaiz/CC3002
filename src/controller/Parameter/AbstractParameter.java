package controller.Parameter;

import model.Tactician;
import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;

public abstract class AbstractParameter implements IParameter {

    public AbstractParameter() {}

    @Override
    public void create (Tactician tactician, IUnit unit) {}

}
