package ru.job4j.design.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return compareListElements(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return compareListElements(value, comparator.reversed());
    }

    private <T> T compareListElements(List<T> list, Comparator<T> comparator) {
        T extremum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(extremum, list.get(i)) < 0) {
                extremum = list.get(i);
            }
        }
        return extremum;
    }
}