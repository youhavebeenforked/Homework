package ru.sberbank.homework.kashin.generics;

import java.util.Map;

/**
 * Дополнить. Параметризовать. Создать класс, реализующий интерфейс.
 */
public interface CountMap<K> {
    /**
     * добавляет элемент в этот контейнер.
     */
    void add(K k);

    /**
     * Возвращает количество добавлений данного элемента
     */
    int getCount(K k);

    /**
     * Удаляет элемент и контейнера и возвращает количество его добавлений(до удаления)
     */
    int remove(K k);

    /**
     * количество разных элементов
     */
    int size();

    /**
     * Добавить все элементы из source в текущий контейнер, при совпадении ключей, суммировать значения
     */
    void addAll(CountMap<? extends K> source);

    /**
     * Вернуть java.util.Map. ключ - добавленный элемент, значение - количество его добавлений
     */
    Map<K, Integer> toMap();

    /**
     * Тот же самый контракт как и toMap(), только всю информацию записать в destination
     */
    void toMap(Map<? super K, ? super Integer> destination);

}
