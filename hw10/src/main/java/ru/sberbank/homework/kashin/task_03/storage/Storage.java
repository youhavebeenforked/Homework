package ru.sberbank.homework.kashin.task_03.storage;

import ru.sberbank.homework.kashin.task_03.proxy.Args;

public interface Storage {
    boolean containsKey(Args key);

    Object get(Args key);

    Object put(Args key, Object value);
}
