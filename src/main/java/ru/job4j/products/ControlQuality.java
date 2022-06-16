package ru.job4j.products;

import java.util.List;

public class ControlQuality {

    public void distribute(Food food, List<Store> stores) {
        stores.forEach(store -> store.add(food));
    }

}
