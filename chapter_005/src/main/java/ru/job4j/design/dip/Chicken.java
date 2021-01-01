package ru.job4j.design.dip;

import java.time.LocalDate;

public class Chicken extends Food {
    public Chicken(String name, LocalDate expiredDate, LocalDate createDate, double price, int discount) {
        super(name, expiredDate, createDate, price, discount);
    }
}
