package ru.sberbank.homework.andreev.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Параметризовать методы, используя правило PECS, и реализовать их.
 */
public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        return new ArrayList<>(source.subList(0, size));
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 элемент второго
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        return c2.stream().anyMatch(c1::contains);
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List range(List<? extends T> list, Comparable<T> min, Comparable<T> max) {
        return list.stream()
                .filter(e -> ((min.compareTo(e) <= 0) && (max.compareTo(e) >= 0)))
                .collect(Collectors.toList());
    }

    public static <T> List range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        return list.stream()
                .filter(e -> ((comparator.compare(min, e) <= 0) && (comparator.compare(max, e) >= 0)))
                .collect(Collectors.toList());
    }

}
