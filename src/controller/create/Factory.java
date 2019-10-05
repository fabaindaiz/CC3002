package controller.create;

import model.map.Location;

import model.items.IEquipableItem;
import model.items.magicbook.Anima;
import model.items.magicbook.Dark;
import model.items.magicbook.Light;
import model.items.otheritem.Staff;
import model.items.weapon.Axe;
import model.items.weapon.Bow;
import model.items.weapon.Spear;
import model.items.weapon.Sword;

import model.units.IUnit;
import model.units.otherunit.Alpaca;
import model.units.otherunit.Cleric;
import model.units.sorcerer.Sorcerer;
import model.units.warrior.Archer;
import model.units.warrior.Fighter;
import model.units.warrior.Hero;
import model.units.warrior.SwordMaster;

public class Factory implements ICreate {

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

    @Override
    public IEquipableItem createItem(String type, final String name, final int power, final int minRange, final int maxRange) {
        switch (type.toLowerCase()) {
            case "anima":
                return new Anima(name, power, minRange, maxRange);
            case "dark":
                return new Dark(name, power, minRange, maxRange);
            case "light":
                return new Light(name, power, minRange, maxRange);
            case "staff":
                return new Staff(name, power, minRange, maxRange);
            case "bow":
                return new Bow(name, power, minRange, maxRange);
            case "axe":
                return new Axe(name, power, minRange, maxRange);
            case "spear":
                return new Spear(name, power, minRange, maxRange);
            case "sword":
                return new Sword(name, power, minRange, maxRange);
            default:
                return null;
        }
    }

}
