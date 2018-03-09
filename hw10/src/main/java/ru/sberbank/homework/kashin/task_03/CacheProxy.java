package ru.sberbank.homework.kashin.task_03;


public class CacheProxy {
    private DynamicProxy proxy;

    public CacheProxy() {
        proxy = new DynamicProxy();
    }

    public CacheProxy(String path) {
        proxy = new DynamicProxy(path);
    }

    public Object cache(Object service) {
        return proxy.cloneAndAddCache(service);
    }
}
