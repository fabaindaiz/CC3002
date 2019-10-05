package controller.create;

import model.Tactician;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CreateTactician extends AbstractFactory {

    Map<String, Tactician> tacticians;

    public CreateTactician() {
        tacticians = new TreeMap<>();
    }

    public Tactician createTactician(String name) {
        return null;
    }

    public ArrayList<Tactician> getTacticians() {
        return new ArrayList<Tactician>(tacticians.values());
    }

    public void deleteTactician(String name) {
        tacticians.remove(name);
    }
}
