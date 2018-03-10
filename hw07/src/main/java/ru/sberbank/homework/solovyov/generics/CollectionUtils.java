package ru.sberbank.homework.solovyov.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.naturalOrder;

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

    public static <T> List<T> limit(List<T> source, int size) {
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
        for (T t2 : c2) {
            if (c1.contains(t2)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T extends Comparable<T>> List<T> range(List<T> list, T min, T max) {
        return range(list, min, max, naturalOrder());
    }

    public static <T extends Comparable<T>> List<T> range(List<T> list, T min, T max,
                                                          Comparator<? super T> comparator) {
        List<T> resultList = new ArrayList<>();
        for (T t : list) {
            if (comparator.compare(t, min) >= 0 && comparator.compare(t, max) <= 0) {
                resultList.add(t);
            }
        }
        resultList.sort(comparator);
        return resultList;
    }

}
