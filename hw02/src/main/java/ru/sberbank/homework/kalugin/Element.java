package ru.sberbank.homework.kalugin;

/**
 * Элемент математического выражения.
 */
interface Element<T> {
    T getElement();

    default boolean isNumber() {
        return false;
    }
}
