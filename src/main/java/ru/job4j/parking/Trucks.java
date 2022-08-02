package ru.job4j.parking;

public class Trucks implements Auto {

    private int size;
    private String number;

    public Trucks(int size, String number) {
        checkSize(size);
        this.size = size;
        this.number = number;
    }

    @Override
    public int getSize() {
        return size;
    }

    private void checkSize(int size) {
        if (size <= Cars.SIZE) {
            throw new IllegalArgumentException("The size of the truck must be greater than 1");
        }
    }

}
