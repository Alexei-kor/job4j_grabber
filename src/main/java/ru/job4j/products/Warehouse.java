package ru.job4j.products;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> foods = new ArrayList<>();

    private String name;

    public Warehouse(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }

    @Override
    public boolean add(Food food) {
        return accept(food) ? foods.add(food) : false;
    }

    @Override
    public boolean accept(Food food) {
        return getPercentLifeExpired(food) < Constants.LIMIT_25;
    }

}
