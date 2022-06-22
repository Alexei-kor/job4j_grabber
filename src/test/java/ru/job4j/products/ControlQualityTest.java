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
        new ControlQuality(List.of(trash, shop, warehouse)).distribute(food);
        List<Food> foods = shop.getFoods();
        foods.add(food);
        List<Food> foods1 = shop.getFoods();
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
        new ControlQuality(List.of(trash, shop, warehouse)).distribute(food);
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
        new ControlQuality(List.of(trash, shop, warehouse)).distribute(food);
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
        new ControlQuality(List.of(trash, shop, warehouse)).distribute(food);
        assertThat(trash.getFoods().size(), is(1));
    }

    @Test
    public void distributeFourProduct() {

        Food foodTrash = new Food(
                "Колбаса в мусорку",
                now.minusDays(1),
                now.minusDays(10),
                300,
                0);

        Food foodWarehouse = new Food(
                "Колбаса на склад",
                now.plusDays(50),
                now.minusDays(10),
                300,
                0);

        Food foodShop = new Food(
                "Колбаса в магазин",
                now.plusDays(29),
                now.minusDays(10),
                300,
                0);

        Food foodShopDisc = new Food(
                "Колбаса в магазин со скидкой",
                now.plusDays(10),
                now.minusDays(31),
                300,
                10);
        List<Food> list = List.of(foodWarehouse, foodTrash, foodShop, foodShopDisc);
        Trash trash = new Trash("Мусорка");
        Shop shop = new Shop("Магазин на Горького");
        Warehouse warehouse = new Warehouse("Склад 1");
        for (Food food: list) {
            new ControlQuality(List.of(trash, shop, warehouse)).distribute(food);
        }
        assertThat(trash.getFoods(), is(List.of(foodTrash)));
        assertThat(warehouse.getFoods(), is(List.of(foodWarehouse)));
        assertThat(shop.getFoods(), is(List.of(foodShop, foodShopDisc)));
    }

}