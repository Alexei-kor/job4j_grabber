package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

public class Garage implements Parking {

    private int sizeStoreCar;
    private int sizeStoreTruck;
    private int freeStoreCar;
    private int freeStoreTruck;
    private List<Auto> storeCars;
    private List<Auto> storeTrucks;

    public Garage(int sizeStoreCar, int sizeStoreTruck) {
        this.sizeStoreCar = sizeStoreCar;
        this.sizeStoreTruck = sizeStoreTruck;
        storeCars = new ArrayList<>(sizeStoreCar);
        storeTrucks = new ArrayList<>(sizeStoreTruck);
        freeStoreCar = sizeStoreCar;
        freeStoreTruck = sizeStoreTruck;
    }

    @Override
    public boolean add(Auto auto) {
        boolean rsl = false;
        if (auto.getSize() == Cars.SIZE) {
            if (freeStoreCar >= Cars.SIZE) {
                storeCars.add(auto);
                freeStoreCar--;
                rsl = true;
            }
        } else if (freeStoreTruck > 0) {
            storeTrucks.add(auto);
            freeStoreTruck -= auto.getSize();
            rsl = true;
        } else if (freeStoreCar >= auto.getSize()) {
            storeCars.add(auto);
            freeStoreCar -= auto.getSize();
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(Auto auto) {
        return true;
    }

}
