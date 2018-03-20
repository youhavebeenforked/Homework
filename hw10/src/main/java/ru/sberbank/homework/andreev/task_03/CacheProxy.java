package ru.sberbank.homework.andreev.task_03;

import ru.sberbank.homework.andreev.task_03.cache.CacheParameterProcessor;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheParameterProcessor defaultCacheParameterProcessor;

    public CacheProxy(CacheParameterProcessor defaultCacheParameterProcessor) {
        this.defaultCacheParameterProcessor = defaultCacheParameterProcessor;
    }

    public <T> T cache(T instance) {
        Object o = Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(), new CacheHandler(instance, defaultCacheParameterProcessor));
        return (T) o;
    }
}
