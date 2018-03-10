package ru.sberbank.homework.kashin.task_03.proxy;

import ru.sberbank.homework.kashin.task_03.annotations.Cache;
import ru.sberbank.homework.kashin.task_03.storage.FileStorage;
import ru.sberbank.homework.kashin.task_03.storage.InMemoryStorage;
import ru.sberbank.homework.kashin.task_03.storage.Storage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    private String root;
    private Storage storage;

    public DynamicProxy(String root) {
        this.root = root;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class[] interfaces, final T code) {

        return (T) Proxy.newProxyInstance(code.getClass().getClassLoader(), interfaces, (proxy, method, args) -> {
            Method methodImpl = code.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
            Cache annotationCache = methodImpl.getAnnotation(Cache.class);
            if (annotationCache != null) {
                if (annotationCache.typeStorage()) {
                    storage = new FileStorage(root);
                } else {
                    storage = new InMemoryStorage();
                }
                MethodAndArgs input = new MethodAndArgs(methodImpl, args);
                Object result = storage.get(input);
                if (result == null && !storage.containsKey(input)) {
                    try {
                        result = methodImpl.invoke(code, args);
                        storage.put(input, result);
                    } catch (InvocationTargetException e) {
                        throw e.getTargetException();
                    }
                }
                return result;
            } else {
                return method.invoke(code, args);
            }
        });
    }
}
