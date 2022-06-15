package ru.job4j.products;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
public class ControlQualityTest {

    @Test
    public void whenPercentLess25ThenWarehouse() {
        Food food = new Food(
                "Колбаса",
                LocalDateTime.of(2022, 7, 30, 0, 0),
                LocalDateTime.of(2022, 6, 1, 0, 0),
                300,
                0);
        ControlQuality control = new ControlQuality();
        Store store = control.distribute(food);
        assertThat(store.getClass(), is(Warehouse.class));
    }

    @Test
    public void whenPercentMore25Less75ThenShop() {
        Food food = new Food(
                "Колбаса",
                LocalDateTime.of(2022, 8, 30, 0, 0),
                LocalDateTime.of(2022, 4, 1, 0, 0),
                300,
                0);
        ControlQuality control = new ControlQuality();
        Store store = control.distribute(food);
        assertThat(store.getClass(), is(Shop.class));
    }

    @Test
    public void whenPercentMore75ThenShop() {
        Food food = new Food(
                "Колбаса",
                LocalDateTime.of(2022, 6, 30, 0, 0),
                LocalDateTime.of(2022, 4, 1, 0, 0),
                300,
                0);
        ControlQuality control = new ControlQuality();
        Store store = control.distribute(food);
        assertTrue(food.getDiscount() != 0);
    }

    @Test
    public void whenPercentMore100ThenTrash() {
        Food food = new Food(
                "Колбаса",
                LocalDateTime.of(2022, 6, 12, 0, 0),
                LocalDateTime.of(2022, 4, 1, 0, 0),
                300,
                0);
        ControlQuality control = new ControlQuality();
        Store store = control.distribute(food);
        assertThat(store.getClass(), is(Trash.class));
    }

}