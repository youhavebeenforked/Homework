package ru.sberbank.homework.Polushin;

import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OutputStreamWriter streamWriter = new OutputStreamWriter(System.out);
        new SimpleCalculator(scanner, streamWriter).run();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Emergency exit!!!")));
        System.out.println(13/0);
        System.out.println("Programs executed without any mistakes");

    }
}