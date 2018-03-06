package ru.sberbank.homework.kalugin;

/**
 * Элемент математического выражения.
 */
interface Element<T> {
    boolean isNumber();
    T getElement();
}
