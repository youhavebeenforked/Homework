package ru.sberbank.homework.kashin.task_03;

import ru.sberbank.homework.kashin.task_03.proxy.DynamicProxy;

/**
 * CacheProxy можно создать конструктором без параметров, тогда файлы кэша будут храниться по умолчанию
 * в корневой директории проекта /storage.
 * Можно создать с параметром корневой директории для хранения файлов кэша.
 * Чтобы метод кэшировался, он должен быть отмечен аннотацией Cache.
 * @see ru.sberbank.homework.kashin.task_03.annotations.Cache
 */
public class CacheProxy {

    private String root;

    public CacheProxy() {
        root = "storage";
    }

    public CacheProxy(String root) {
        this.root = root;
    }

    public Object cache(Object service) {
        DynamicProxy proxy = new DynamicProxy(root);
        return proxy.create(service.getClass().getInterfaces(), service);
    }
}
