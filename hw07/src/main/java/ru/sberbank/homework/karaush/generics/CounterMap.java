package ru.sberbank.homework.karaush.generics;

import ru.sberbank.homework.karaush.generics.CountMap;

import java.util.HashMap;
import java.util.Map;

public class CounterMap<T> implements CountMap<T> {

    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T t) {
        //put 1 if not present, otherwise sum with present value
        map.merge(t, 1, (value, newValue) -> value + newValue);
    }

    @Override
    public int getCount(T t) {
        return map.get(t);
    }

    @Override
    public int remove(T t) {
        return map.remove(t);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        for (Map.Entry<T, Integer> entry : source.toMap().entrySet()) {
            map.merge(entry.getKey(), entry.getValue(), (value, newValue) -> value + newValue);
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<? super T, ? super Integer> destination) {
        destination.putAll(map);
    }
}
