package ru.sberbank.homework.kashin.task_1.writer;

public class ConsoleWriter implements Writer {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
