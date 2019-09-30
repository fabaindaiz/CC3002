package model.units;

import model.items.IEquipableItem;
import model.map.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;

/**
 * This class represents an abstract unit.
 * <p>
 * An abstract unit is a unit that cannot be used in the
 * game, but that contains the implementation of some of the methods that are common for most
 * units.
 *
 * @author Ignacio Slater Muñoz
 * @since 1.0
 */
public abstract class AbstractUnit implements IUnit {

    private final int maxHitPoints;
    private int currentHitPoints;
    private final int movement;
    private final int maxItems;
    private Location location;

    protected List<IEquipableItem> items = new ArrayList<>();
    protected IEquipableItem equippedItem;

    /**
     * Creates a new Unit.
     *
     * @param hitPoints the maximum amount of damage a unit can sustain
     * @param movement  the number of panels a unit can move
     * @param location  the current position of this unit on the map
     * @param maxItems  maximum amount of items this unit can carry
     */
    protected AbstractUnit(final int hitPoints, final int movement,
                           final Location location, final int maxItems, final IEquipableItem... items) {
        this.maxHitPoints = hitPoints;
        this.currentHitPoints = hitPoints;
        this.movement = movement;
        this.maxItems = maxItems;
        this.location = location;
        this.items.addAll(Arrays.asList(items).subList(0, min(maxItems, items.length)));
    }

    @Override
    public int getMaxHitPoints() { return maxHitPoints; }

    @Override
    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    @Override
    public void setCurrentHitPoints(int HP) {
        currentHitPoints = HP;
    }

    @Override
    public int getMovement() { return movement; }

    @Override
    public int getMaxItems() {
        return maxItems;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public List<IEquipableItem> getItems() {
        return List.copyOf(items);
    }

    @Override
    public IEquipableItem getEquippedItem() {
        return equippedItem;
    }

    /**
     * Verifica si el ataque esta fuera de rango
     *
     * @return un valor booleano segun la condicion
     * TRUE es fuera de rango
     * FALSE es dentro de rango
     */
    private boolean outOfRange(IUnit unit) {
        IEquipableItem item = unit.getEquippedItem();
        int distancia = (int) unit.getLocation().distanceTo(getLocation());
        return distancia < item.getMinRange() || distancia > item.getMaxRange();
    }

    /**
     * Mata a esta unidad, sacandola del mapa
     * De esta forma nadie puede interactuar con ella.
     * (Despues del SMAAAASH!! unit ha recibido daño mortal)
     */
    private void death() {
        location.setUnit(null);
        location = null;
    }

    /**
     * Sets a new location for this unit,
     */
    private void setLocation(final Location location) {
        this.location.setUnit(null);
        this.location = location;
        location.setUnit(this);
    }

    @Override
    public void moveTo(final Location targetLocation) {
        if (getLocation().distanceTo(targetLocation) <= getMovement()
                && targetLocation.getUnit() == null) {
            setLocation(targetLocation);
        }
    }

    @Override
    public boolean addItem(IEquipableItem item) {
        if (items.size() == maxItems)
            return false;
        items.add(item);
        return true;
    }

    @Override
    public void useItem(IUnit other, boolean counterattack) {
        if (equippedItem != null && other != null)
            equippedItem.useItem(other, counterattack);
    }

    @Override
    public void equipItem(final IEquipableItem item) {
        if (item != null && items.contains(item))
            item.equipTo(this);
    }

    /**
     * Recibe una cantidad especifica de daño en una unidad
     * - Contraataca dado el caso
     * - Mata a la unidad dado el caso
     *
     * @param item          Arma que esta atacando
     * @param damage        DAño que esta haciendo (Ya esta modificado)
     * @param counterAttack
     */
    private void receiveDamage(IEquipableItem item, int damage, boolean counterAttack) {
        if (damage < currentHitPoints) {
            if (damage > 0)
                this.currentHitPoints -= damage;
            if (counterAttack && equippedItem.counterattack())
                useItem(item.getOwner(), false);
        } else
            death();
    }

    @Override
    public void receiveHeal(IEquipableItem item) {
        if (outOfRange(item.getOwner())) return;
        int healed = item.getPower();
        if ((maxHitPoints - currentHitPoints) < healed)
            currentHitPoints = maxHitPoints;
        else
            this.currentHitPoints += healed;
    }

    @Override
    public void receiveAttack(IEquipableItem item, boolean counterAttack) {
        if (outOfRange(item.getOwner())) return;
        int damage = item.getPower();
        receiveDamage(item, damage, counterAttack);
    }

    @Override
    public void receiveWeaknessAttack(IEquipableItem item, boolean counterAttack) {
        if (outOfRange(item.getOwner())) return;
        int damage = (int) (item.getPower() * 1.5);
        receiveDamage(item, damage, counterAttack);
    }

    @Override
    public void receiveResistantAttack(IEquipableItem item, boolean counterAttack) {
        if (outOfRange(item.getOwner())) return;
        int damage = item.getPower() - 20;
        receiveDamage(item, damage, counterAttack);
    }

    /**
     * Verifica las condiciones para el intercambio
     *
     * @param unit Unidad de destino para el intercambio
     * @param item Item para intercambiar
     */
    private void exchangeCondition(IUnit unit, IEquipableItem item) {
        if (unit.addItem(item)) {
            if (equippedItem == item) {
                equippedItem = null;
                item.setOwner(null);
            }
            items.remove(item);
        }
    }

    @Override
    public void exchange(IUnit unit, IEquipableItem item) {
        if (unit != null && items.contains(item))
            if ((int) unit.getLocation().distanceTo(getLocation()) == 1)
                exchangeCondition(unit, item);
    }

    @Override
    public void equipMagicBook(IEquipableItem item) {
    }

    @Override
    public void equipAxe(IEquipableItem item) {
    }

    @Override
    public void equipSpear(IEquipableItem item) {
    }

    @Override
    public void equipSword(IEquipableItem item) {
    }

    @Override
    public void equipBow(IEquipableItem item) {
    }

    @Override
    public void equipStaff(IEquipableItem item) {
    }
}
