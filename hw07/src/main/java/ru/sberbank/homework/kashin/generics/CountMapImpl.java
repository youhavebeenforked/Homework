package ru.sberbank.homework.kashin.generics;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class CountMapImpl<K> implements CountMap<K> {
    Map<K, Integer> data = new HashMap<>();

    @Override
    public void add(K k) {
        if (isNull(data.get(k))) {
            data.put(k, 1);
        } else {
            data.put(k, data.get(k) + 1);
        }
    }

    @Override
    public int getCount(K k) {
        return data.get(k);
    }

    @Override
    public int remove(K k) {
        return data.remove(k);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void addAll(CountMap<? extends K> source) {
        for (Map.Entry<? extends K,Integer> entry : source.toMap().entrySet()) {
            if (isNull(data.get(entry.getKey()))) {
                data.put(entry.getKey(), entry.getValue());
            } else {
                data.put(entry.getKey(), data.get(entry.getKey()) + entry.getValue());
            }
        }
    }

    @Override
    public Map<K, Integer> toMap() {
        return data;
    }

    @Override
    public void toMap(Map<? super K, ? super Integer> destination) {
        for (Map.Entry<K,Integer> entry : data.entrySet()) {
            destination.put(entry.getKey(), entry.getValue());
        }
    }
}
