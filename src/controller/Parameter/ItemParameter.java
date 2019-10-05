package controller.Parameter;

import model.Tactician;
import model.units.IUnit;

public class ItemParameter extends AbstractParameter {


    private final String name;
    private final int power;
    private final int minRange;
    private final int maxRange;
    private final IUnit owner;
    private final boolean equiped;


    public ItemParameter(final String name, final int power, final int minRange, final int maxRange, final IUnit owner, final boolean equiped) {
        this.name = name;
        this.power = power;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.owner = owner;
        this.equiped = equiped;
    }

    @Override
    public void create(Tactician tactician, IUnit unit) {

    }

}
