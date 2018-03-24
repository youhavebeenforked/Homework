package ru.sberbank.homework.bobrov;

import ru.sberbank.homework.common.Calculator;

import java.util.Scanner;

/**
 * Improved calculator.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 08.02.2018
 */


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new CalcImpl();
        System.out.println("Enter expression or quit for exit");
        while (true) {
            String expression = scanner.nextLine();
            if (expression.equals("quit")) {
                break;
            }
            System.out.println(calc.calculate(expression));
        }
    }
}
