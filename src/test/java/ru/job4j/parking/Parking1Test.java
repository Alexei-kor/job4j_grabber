package ru.job4j.parking;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Parking1Test {

    @Test
    public void when2x1And2Car1TruckThenOK() {
        Parking parking = new Garage(2, 1);
        assertTrue(parking.add(new Cars("1")));
        assertTrue(parking.add(new Cars("2")));
        assertTrue(parking.add(new Trucks(2, "3")));
    }

    @Test
    public void when2x1And2TruckThenOK() {
        Parking parking = new Garage(2, 1);
        assertTrue(parking.add(new Trucks(7, "34")));
        assertTrue(parking.add(new Trucks(2, "56")));
    }

    @Test
    public void when2x1And1Car2TruckThenNoOK() {
        Parking parking = new Garage(2, 1);
        assertTrue(parking.add(new Cars("3")));
        assertTrue(parking.add(new Trucks(2, "3")));
        assertFalse(parking.add(new Trucks(2, "36")));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenSizeTruckIs1ThenErr() {
        new Trucks(1, "3");
    }

}