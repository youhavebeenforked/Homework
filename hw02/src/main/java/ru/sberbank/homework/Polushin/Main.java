package ru.sberbank.homework.Polushin;

import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OutputStreamWriter streamWriter = new OutputStreamWriter(System.out);
        new MyCalculator(scanner, streamWriter).run();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Thx for using my programs, good bye!")));
        System.out.println("Programs executed without any mistakes");

    }
}