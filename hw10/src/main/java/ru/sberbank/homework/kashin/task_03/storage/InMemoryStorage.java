package ru.sberbank.homework.kashin.task_03.storage;

import ru.sberbank.homework.kashin.task_03.proxy.MethodAndArgs;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage implements Storage {
    private static final Map<MethodAndArgs, Object> cache = new HashMap<>();

    @Override
    public boolean containsKey(MethodAndArgs key) {
        return cache.containsKey(key);
    }

    @Override
    public Object get(MethodAndArgs key) {
        return cache.get(key);
    }

    @Override
    public Object put(MethodAndArgs key, Object value) {
        return cache.put(key, value);
    }
}
