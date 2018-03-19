package ru.sberbank.homework.maruev.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Параметризовать методы, используя правило PECS, и реализовать их.
 */
public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List limit(List<? extends T> source, int size) {
        return source.subList(0, size);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T elem : c2) {
            if (c1.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List range(List<? extends T> list, Comparable<T> min, Comparable<T> max) {
        return list.stream()
                .filter(elem -> min.compareTo(elem) >= 0)
                .filter(elem -> max.compareTo(elem) <= 0)
                .sorted()
                .collect(Collectors.toList());
    }

    public static <T> List range(List<? extends T> list, Object min, Object max, Comparator comparator) {

        return list.stream()
                .filter(elem -> comparator.compare(elem, min) >= 0)
                .filter(x -> comparator.compare(x, max) <= 0)
                .sorted()
                .collect(Collectors.toList());
    }
}
