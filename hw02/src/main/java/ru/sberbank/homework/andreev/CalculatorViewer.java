package ru.sberbank.homework.andreev;

import java.util.Scanner;

public class CalculatorViewer {
    static CalculatorOnArray calc = new CalculatorOnArray();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to calculator, please input expression");
        while (true){
            String input = sc.nextLine();
            if ("exit".equals(input)){
                return;
            }
            System.out.println(calc.calculate(input));
        }

    }
}
