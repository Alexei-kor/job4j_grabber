package ru.job4j.parking;

public class Cars implements Autos {

    private int size;
    private String number;

    public Cars(int size, String number) {
        this.size = size;
        this.number = number;
    }
}
