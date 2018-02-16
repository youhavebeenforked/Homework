package ru.sberbank.homework.kochkova;

public class Main {

    public static void main(String[] args) {
        ConsoleInteraction consoleInteraction = new ConsoleInteraction();
        boolean finished = false;
        while (!finished) {
            finished = consoleInteraction.getLineAndPrintResult();
        }
    }
}
