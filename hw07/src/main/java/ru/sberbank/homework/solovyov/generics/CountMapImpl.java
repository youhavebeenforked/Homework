package ru.sberbank.homework.solovyov.generics;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<K> implements CountMap<K> {

    Map<K, Integer> dataMap = new HashMap<>();

    @Override
    public void add(K k) {
        dataMap.merge(k, 1, (value, newValue) -> value + newValue);
    }

    @Override
    public int getCount(K k) {
        return dataMap.get(k);
    }

    @Override
    public int remove(K k) {
        return dataMap.remove(k);
    }

    @Override
    public int size() {
        return dataMap.size();
    }

    @Override
    public void addAll(CountMap<? extends K> source) {
        for (Map.Entry<? extends K, Integer> entry : source.toMap().entrySet()) {
            dataMap.merge(entry.getKey(), entry.getValue(), (value, newValue) -> value + newValue);
        }
    }

    @Override
    public Map<K, Integer> toMap() {
        return dataMap;
    }

    @Override
    public void toMap(Map<? super K, ? super Integer> destination) {
        for (Map.Entry<K, Integer> entry : dataMap.entrySet()) {
            destination.put(entry.getKey(), entry.getValue());
        }
    }
}
