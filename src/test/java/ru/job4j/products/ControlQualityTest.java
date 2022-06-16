package ru.job4j.products;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
public class ControlQualityTest {

    public LocalDateTime now = LocalDateTime.now();

    @Test
    public void whenPercentLess25ThenWarehouse() {
        Food food = new Food(
                "Колбаса",
                now.plusDays(50),
                now.minusDays(10),
                300,
                0);
        Trash trash = new Trash("Мусорка");
        Shop shop = new Shop("Магазин на Горького");
        Warehouse warehouse = new Warehouse("Склад 1");
        new ControlQuality().distribute(food, List.of(trash, shop, warehouse));
        assertThat(warehouse.getFoods().size(), is(1));
    }

    @Test
    public void whenPercentMore25Less75ThenShop() {
        Food food = new Food(
                "Колбаса",
                now.plusDays(29),
                now.minusDays(10),
                300,
                0);
        Trash trash = new Trash("Мусорка");
        Shop shop = new Shop("Магазин на Горького");
        Warehouse warehouse = new Warehouse("Склад 1");
        new ControlQuality().distribute(food, List.of(trash, shop, warehouse));
        assertThat(shop.getFoods().size(), is(1));
    }

    @Test
    public void whenPercentMore75ThenShop() {
        Food food = new Food(
                "Колбаса",
                now.plusDays(10),
                now.minusDays(31),
                300,
                10);
        Trash trash = new Trash("Мусорка");
        Shop shop = new Shop("Магазин на Горького");
        Warehouse warehouse = new Warehouse("Склад 1");
        new ControlQuality().distribute(food, List.of(trash, shop, warehouse));
        assertThat(food.getPrice(), is(290d));
    }

    @Test
    public void whenPercentMore100ThenTrash() {
        Food food = new Food(
                "Колбаса",
                now.minusDays(1),
                now.minusDays(10),
                300,
                0);
        Trash trash = new Trash("Мусорка");
        Shop shop = new Shop("Магазин на Горького");
        Warehouse warehouse = new Warehouse("Склад 1");
        new ControlQuality().distribute(food, List.of(trash, shop, warehouse));
        assertThat(trash.getFoods().size(), is(1));
    }

}