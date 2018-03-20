package ru.sberbank.homework.andreev.task_03;

import ru.sberbank.homework.andreev.task_03.cache.CacheParameterProcessor;
import ru.sberbank.homework.andreev.task_03.cache.CacheProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Optional;

public class CacheHandler implements InvocationHandler {
    private final Object delegate;
    private CacheParameterProcessor defaultParameterProcessor;

    public CacheHandler(Object delegate, CacheParameterProcessor cacheParameterProcessor) {
        this.delegate = delegate;
        this.defaultParameterProcessor = new CacheParameterProcessor(cacheParameterProcessor);
        defaultParameterProcessor.setObjectHash(delegate.hashCode());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        CacheParameterProcessor localParameterProcessor = defaultParameterProcessor.getCacheParameterProcessor(method);
        CacheProvider cacheProvider = localParameterProcessor.getCacheProvider();
        Optional<Object> cachedResult = cacheProvider.getCachedResult(method, args);
        if (cachedResult.isPresent()) {
            return cachedResult.get();
        } else {
            Object result = method.invoke(delegate, args);
            Object cacheResult = localParameterProcessor.checkListResult(result);
            cacheProvider.putCachedResult(method, args, cacheResult);
            return result;
        }
    }


}
