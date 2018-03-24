package ru.sberbank.homework.chat.writer;

public class ConsoleWriter implements Writer {

    @Override
    public void write(String message) {
        System.out.println(message);
    }

}
