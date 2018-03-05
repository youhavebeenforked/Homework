package ru.sberbank.homework.bobrov.generics;

import javafx.collections.transformation.SortedList;

import java.util.*;

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
        boolean result = false;
        for (T value : c1) {
            if (c2.contains(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List range(List<? extends Comparable<T>> list, T min, T max) {
        List result = new ArrayList<>();
        for (Comparable<T> value : list) {
            if (value.compareTo(max) <= 0 && value.compareTo(min) >= 0) {
                result.add(value);
            }
        }
        Collections.sort(result);
        return result;
    }

    public static <T> List range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
        List result = new ArrayList();
        for (T value : list) {
            if (comparator.compare(value, max) <= 0 && comparator.compare(value, min) >= 0) {
                result.add(value);
            }
        }
        Collections.sort(result);
        return result;
    }

}
