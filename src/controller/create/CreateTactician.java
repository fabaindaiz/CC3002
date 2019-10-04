package controller.create;

import model.Tactician;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CreateTactician implements ICreate {

    Map<String, Tactician> tacticians;

    public CreateTactician () {
        tacticians = new TreeMap<>();
    }

    public Tactician createTactician(String name) {
        Tactician newTactician = new Tactician(name);
        tacticians.put(name, newTactician);
        return newTactician;
    }

    public ArrayList<Tactician> getTacticians() {
        return new ArrayList<Tactician>(tacticians.values());
    }

    public void deleteTactician (String name) {
        tacticians.remove(name);
    }
}
