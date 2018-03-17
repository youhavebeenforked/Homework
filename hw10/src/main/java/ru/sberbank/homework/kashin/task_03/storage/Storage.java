package ru.sberbank.homework.kashin.task_03.storage;

import ru.sberbank.homework.kashin.task_03.proxy.MethodAndArgs;

public interface Storage {
    boolean containsKey(MethodAndArgs key);

    Object get(MethodAndArgs key);

    Object put(MethodAndArgs key, Object value);
}
