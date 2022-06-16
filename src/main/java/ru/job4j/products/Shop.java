package ru.job4j.products;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private List<Food> foods = new ArrayList<>();
    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            foods.add(food);
            rsl = true;
        }
        if (getPercentLifeExpired(food, LocalDateTime.now()) > 75) {
            food.setPrice(food.getPrice() - food.getDiscount());
        }
        return rsl;
    }

    @Override
    public boolean accept(Food food) {
        int percent = getPercentLifeExpired(food, LocalDateTime.now());
        return percent >= 25 && percent <= 100;
    }
}
