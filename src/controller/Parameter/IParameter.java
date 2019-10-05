package controller.Parameter;

import model.Tactician;
import model.units.IUnit;

public interface IParameter {
    void create (Tactician tactician, IUnit unit);
}