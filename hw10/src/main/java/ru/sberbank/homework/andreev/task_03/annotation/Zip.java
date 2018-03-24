package ru.sberbank.homework.andreev.task_03.annotation;

public enum Zip {
    EMPTY(false), TRUE(true), FALSE(false);
    boolean value;

    Zip(boolean zip) {
        this.value = zip;
    }

    public boolean getValue() {
        return value;
    }
}
