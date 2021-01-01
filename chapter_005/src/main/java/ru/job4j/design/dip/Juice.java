package ru.job4j.design.dip;

import java.time.LocalDate;

public class Juice extends Food {
    public Juice(String name, LocalDate expiredDate, LocalDate createDate, double price, int discount) {
        super(name, expiredDate, createDate, price, discount);
    }
}
