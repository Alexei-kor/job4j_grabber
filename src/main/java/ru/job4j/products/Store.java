package ru.job4j.products;

import java.time.Duration;
import java.time.LocalDateTime;

public interface Store {

    default int getPercentLifeExpired(Food food) {
        return (int) Math.round(
                (double) Duration.between(food.getCreateDate(), LocalDateTime.now()).toDays()
                        / (double) Duration.between(food.getCreateDate(), food.getExpiryDate()).toDays()
                        * 100);
    }

    boolean add(Food food);

    boolean accept(Food food);

}
