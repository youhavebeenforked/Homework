package ru.sberbank.homework.kashin.task_03;

import ru.sberbank.homework.kashin.task_03.storage.FileStorage;
import ru.sberbank.homework.kashin.task_03.storage.InMemoryStorage;
import ru.sberbank.homework.kashin.task_03.storage.Storage;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class DynamicProxy {
    private Storage cache;

    public DynamicProxy() {
        cache = new InMemoryStorage();
    }

    public DynamicProxy(String path) {
        cache = new FileStorage(path);
    }

    public Object cloneAndAddCache(Object object) {
        Object result;
        String name = object.getClass().getName();

        if (cache.containsKey(name + ".ser")) {
            result = cache.get(name);
        } else {
            result = cloneObject(object);

            Cache annotation = object.getClass().getAnnotation(Cache.class);
            if (annotation != null) {
                cache.put(name, result);
            }
        }
        return result;
    }

    public Object cloneObject(Object object) {
        try {
            Object clone = object.getClass().newInstance();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(object) == null || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                if (field.getType().isPrimitive() || field.getType().equals(String.class)
                        || field.getType().getSuperclass().equals(Number.class)
                        || field.getType().equals(Boolean.class)) {
                    field.set(clone, field.get(object));
                } else {
                    Object childObj = field.get(object);
                    if (childObj == object) {
                        field.set(clone, clone);
                    } else {
                        field.set(clone, cloneObject(field.get(object)));
                    }
                }
            }
            return clone;
        } catch (Exception e) {
            return null;
        }
    }
}
