package ru.sberbank.homework.maruev.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Иван on 12.03.2018.
 */
public class CountMapImpl<T> implements CountMap<T> {

    private Map<T, Integer> map = new HashMap<>();
    private int value = 0;

    @Override
    public void add(T key) {
        map.put(key, value++);
    }

    @Override
    public int getCount(T key) {
        int count = 0;
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals(key)) {
                count++;
            }
        }
        return count;
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
        map.putAll((Map<? extends T, Integer>) source);
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
