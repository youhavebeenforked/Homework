package ru.sberbank.homework.kashin.task_03.storage;

import ru.sberbank.homework.kashin.task_03.proxy.Args;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements Storage {
    private static final Map<Args, Object> cache = new HashMap<>();

    @Override
    public boolean containsKey(Args key) {
        return cache.containsKey(key);
    }

    @Override
    public Object get(Args key) {
        return cache.get(key);
    }

    @Override
    public Object put(Args key, Object value) {
        return cache.put(key, value);
    }
}
