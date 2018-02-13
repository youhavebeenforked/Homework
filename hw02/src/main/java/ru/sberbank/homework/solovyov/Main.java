package ru.sberbank.homework.solovyov;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleCalculator calculator = new SimpleCalculator();
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.compareTo("quit") == 0) {
                return;
            }

            System.out.println(calculator.calculate(userInput));
        }

    }

}

