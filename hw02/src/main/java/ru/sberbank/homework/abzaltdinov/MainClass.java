package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.common.Calculator;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nextLine;
        String quitLoop = "quit";
        Calculator calculator = new CalculatorImpl();
        while (!(nextLine = in.nextLine()).equals(quitLoop)) {
            System.out.println(calculator.calculate(nextLine));
        }
    }
}
