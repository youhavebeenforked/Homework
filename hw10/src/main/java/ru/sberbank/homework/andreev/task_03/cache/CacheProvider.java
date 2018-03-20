package ru.sberbank.homework.andreev.task_03.cache;

import java.lang.reflect.Method;
import java.util.Optional;

public interface CacheProvider {
    Optional<Object> getCachedResult(Method method, Object[] args);

    void putCachedResult(Method method, Object[] args, Object result);
}
