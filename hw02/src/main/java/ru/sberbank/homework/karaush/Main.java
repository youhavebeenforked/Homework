package ru.sberbank.homework.karaush;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MyCalculator calculator = new MyCalculator();
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("quit")) {
            String result = calculator.calculate(input);
            System.out.println(result);
            input = scanner.nextLine();
        }
    }
}
