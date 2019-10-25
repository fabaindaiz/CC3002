package model.units.otherunit;

import model.items.IEquipableItem;
import model.map.InvalidLocation;
import model.map.Location;
import model.units.IUnit;

/**
 * Esta clase representa una unidad nula generica
 * <p>
 * Es usada para evitar usar null, implementando Null Pattern
 *
 * @author Fabián Díaz
 * @since 2.0
 */
public class NullUnit extends OtherUnit {

    /**
     * Crea una nueva unidad nula para ser usada por Tactician
     */
    public NullUnit() {
        super(0, 0, new InvalidLocation(), 0);
        super.setMaxAction(0);
    }

    /**
     * Crea una nueva unidad nula para ser usada por Location
     *
     * @param location ubicacion en el mapa
     */
    public NullUnit(Location location) {
        super(0, 0, location, 0);
        super.setMaxAction(0);
    }

    @Override
    public void setMaxAction(int maxAction) {}

    @Override
    public void moveTo(final Location targetLocation) {}

    @Override
    public boolean addItem(IEquipableItem item) {
        return false;
    }

    @Override
    public void useItem(IUnit other, boolean counterattack) {}

    @Override
    public void equipItem(final IEquipableItem item) {}

    @Override
    public void receiveHeal(IEquipableItem item, boolean counterattack) {}

    @Override
    public void receiveAttack(IEquipableItem item, boolean counterAttack) {}

    @Override
    public void exchange(IUnit unit, IEquipableItem item) {}
}
