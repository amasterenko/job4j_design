package ru.job4j.design.srp;

public class RubToUsdConverter implements Converter {
    private double rate;

    public RubToUsdConverter(double rate) {
        if (rate == 0 || rate < 0) {
            throw new IllegalArgumentException("The rate must be grater than 0");
        }
        this.rate = rate;
    }

    @Override
    public double convert(double amount) {
        return amount / rate;
    }
}
