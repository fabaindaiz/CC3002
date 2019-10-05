package controller.create;

import model.items.IEquipableItem;
import model.map.Location;
import model.units.IUnit;
import model.units.otherunit.Alpaca;
import model.units.otherunit.Cleric;
import model.units.sorcerer.Sorcerer;
import model.units.warrior.Archer;
import model.units.warrior.Fighter;
import model.units.warrior.Hero;
import model.units.warrior.SwordMaster;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CreateUnit extends AbstractFactory {

    public CreateUnit() {}

    public IUnit createUnit(IUnit unit) {
        /*IUnit newTactician = new (name);
        units.put(name, newTactician);
        return newTactician;*/
        return null;
    }

    @Override
    public IUnit createUnit(String type, final int hitPoints, final int movement, final Location location, IEquipableItem... items) {
        switch (type.toLowerCase()) {
            case "alpaca":
                return new Alpaca(hitPoints, movement, location, items);
            case "cleric":
                return new Cleric(hitPoints, movement, location, items);
            case "sorcerer":
                return new Sorcerer(hitPoints, movement, location, items);
            case "archer":
                return new Archer(hitPoints, movement, location, items);
            case "fighter":
                return new Fighter(hitPoints, movement, location, items);
            case "hero":
                return new Hero(hitPoints, movement, location, items);
            case "swordmaster":
                return new SwordMaster(hitPoints, movement, location, items);

            default:
                return null;
        }
    }

}
