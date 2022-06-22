package ru.job4j.products;

import java.util.List;

public class ControlQuality {

    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        stores.forEach(store -> {
            if (store.accept(food)) {
                store.add(food);
            }
        });
    }

}
