package ru.sberbank.homework.dergun.task_03;

import java.lang.reflect.Proxy;

public class CacheProxy {

    public Object cache(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new DynamicProxyImpl(object));
    }
}
