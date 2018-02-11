package ru.sberbank.homework.solovyov;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleCalculator calculator = new SimpleCalculator();
        String calcResult = "";

        while (calcResult.compareTo("quit") != 0) {
            calcResult = calculator.calculate(scanner.nextLine());
        }

    }

}

