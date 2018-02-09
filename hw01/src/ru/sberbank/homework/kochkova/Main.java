package ru.sberbank.homework.kochkova;

public class Main {

    public static void main(String[] args) {
        SimpleCalculatorTest test = new SimpleCalculatorTest();
        test.testOverflowByAdd();
        test.testDivisionByZeroInts();
        test.testDivisionByZeroDoubles();
        test.testOverflowByMul();
        test.testAddOfDoubles();
        test.testSubOfDoubles();
        test.testMulOfDoubles();
        test.testDivOfDoubles();
        test.testAddOfInts();
        test.testSubOfInts();
        test.testMulOfInts();
        test.testDivOfInts();
    }
}
