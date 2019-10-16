package model.items.otheritem;

import model.units.IUnit;

/**
 * Esta clase representa un item nula generica
 * <p>
 * Es usada para evitar usar null, implementando Null Pattern
 *
 * @author Fabián Díaz
 * @since 2.0
 */
public class NullItem extends OtherItem {

    /**
     * Crea una nueva unidad nula para ser usada por Tactician y Location
     */
    public NullItem() {
        super(null, 0, 1, 1);
    }

    @Override
    public void equipTo(IUnit unit) {
        unit.equipNullItem();
    }

    @Override
    public void specificAttack(IUnit other, boolean counterAttack) {
    }
}
