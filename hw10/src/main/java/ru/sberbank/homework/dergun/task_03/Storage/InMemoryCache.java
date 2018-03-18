package ru.sberbank.homework.dergun.task_03.Storage;


import ru.sberbank.homework.dergun.task_03.CacheKey;
import ru.sberbank.homework.dergun.task_03.CacheSettings;

import java.util.HashMap;

public class InMemoryCache implements CacheStorage {
    private final HashMap<CacheKey, Object> cacheMap = new HashMap<>();
    private final String monitor = "";

    @Override
    public void addObject(CacheKey key, Object object) {
        synchronized (monitor) {
            cacheMap.put(key, object);
        }
    }

    @Override
    public boolean contains(CacheKey key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public void changeSetting(CacheSettings settings) {
    }

    @Override
    public Object getByKey(CacheKey key) {
        synchronized (monitor) {
            return cacheMap.get(key);
        }
    }
}
