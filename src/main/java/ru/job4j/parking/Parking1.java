package ru.job4j.parking;

public class Parking1 implements Parking {

    private int height;
    private int width;
    private int free;
    private int[][] store;

    public Parking1(int height, int width) {
        this.height = height;
        this.width = width;
        store = new int[width][height];
        free = height * width;
    }

    public int getFree() {
        return free;
    }

    @Override
    public boolean add(Autos autos) {
        return isSpace(autos);
    }

    @Override
    public void delete(Autos autos) {

    }

    private boolean isSpace(Autos autos) {
        return false;
    }

}
