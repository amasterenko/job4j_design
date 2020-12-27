package ru.job4j.design.lsp;

public class Space {
    private Vehicle vehicle;

    public boolean take(Vehicle vehicle) {
        boolean rsl = false;
        if (isEmpty()) {
            this.vehicle = vehicle;
            rsl = true;
        }
        return rsl;
    }

    public void release() {
        this.vehicle = null;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    public Vehicle getParkedVehicle() {
        return vehicle;
    }
}
