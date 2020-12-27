package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarsAndTrucksParking implements Parking {
    private final PoolOfSpaces carSpaces;
    private final PoolOfSpaces truckSpaces;

    public CarsAndTrucksParking(int numberOfCarsSpaces, int numberOfTrucksSpaces) {
        if (numberOfCarsSpaces < 0 || numberOfTrucksSpaces < 0) {
            throw new IllegalArgumentException("Arguments must be grater than 0!");
        }
        this.carSpaces = new CarSpacesPool(numberOfCarsSpaces);
        this.truckSpaces = new TruckSpacesPool(numberOfTrucksSpaces);
    }

    @Override
    public boolean park(Vehicle vehicle) {
        if (vehicle.needsParkingUnits() == 1) {
            return carSpaces.takeSpace(vehicle, 1);
        } else {
            if (truckSpaces.takeSpace(vehicle, 1)) {
                return true;
            }
            return carSpaces.takeSpace(vehicle, vehicle.needsParkingUnits());
        }
    }

    @Override
    public boolean unPark(Vehicle vehicle) {
        if (vehicle.needsParkingUnits() == 1) {
            return carSpaces.releaseSpace(vehicle, 1);
        } else {
            if (truckSpaces.releaseSpace(vehicle, 1)) {
                return true;
            }
            return carSpaces.releaseSpace(vehicle, vehicle.needsParkingUnits());
        }
    }

    @Override
    public List<Vehicle> getParkedVehicles() {
        List<Vehicle> resultList = new ArrayList<>();
        resultList.addAll(carSpaces.getTakenSpaces());
        resultList.addAll(truckSpaces.getTakenSpaces());
        resultList.sort(Comparator.comparing(Vehicle::getRegNumber));
        return resultList;
    }
}
