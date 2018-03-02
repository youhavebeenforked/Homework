package ru.sberbank.homework.abzaltdinov.first.output;

public class ConsoleUserOutput implements UserOutput {
    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }
}
