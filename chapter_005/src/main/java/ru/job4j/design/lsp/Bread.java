package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Bread extends Food {
    public Bread(String name, LocalDate expiredDate, LocalDate createDate, double price, int discount) {
        super(name, expiredDate, createDate, price, discount);
    }
}
