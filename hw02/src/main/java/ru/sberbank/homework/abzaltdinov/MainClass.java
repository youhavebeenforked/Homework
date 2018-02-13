package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nextLine;
        String quitLoop = "quit";
        System.out.println(Parser.parseNumber("15.03"));
        System.out.println(Parser.parseNumber("15."));
        System.out.println(Parser.parseNumber("1234"));
        System.out.println(Parser.parseNumber("1723445L"));
        System.out.println(Parser.parseNumber("01742"));
        System.out.println(Parser.parseNumber("01742L"));
        System.out.println(Parser.parseNumber("0X3F4"));
        System.out.println(Parser.parseNumber("0x3F4L"));
        System.out.println(Parser.parseNumber("0B1011"));
        System.out.println(Parser.parseNumber("0b101110101001010101011L"));

        Calculator calculator = new CalculatorImpl();
        while (!(nextLine = in.nextLine()).equals(quitLoop)) {
            System.out.println(calculator.calculate(nextLine));
        }
    }
}
