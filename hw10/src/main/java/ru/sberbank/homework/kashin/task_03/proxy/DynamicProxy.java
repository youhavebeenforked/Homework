package ru.sberbank.homework.kashin.task_03.proxy;

import ru.sberbank.homework.kashin.task_03.annotations.Cache;
import ru.sberbank.homework.kashin.task_03.storage.FileStorage;
import ru.sberbank.homework.kashin.task_03.storage.InMemoryStorage;
import ru.sberbank.homework.kashin.task_03.storage.Storage;
import ru.sberbank.homework.kashin.task_03.storage.TypeStorage;

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

        return (T) Proxy.newProxyInstance(code.getClass().getClassLoader(), interfaces, (proxy, interfaceMethod, args) -> {
            Method method = code.getClass().getDeclaredMethod(interfaceMethod.getName(), interfaceMethod.getParameterTypes());
            Cache annotationCache = method.getAnnotation(Cache.class);
            if (annotationCache != null) {
                if (annotationCache.typeStorage() == TypeStorage.FS) {
                    storage = new FileStorage(root);
                } else {
                    storage = new InMemoryStorage();
                }

                MethodAndArgs input = new MethodAndArgs(method, args);
                Object result = storage.get(input);
                if (result == null && !storage.containsKey(input)) {
                    try {
                        result = method.invoke(code, args);
                        storage.put(input, result);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException("Ошибка при выполнее метода " + method.getName() + ". Описание: " +  e.getMessage());
                    }
                }
                return result;
            } else {
                return interfaceMethod.invoke(code, args);
            }
        });
    }
}
