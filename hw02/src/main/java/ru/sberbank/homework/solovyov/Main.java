package ru.sberbank.homework.solovyov;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
        String result = "";
        boolean isNextStep = false;
        System.out.println("**********************");
        System.out.println("Welcome to Calculator!");
        System.out.println("**********************");

        while (true) {
            if (isNextStep) {
                result = calculator.calculate(result);
                if (result!=null) {
                    System.out.println("= " + result);
                }
            } else {
                System.out.println("Enter your arithmetic problem for calculation in the format a @ b, where @ is an operation +,-,*,/.");
                if (scanner.hasNextLine()) {
                    result = calculator.calculate(scanner.nextLine());
                    if (result!=null) {
                        System.out.println("= " + result);
                    }
                } else {
                    System.out.println("Nothing to calculate!");
                }
            }

            if (result == null){
                isNextStep = false;
                continue;
            }

            System.out.println("Type:\n- the next operation and the operand (format: @ c) to continue calculation\n" +
                    "- n(ew) for new calculation example\n- q(uit) for exit.");
            String userAnswer = scanner.nextLine();
            switch (userAnswer.charAt(0)) {
                case 'q':
                    System.out.println("See you later.");
                    return;
                case 'n':
                    isNextStep = false;
                    continue;
                default:
                    isNextStep = true;
                    result += " " + userAnswer;
            }
        }
    }

}

