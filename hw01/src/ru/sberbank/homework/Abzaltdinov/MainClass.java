package ru.sberbank.homework.Abzaltdinov;

public class MainClass {
    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();
        int firstInt = 10;
        int secondInt = 3;
        System.out.println(firstInt + " + " + secondInt + " is " + calculator.sum(firstInt, secondInt));
        System.out.println(firstInt + " - " + secondInt + " is " + calculator.sub(firstInt, secondInt));
        System.out.println(firstInt + " * " + secondInt + " is " + calculator.mul(firstInt, secondInt));
        System.out.println(firstInt + " / " + secondInt + " is " + calculator.div(firstInt, secondInt));
    }
}
