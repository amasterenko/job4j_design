package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RegularPool implements PoolOfSpaces {
    private final List<Space> spaces = new ArrayList<>();

    public RegularPool(int size) {
        init(size);
    }

    @Override
    public boolean takeSpace(Vehicle vehicle, int needSpaces) {
        int freeSpaceIndex = findFreeSpacesIndex(needSpaces);
        if (freeSpaceIndex != -1) {
            for (int i = 0; i < needSpaces; i++) {
                spaces.get(freeSpaceIndex + i).take(vehicle);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean releaseSpace(Vehicle vehicle, int takenSpaces) {
        int takenSpaceIndex = findSpaceByVehicle(vehicle);
        if (takenSpaceIndex != -1) {
            for (int i = 0; i < takenSpaces; i++) {
                spaces.get(takenSpaceIndex + i).release();
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Vehicle> getTakenSpaces() {
        return spaces.stream()
                .filter(s -> !s.isEmpty())
                .map(Space::getParkedVehicle)
                .distinct()
                .collect(Collectors.toList());
    }

    private void init(int size) {
        for (int i = 0; i < size; i++) {
            spaces.add(new Space());
        }
    }

    private int findSpaceByVehicle(Vehicle vehicle) {
        for (int i = 0; i < spaces.size(); i++) {
            if (spaces.get(i).getParkedVehicle().equals(vehicle)) {
                return i;
            }
        }
        return -1;
    }

    private int findFreeSpacesIndex(int amountOfSequentialSpaces) {
        int firstFreeIndex = 0;
        int cnt = amountOfSequentialSpaces;
        for (int i = 0; i < spaces.size(); i++) {
            if (spaces.get(i).isEmpty()) {
                if (cnt == amountOfSequentialSpaces) {
                    firstFreeIndex = i;
                }
                cnt--;
                if (cnt == 0) {
                    return firstFreeIndex;
                }
            } else {
                cnt = amountOfSequentialSpaces;
            }
        }
        return -1;
    }
}
