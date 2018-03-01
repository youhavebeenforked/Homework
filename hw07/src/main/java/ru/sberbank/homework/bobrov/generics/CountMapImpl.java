package ru.sberbank.homework.bobrov.generics;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 01.03.2018
 */


public class CountMapImpl<K> implements CountMap<K> {
    private Map<K, Integer> store = new HashMap<>();

    @Override
    public void add(K key) {
        if (store.containsKey(key)) {
            store.put(key, store.get(key) + 1);
        } else {
            store.put(key, 1);
        }
    }

    @Override
    public int getCount(K key) {
        return store.get(key);
    }

    @Override
    public int remove(K key) {
        return store.remove(key);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public void addAll(CountMap<? extends K> source) {
        Map<? extends K, Integer> tmp = source.toMap();
        for (K key : tmp.keySet()) {
            this.add(key);
        }
    }

    @Override
    public Map toMap() {
        return store;
    }

    @Override
    public void toMap(Map destination) {
        destination = store;

    }
}
