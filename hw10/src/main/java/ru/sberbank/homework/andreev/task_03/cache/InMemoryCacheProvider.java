package ru.sberbank.homework.andreev.task_03.cache;

import java.lang.reflect.Method;
import java.util.*;

public class InMemoryCacheProvider implements CacheProvider {
    private Map<Method, Map<Integer, Object>> cache = new HashMap<>();
    private CacheParameterProcessor defaultParameterProcessor;

    public InMemoryCacheProvider(CacheParameterProcessor cacheParameterProcessor) {
        this.defaultParameterProcessor = cacheParameterProcessor;
    }

    @Override
    public Optional<Object> getCachedResult(Method method, Object[] args) {
        CacheParameterProcessor parameterProcessor = defaultParameterProcessor.getCacheParameterProcessor(method);
        Map<Integer, Object> cacheForMethod = cache.getOrDefault(method, Collections.emptyMap());
        return Optional.ofNullable(cacheForMethod.get(parameterProcessor.calculateHash(args)));
    }

    @Override
    public void putCachedResult(Method method, Object[] args, Object result) {
        Map<Integer, Object> cacheForMethod;
        if(cache.containsKey(method)){
            cacheForMethod = cache.get(method);
        } else {
            cacheForMethod = new HashMap<>();
            cache.put(method,cacheForMethod);
        }

        CacheParameterProcessor parameterProcessor = defaultParameterProcessor.getCacheParameterProcessor(method);
        cacheForMethod.put(parameterProcessor.calculateHash(args),result);
    }
}
