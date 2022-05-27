package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
public class MaxMinTest {

    @Test
    public void whenMaxInt() {
        MaxMin maxMin = new MaxMin();
        int actual = maxMin.max(Arrays.asList(4, 5, 2, 6, 77, 23), Comparator.naturalOrder());
        assertThat(actual, is(77));
    }

    @Test
    public void whenMinInt() {
        MaxMin maxMin = new MaxMin();
        int actual = maxMin.min(Arrays.asList(4, 5, 2, 6, 77, 23), Comparator.naturalOrder());
        assertThat(actual, is(2));
    }

    @Test
    public void whenMaxChar() {
        MaxMin maxMin = new MaxMin();
        char actual = maxMin.max(Arrays.asList('A', 'B', 'C', 'D'), Comparator.naturalOrder());
        assertThat(actual, is('D'));
    }

    @Test
    public void whenMinChar() {
        MaxMin maxMin = new MaxMin();
        char actual = maxMin.min(Arrays.asList('A', 'B', 'C', 'D'), Comparator.naturalOrder());
        assertThat(actual, is('A'));
    }

}