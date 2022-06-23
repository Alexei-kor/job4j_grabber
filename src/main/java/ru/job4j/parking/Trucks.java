package ru.job4j.parking;

public class Trucks implements Autos {

    private int size;
    private String number;

    public Trucks(int size, String number) {
        checkSize(size);
        this.size = size;
        this.number = number;
    }

    private void checkSize(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("The size of the truck must be greater than 1");
        }
    }

}
