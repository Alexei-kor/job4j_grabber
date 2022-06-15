package ru.job4j.products;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ControlQuality {

    public Store distribute(Food food) {
        Store rsl = null;
        LocalDateTime now = LocalDateTime.now();
        float percent = Math.round(
                (float) Duration.between(food.getCreateDate(), now).toDays() /
                        (float) Duration.between(food.getCreateDate(), food.getExpiryDate()).toDays()
                        * 100);
        if (percent < 25) {
            rsl = new Warehouse();
        } else if (percent >= 25 && percent <= 75) {
            rsl = new Shop();
        } else if (percent > 75 && percent < 100) {
            rsl = new Shop();
            food.setDiscount(50);
        } else {
            rsl = new Trash();
        }
        return rsl;
    }

}
