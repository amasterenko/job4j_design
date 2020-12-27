package ru.job4j.design.lsp;

import java.util.List;

public interface PoolOfSpaces {

    boolean takeSpace(Vehicle vehicle, int needSpaces);

    boolean releaseSpace(Vehicle vehicle, int takenSpaces);

    List<Vehicle> getTakenSpaces();

}
