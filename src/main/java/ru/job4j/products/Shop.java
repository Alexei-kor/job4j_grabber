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
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (accept(food)) {
            foods.add(food);
            rsl = true;
        }
        if (getPercentLifeExpired(food) > Constants.LIMIT_75) {
            food.setPrice(food.getPrice() - food.getDiscount());
        }
        return rsl;
    }

    @Override
    public boolean accept(Food food) {
        int percent = getPercentLifeExpired(food);
        return percent >= Constants.LIMIT_25 && percent <= Constants.LIMIT_100;
    }
}
