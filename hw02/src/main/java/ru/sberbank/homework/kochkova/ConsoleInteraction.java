package ru.sberbank.homework.kochkova;

import java.util.Scanner;

/**
 * Created by sofya on 09.02.2018.
 */

public class ConsoleInteraction {
    private static Scanner scanner = new Scanner(System.in);
    private CalculatorImpl calculator = new CalculatorImpl();

    public boolean getLineAndPrintResult() {
        String input = scanner.nextLine();
        if (input.equals("quit")) {
            return true;
        }
        String result = calculator.calculate(input);
        System.out.println(result);
        return false;
    }
}
