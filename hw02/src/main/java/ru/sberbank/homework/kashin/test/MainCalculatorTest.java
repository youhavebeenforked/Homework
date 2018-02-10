package ru.sberbank.homework.kashin.test;

import ru.sberbank.homework.kashin.test.calculator.CalculatorTest;
import ru.sberbank.homework.kashin.test.util.HelperTest;

public class MainCalculatorTest {
    public static void main(String[] args) {
        CalculatorTest test = new CalculatorTest();
        test.calculateTest();

        HelperTest helperTest = new HelperTest();
        helperTest.checkNotationTest();
        helperTest.parserTest();
    }
}
