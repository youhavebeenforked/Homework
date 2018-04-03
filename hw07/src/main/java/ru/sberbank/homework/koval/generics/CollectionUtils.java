package ru.sberbank.homework.koval.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Параметризовать методы, используя правило PECS, и реализовать их.
 */
public class CollectionUtils {
    public static <E> void addAll(List<? extends E> source, List<? super E> destination) {
        destination.addAll(source);
    }

    public static <E> List<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E> int indexOf(List<? super E> source, E o) {
        return source.indexOf(o);
    }

    public static <E> List<E> limit(List<? extends E> source, int size) {
        return new ArrayList<>(source.subList(0, size));
    }

    public static <E> void add(List<? super E> destination, E o) {
        destination.add(o);
    }

    public static <E> void removeAll(List<? super E> removeFrom, List<? extends E> toRemove) {
        removeFrom.removeAll(toRemove);
    }

    public static <E> boolean containsAll(List<? super E> c1, List<? extends E> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <E> boolean containsAny(List<? super E> c1, List<? extends E> c2) {
        for (E elem : c2) {
            if (c1.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <E extends Comparable<E>> List<E> range(List<? extends E> list, E min, E max) {
        return range(list, min, max, Comparator.<E>naturalOrder());
    }

    public static <E extends Comparable<E>> List<E> range(List<? extends E> list, E min, E max, Comparator<E> comparator) {
        List<E> result = new ArrayList<>();
        for (E elem : list) {
            if (comparator.compare(min, elem) <= 0 &&
                    comparator.compare(max, elem) >= 0) {
                result.add(elem);
            }
        }
        return result;
    }

}
