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

    protected final List<IEquipableItem> items = new ArrayList<>();
    private final int maxHitPoints;
    private int currentHitPoints;
    private final int movement;
    private final int maxItems;
    protected IEquipableItem equippedItem;
    private Location location;

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
    public boolean getDeathStatus() {
        return location != null;
    }

    @Override
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    @Override
    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    @Override
    public void setCurrentHitPoints(int HP) {
        currentHitPoints = HP;
    }

    @Override
    public int getMaxItems() {
        return maxItems;
    }

    @Override
    public List<IEquipableItem> getItems() {
        return List.copyOf(items);
    }

    @Override
    public IEquipableItem getEquippedItem() {
        return equippedItem;
    }

    @Override
    public void setEquippedItem(final IEquipableItem item) {
        this.equippedItem = item;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(final Location location) {
        this.location = location;
    }

    @Override
    public int getMovement() {
        return movement;
    }

    @Override
    public void moveTo(final Location targetLocation) {
        if (!getDeathStatus()) return;
        if (getLocation().distanceTo(targetLocation) <= getMovement()
                && targetLocation.getUnit() == null) {
            setLocation(targetLocation);
        }
    }

    @Override
    public void muerte() {
        location = null;
    }

    @Override
    public void attack(IUnit other, boolean counterattack) {
        if (!getDeathStatus() || !other.getDeathStatus()) return;
        if (equippedItem != null)
            equippedItem.attack(other, counterattack);
    }

    @Override
    public boolean outOfRange(IUnit unit) {
        if (!getDeathStatus()) return true;
        IEquipableItem item = unit.getEquippedItem();
        int distancia = (int) getLocation().distanceTo(unit.getLocation());
        return distancia < item.getMinRange() || distancia > item.getMaxRange();
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

        if (damage < currentHitPoints) {
            this.currentHitPoints -= damage;
            if (counterAttack && equippedItem.counterattack())
                attack(item.getOwner(), false);
        } else
            muerte();
    }

    @Override
    public void receiveWeaknessAttack(IEquipableItem item, boolean counterAttack) {
        if (outOfRange(item.getOwner())) return;
        int damage = (int) (item.getPower() * 1.5);

        if (damage < currentHitPoints) {
            this.currentHitPoints -= damage;
            if (counterAttack && equippedItem.counterattack())
                attack(item.getOwner(), false);
        } else
            muerte();
    }

    @Override
    public void receiveResistantAttack(IEquipableItem item, boolean counterAttack) {
        if (outOfRange(item.getOwner())) return;
        int damage = item.getPower() - 20;

        if (damage < currentHitPoints) {
            if (damage > 0)
                this.currentHitPoints -= damage;
            if (counterAttack && equippedItem.counterattack())
                attack(item.getOwner(), false);
        } else
            muerte();
    }

    @Override
    public boolean addItem(IEquipableItem item) {
        if (items.size() == maxItems) {
            return false;
        }
        items.add(item);
        return true;
    }

    @Override
    public void exchange(IUnit unit, IEquipableItem item) {
        if (!getDeathStatus() || !unit.getDeathStatus()) return;
        if (items.contains(item) && (int) getLocation().distanceTo(unit.getLocation()) == 1) {
            if (unit.addItem(item)) {
                if (equippedItem == item) {
                    equippedItem = null;
                    item.setOwner(null);
                }
                items.remove(item);
            }
        }
    }
}
