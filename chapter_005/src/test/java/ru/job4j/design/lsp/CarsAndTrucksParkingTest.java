package ru.job4j.design.lsp;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CarsAndTrucksParkingTest {
    @Ignore
    @Test
    public void whenNoVehiclesThenParkedListIsEmpty() {
        Parking parking = new CarsAndTrucksParking(10, 10);
        List<Vehicle> expected = Collections.emptyList();
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void whenNoSpacesForParkingCarsAndTrucks() {
        Parking parking = new CarsAndTrucksParking(0, 0);
        Vehicle car = new Car("CModel", "1");
        Vehicle truck = new Truck("TModel", "2", 2);
        List<Vehicle> expected = Collections.emptyList();
        assertThat(parking.park(car), is(false));
        assertThat(parking.park(truck), is(false));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void when1Car1TruckParkAndUnpark() {
        Parking parking = new CarsAndTrucksParking(1, 1);
        Vehicle car = new Car("CModel", "1");
        Vehicle truck = new Truck("TModel", "2", 2);
        List<Vehicle> expected = Collections.emptyList();
        assertThat(parking.park(car), is(true));
        assertThat(parking.park(truck), is(true));
        assertThat(parking.unPark(car), is(true));
        assertThat(parking.unPark(truck), is(true));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void whenAllVehiclesParkedInTheirSpaces() {
        Parking parking = new CarsAndTrucksParking(3, 2);
        Vehicle car1 = new Car("CModel", "1");
        Vehicle car2 = new Car("CModel", "2");
        Vehicle car3 = new Car("CModel", "3");
        Vehicle truck1 = new Truck("TModel", "4", 2);
        Vehicle truck2 = new Truck("TModel", "5", 2);
        List<Vehicle> expected = List.of(car1, car2, car3, truck1, truck2);
        assertThat(parking.park(car1), is(true));
        assertThat(parking.park(car2), is(true));
        assertThat(parking.park(car3), is(true));
        assertThat(parking.park(truck1), is(true));
        assertThat(parking.park(truck2), is(true));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void when0SpaceForCars1SpaceForTrucksThenCarisNotParkedTruckIsParked() {
        Parking parking = new CarsAndTrucksParking(0, 1);
        Vehicle car = new Car("CModel", "1");
        Vehicle truck = new Truck("TModel", "2", 2);
        List<Vehicle> expected = List.of(truck);
        assertThat(parking.park(car), is(false));
        assertThat(parking.park(truck), is(true));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void when0SpaceForTrucksAnd1SpaceForCarsThenCarIsParkedTruckIsNotParked() {
        Parking parking = new CarsAndTrucksParking(1, 0);
        Vehicle car = new Car("CModel", "1");
        Vehicle truck = new Truck("TModel", "2", 2);
        List<Vehicle> expected = List.of(car);
        assertThat(parking.park(car), is(true));
        assertThat(parking.park(truck), is(false));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void when0SpaceForTrucks3SpacesForCarsThen1CarAnd1TruckAreParked() {
        Parking parking = new CarsAndTrucksParking(3, 0);
        Vehicle car = new Car("CModel", "1");
        Vehicle truck = new Truck("TModel", "2", 2);
        List<Vehicle> expected = List.of(car, truck);
        parking.park(car);
        assertThat(parking.park(truck), is(true));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void when1SpaceForTrucks2SpacesForCarsThen2TrucksAreParked() {
        Parking parking = new CarsAndTrucksParking(2, 1);
        Vehicle truck1 = new Truck("TModel", "1", 2);
        Vehicle truck2 = new Truck("TModel", "2", 2);
        List<Vehicle> expected = List.of(truck1, truck2);
        parking.park(truck1);
        assertThat(parking.park(truck2), is(true));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void whenNotEnoughSpaceForTruckThenTruckIsNotParked() {
        Parking parking = new CarsAndTrucksParking(2, 0);
        Vehicle truck = new Truck("TModel", "1", 3);
        List<Vehicle> expected = List.of();
        assertThat(parking.park(truck), is(false));
        assertThat(parking.getParkedVehicles(), is(expected));
    }

    @Ignore
    @Test
    public void when2NotConsecutiveSpacesForCarsThenTruckIsNotParked() {
        Parking parking = new CarsAndTrucksParking(2, 0);
        Vehicle car1 = new Car("CModel", "1");
        Vehicle car2 = new Car("CModel", "2");
        Vehicle truck = new Truck("TModel", "3", 2);
        List<Vehicle> expected = List.of(car2);
        parking.park(car1);
        parking.park(car2);
        parking.unPark(car1);
        assertThat(parking.park(truck), is(false));
        assertThat(parking.getParkedVehicles(), is(expected));
    }
}