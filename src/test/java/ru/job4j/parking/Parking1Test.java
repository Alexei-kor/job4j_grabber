package ru.job4j.parking;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Parking1Test {

    @Test
    public void when2x1And2Car1TruckThenOK() {
        Parking parking = new Parking1(2, 1);
        List<Autos> list = List.of(
                new Cars("1"),
                new Cars("2"),
                new Trucks(2, "3")
        );
        assertTrue(true);
    }

    @Test
    public void when2x1And2TruckThenOK() {
        Parking parking = new Parking1(2, 1);
        List<Autos> list = List.of(
                new Trucks(2, "3"),
                new Trucks(7, "3")
        );
        assertTrue(true);
    }

    @Test
    public void when2x1And1Car2TruckThenNoOK() {
        Parking parking = new Parking1(2, 1);
        List<Autos> list = List.of(
                new Cars("3"),
                new Trucks(2, "3"),
                new Trucks(2, "3")
        );
        assertFalse(parking.add(list));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenSizeTruckIs1ThenErr() {
        List<Autos> list = List.of(
                new Cars("1"),
                new Cars("2"),
                new Trucks(1, "3")
        );
    }

}