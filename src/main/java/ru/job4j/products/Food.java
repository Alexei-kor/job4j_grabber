package ru.job4j.products;

import java.time.LocalDateTime;

public class Food {

    private String name;
    private LocalDateTime expiryDate;
    private LocalDateTime createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDateTime expiryDate, LocalDateTime createDate,
                float price, float discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
