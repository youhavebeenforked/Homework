package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String nextLine;
        String quitLoop = "quit";
        System.out.println(Double.valueOf("15.03d"));
        System.out.println(Double.valueOf("15."));
        System.out.println(Long.valueOf("1234"));
        //System.out.println(Long.parseLong("1723445L"));
        System.out.println(Long.valueOf("1742", 8));
        //System.out.println(Long.valueOf("01742L"));
        System.out.println(Long.valueOf("3F4", 16));
        //System.out.println(Long.valueOf("0x3F4L"));
        System.out.println(Long.valueOf("1011", 2));
        //System.out.println(Long.valueOf("0b101110101001010101011L"));
        //System.out.println(Long.valueOf("123_456_789"));
        System.out.println(in.nextInt());
        Calculator calculator = new CalculatorImpl();
        while (!(nextLine = in.nextLine()).equals(quitLoop)) {
            calculator.calculate(nextLine);
        }
    }
}
