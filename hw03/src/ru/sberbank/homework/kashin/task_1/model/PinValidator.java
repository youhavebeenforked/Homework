package ru.sberbank.homework.kashin.task_1.model;

public interface PinValidator {
    boolean giveAccess();
    boolean checkPin(String pin);
    void checkBlock();
}
