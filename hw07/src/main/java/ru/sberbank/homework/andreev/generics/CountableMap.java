package ru.sberbank.homework.andreev.generics;

import java.util.HashMap;
import java.util.Map;

import java.util.stream.IntStream;

public class CountableMap<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T e) {
        map.compute(e, (key, value) -> (value == null) ? 1 : value + 1);
    }

    @Override
    public int getCount(T e) {
        return map.getOrDefault(e,0);
    }

    @Override
    public int remove(T e) {
        return map.remove(e);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        source.toMap().forEach((key, value) -> IntStream.rangeClosed(1, value).forEach(f -> this.add(key)));
    }

    @Override
    public Map<T, Integer> toMap() {
        Map<T, Integer> result = new HashMap<>();
        toMap(result);
        return result;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {
        destination.putAll(map);
    }
}
