package ru.job4j.kiss;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getFirst(value, (a, b) -> comparator.compare(a, b) < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getFirst(value, (a, b) -> comparator.reversed().compare(a, b) < 0);
    }

    private <T> T getFirst(List<T> value, BiPredicate<T, T> biPredicate) {
        T rsl = value.isEmpty() ? null : value.get(0);
        for (T tmp : value) {
            if (biPredicate.test(rsl, tmp)) {
                rsl = tmp;
            }
        }
        return rsl;
    }

}
