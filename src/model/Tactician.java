package model;

import model.units.IUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tactician {

    protected final List<IUnit> units = new ArrayList<>();
    private final String name;

    public Tactician(String name) {
        this.name = name;
    }

    public Tactician(String name, final IUnit... units) {
        this.name = name;
        this.units.addAll(Arrays.asList(units));
    }

    public String getName() {
        return name;
    }
}
