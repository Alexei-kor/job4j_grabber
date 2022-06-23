package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

public class Parking1 implements Parking {

    private int sizeStoreCar;
    private int sizeStoreTruck;
    private int freeStoreCar;
    private int freeStoreTruck;
    private List<Autos> storeCars;
    private List<Autos> storeTrucks;

    public Parking1(int sizeStoreCar, int sizeStoreTruck) {
        this.sizeStoreCar = sizeStoreCar;
        this.sizeStoreTruck = sizeStoreTruck;
        storeCars = new ArrayList<>(sizeStoreCar);
        storeTrucks = new ArrayList<>(sizeStoreTruck);
        freeStoreCar = sizeStoreCar;
        freeStoreTruck = sizeStoreTruck;
    }

    @Override
    public boolean add(List<Autos> autos) {
        boolean rsl = false;
        if (isSpace(autos)) {
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(Autos autos) {
        return true;
    }

    private boolean isSpace(List<Autos> autos) {
        return false;
    }

}
