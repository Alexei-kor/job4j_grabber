package ru.job4j.products;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private List<Food> foods = new ArrayList<>();

    private String name;

    public Trash(String name) {
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public boolean add(Food food) {
        return accept(food) ? foods.add(food) : false;
    }

    @Override
    public boolean accept(Food food) {
        return getPercentLifeExpired(food, LocalDateTime.now()) > 100;
    }

}
