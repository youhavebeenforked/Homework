package ru.sberbank.homework.abzaltdinov;

public class ErrorPrinter {
    public static void print(String message) {
        System.out.println("error > " + message);
    }

    public static void printWrongExpression() {
        ErrorPrinter.print("wrong expression");
    }
}
