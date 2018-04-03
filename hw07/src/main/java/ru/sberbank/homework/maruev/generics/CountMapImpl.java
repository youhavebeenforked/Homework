package ru.sberbank.homework.maruev.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Иван on 12.03.2018.
 */
public class CountMapImpl<T> implements CountMap<T> {

    private Map<T, Integer> map = new HashMap<>();

    @Override
    public void add(T key) {
        map.merge(key, 1, (oldValue, value) -> oldValue + value);
    }

    @Override
    public int getCount(T key) {
        return map.get(key);
    }

    @Override
    public int remove(T key) {
        return map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        for(Map.Entry<? extends T, Integer> entry : source.toMap().entrySet()) {
            map.merge(entry.getKey(), entry.getValue(), (oldValue, value) -> oldValue + value);
        }
    }

    @Override
    public Map toMap() {
        return map;
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        destination.putAll(map);
    }
}
