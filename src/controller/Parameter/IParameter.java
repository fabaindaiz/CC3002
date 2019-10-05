package controller.Parameter;

import controller.create.ICreate;
import model.Tactician;
import model.units.IUnit;

import java.util.ArrayList;

public interface IParameter {

    /**
     * Crea el objeto dependiendo de sus parametros
     *
     * @param factory   Fabrica
     * @param tacticians Lista con los tacticians
     * @param units      Lista con las unidades
     */
    void create (ICreate factory, ArrayList<Tactician> tacticians);
}