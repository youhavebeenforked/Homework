package ru.sberbank.homework.dergun.task_03.Storage;

import ru.sberbank.homework.dergun.task_03.CacheKey;
import ru.sberbank.homework.dergun.task_03.CacheSettings;

public interface CacheStorage {
    Object getByKey(CacheKey key);

    void addObject(CacheKey key, Object object);

    boolean contains(CacheKey key);

    void changeSetting(CacheSettings settings);
}
