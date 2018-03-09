package ru.sberbank.homework.kashin.task_03.storage;

public interface Storage {
    boolean containsKey(String key);
    Object get(String  key);
    Object put(String key, Object value);
}
