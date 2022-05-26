package ru.job4j.kiss;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        value.sort(comparator);
        return getFirst(value);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Collections.sort(value, comparator.reversed());
        return getFirst(value);
    }

    private <T> T getFirst(List<T> value) {
        return value.isEmpty() ? null : value.get(0);
    }

    public static void main(String[] args) {
        MaxMin maxMin = new MaxMin();
        System.out.println(maxMin.max(Arrays.asList(4, 5, 2, 6, 77, 23), Comparator.naturalOrder()));
        System.out.println(maxMin.max(Arrays.asList(4, 5, 2, 6, 77, 23), Comparator.reverseOrder()));

        System.out.println(maxMin.min(Arrays.asList(4, 5, 2, 6, 77, 23), Comparator.naturalOrder()));
        System.out.println(maxMin.min(Arrays.asList(4, 5, 2, 6, 77, 23), Comparator.reverseOrder()));
    }

}
