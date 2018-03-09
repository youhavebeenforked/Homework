package ru.sberbank.homework.kashin.task_03;

/**
 * Если создаем экземпляр CacheProxy без параметров, то хранение кэша будет в памяти JVM.
 * Чтобы хранение было в файловой системе, то в аргумент при создании передать директорию для хранения кэша.
 * При хранении в файловой системе класс должен имплементировать интерфейс Serializable.
 */
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
