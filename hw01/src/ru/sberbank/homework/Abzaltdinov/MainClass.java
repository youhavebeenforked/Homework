package ru.sberbank.homework.Abzaltdinov;

public class MainClass {
    public static void main(String[] args) {
        runAllTests();
    }

    public static void runAllTests() {
        SimpleCalculatorTest calculatorTest = new SimpleCalculatorTest();

        calculatorTest.intSummationTest();
        calculatorTest.intSubstractionTest();
        calculatorTest.intMultiplicationTest();
        calculatorTest.intDivisionTest();
        calculatorTest.intDivisionByZeroTest();

        calculatorTest.doubleSummationTest();
        calculatorTest.doubleSubstractionTest();
        calculatorTest.doubleMultiplicationTest();
        calculatorTest.doubleDivisionTest();
        calculatorTest.doubleDivisionByZeroTest();
    }
}
