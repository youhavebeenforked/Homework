package ru.sberbank.homework.kashin.task_03.storage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements Storage {
    private final Map<String, Object> cache = new HashMap<>();

    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return cache.put(key, value);
    }
}
