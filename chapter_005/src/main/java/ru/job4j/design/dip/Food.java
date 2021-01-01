package ru.job4j.design.dip;

import java.time.LocalDate;

public abstract class Food {
    private String name;
    private LocalDate expiredDate;
    private LocalDate createDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate expiredDate, LocalDate createDate, double price, int discount) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
