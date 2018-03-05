package ru.sberbank.homework.koval.generics;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {
    /**
     * добавляет элемент в этот контейнер.
     */
    private Map<E, Integer> storage;

    public CountMapImpl() {
        storage = new HashMap<>();
    }

    private static int transformInteger(Integer number) {
        return (number == null) ? 0 : number;
    }

    private void add(E o, int count) {
        storage.put(o, getCount(o) + count);
    }

    /**
     * добавляет элемент в этот контейнер.
     */
    public void add(E o) {
        add(o, 1);
    }

    /**
     * Возвращает количество добавлений данного элемента
     */
    public int getCount(E o) {
        return transformInteger(storage.get(o));
    }

    /**
     * Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
     */
    public int remove(E o) {
        return transformInteger(storage.remove(o));
    }

    /**
     * количество разных элементов
     */
    public int size() {
        return storage.size();
    }

    /**
     * Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
     */
    public void addAll(CountMap<? extends E> source) {
        Map<? extends E, Integer> sourceMap = source.toMap();
        for (Map.Entry<? extends E, Integer> entry : sourceMap.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
     */
    public Map<E, Integer> toMap() {
        return new HashMap<>(storage);
    }

    /**
     * Тот же самый контракт как и toMap(), только всю информацию записать в destination
     */
    public void toMap(Map<E, Integer> destination) {
        destination = toMap();
    }
}
