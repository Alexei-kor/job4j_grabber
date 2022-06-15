package ru.job4j.products;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Food {

    private String Name;
    private LocalDateTime expiryDate;
    private LocalDateTime createDate;
    private float price;
    private float discount;

    public Food(String name, LocalDateTime expiryDate, LocalDateTime createDate,
                float price, float discount) {
        Name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getDiscount() {
        return discount;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
