package ru.job4j.design.lsp;

import java.util.Objects;

public class Car implements Vehicle {
    private final String model;
    private final String regNumber;
    private final int parkingUnits;

    public Car(String model, String regNumber) {
        this.model = model;
        this.regNumber = regNumber;
        this.parkingUnits = 1;
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
        Car car = (Car) o;
        return Objects.equals(regNumber, car.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber);
    }
}