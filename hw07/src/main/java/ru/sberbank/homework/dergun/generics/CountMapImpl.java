package ru.sberbank.homework.dergun.generics;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<E> implements CountMap<E> {
    private Map<E, Integer> map = new HashMap<>();

    @Override
    public void add(E e) {
        map.put(e, map.getOrDefault(e, 0) + 1);
    }

    @Override
    public int getCount(E e) {
        return map.getOrDefault(e, 0);
    }

    @Override
    public int remove(Object o) {
        return map.containsKey(o) ? map.remove(o) : 0;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends E> source) {
        map.putAll(source.toMap());
    }

    @Override
    public Map<? extends E, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<? super E, Integer> destination) {
        destination.clear();
        destination.putAll(map);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
