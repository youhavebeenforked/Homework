package ru.sberbank.homework.common;

class UnknownCityException extends RuntimeException {
    UnknownCityException(String name) {
        super("Unknown city with name: " + name);
    }
}
