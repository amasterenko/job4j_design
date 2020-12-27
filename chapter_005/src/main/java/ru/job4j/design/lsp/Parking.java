package ru.job4j.design.lsp;

import java.util.List;

public interface Parking {
    boolean park(Vehicle vehicle);

    boolean unPark(Vehicle vehicle);

    List<Vehicle> getParkedVehicles();
}

