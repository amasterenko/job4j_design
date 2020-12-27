package ru.job4j.design.lsp;

import java.util.Objects;

public class Truck implements Vehicle {
    private String model;
    private String regNumber;
    private int parkingUnits;

    public Truck(String model, String regNumber, int parkingUnits) {
        if (parkingUnits < 2) {
            throw new IllegalArgumentException("Parking units must be grater than or equal 2!");
        }
        this.model = model;
        this.regNumber = regNumber;
        this.parkingUnits = parkingUnits;
    }

    @Override
    public int needsParkingUnits() {
        return parkingUnits;
    }

    @Override
    public String getRegNumber() {
        return regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return Objects.equals(regNumber, truck.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber);
    }
}
