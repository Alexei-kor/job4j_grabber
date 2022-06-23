package ru.job4j.parking;

public class Cars implements Autos {

    public final static int SIZE = 1;
    private String number;

    public Cars(String number) {
        this.number = number;
    }
}
