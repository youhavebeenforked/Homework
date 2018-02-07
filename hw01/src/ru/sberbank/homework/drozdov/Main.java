package ru.sberbank.homework.drozdov;

import ru.sberbank.homework.drozdov.tests.SimpleCalculatorTest;

public class Main {
    public static void main(String[] args) {
        SimpleCalculatorTest simpleCalculatorTest = new SimpleCalculatorTest();
        simpleCalculatorTest.sumTest(5, 6, 11);
        simpleCalculatorTest.sumTest(5.4, 9.7, 15.1);
        simpleCalculatorTest.subtractionTest(12, 5, 7);
        simpleCalculatorTest.subtractionTest(12.0, 7.5, 4.5);
        simpleCalculatorTest.multiplyTest(4, 5, 20);
        simpleCalculatorTest.multiplyTest(4.0, 7.0, 28.0);
        simpleCalculatorTest.divisionTest(542, 2, 271);
        simpleCalculatorTest.divisionTest(25.4, 2.1, 12.095238);
        simpleCalculatorTest.divisionTest(25.4, 0.0, Double.POSITIVE_INFINITY);
        simpleCalculatorTest.divisionTest(0.0, 0.0, Double.NaN);
    }
}
