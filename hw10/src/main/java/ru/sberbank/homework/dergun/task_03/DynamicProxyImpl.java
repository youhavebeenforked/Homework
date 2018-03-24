package ru.sberbank.homework.dergun.task_03;

import ru.sberbank.homework.dergun.task_03.Annotation.Cache;
import ru.sberbank.homework.dergun.task_03.Annotation.IgnoreArgument;
import ru.sberbank.homework.dergun.task_03.Storage.CacheStorage;
import ru.sberbank.homework.dergun.task_03.Storage.CacheType;
import ru.sberbank.homework.dergun.task_03.Storage.InDiskCache;
import ru.sberbank.homework.dergun.task_03.Storage.InMemoryCache;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DynamicProxyImpl implements InvocationHandler {
    private final HashMap<Method, CacheStorage> mapMethods = new HashMap<>();
    private final Object object;
    private CacheStorage cacheStorage;

    public DynamicProxyImpl(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(Cache.class) == null) {
            return method.invoke(object, args);
        }
        CacheKey cacheKey = getCacheKey(method, args);
        if (!mapMethods.containsKey(method)) {
            setSettingsCache(method.getAnnotation(Cache.class));
            mapMethods.put(method, cacheStorage);
        }
        if (cacheStorage.contains(cacheKey)) {
            return cacheStorage.getByKey(cacheKey);
        }
        Object result = method.invoke(object, args);
        if (result instanceof List) {
            int maxSizeList = method.getAnnotation(Cache.class).maxSizeList();
            List list = (List) result;
            result = list.subList(0, Math.min(list.size(), maxSizeList));
        }
        cacheStorage.addObject(cacheKey, result);
        return result;
    }

    private CacheKey getCacheKey(Method method, Object[] args) {
        List<Object> list = new ArrayList<>();
        final Annotation[][] paramAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < paramAnnotations.length; i++) {
            if (!Arrays.stream(paramAnnotations[i])
                    .filter(x -> x instanceof IgnoreArgument)
                    .findAny()
                    .isPresent()) {
                list.add(args[i]);
            }
        }
        return new CacheKey(method, list.toArray());
    }

    private void setSettingsCache(Cache annotation) {
        CacheSettings cacheSettings = new CacheSettings();
        if (!annotation.fileNamePrefix().equals("")) {
            cacheSettings.setFileName(annotation.fileNamePrefix());
        }
        if (!annotation.rootDirectory().equals("")) {
            cacheSettings.setDirectory(annotation.rootDirectory());
        }
        if (annotation.zip()) {
            cacheSettings.setZip(true);
        }
        if (annotation.cacheType().equals(CacheType.FILE)) {
            cacheSettings.setTypeFile(true);
        }
        setCacheStorage(cacheSettings);
    }

    private void setCacheStorage(CacheSettings cacheSettings) {
        if (cacheSettings.isTypeFile()) {
            cacheStorage = new InDiskCache();
            cacheStorage.changeSetting(cacheSettings);
        } else {
            cacheStorage = new InMemoryCache();
        }
    }
}
