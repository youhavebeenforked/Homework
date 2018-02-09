package ru.sberbank.homework.kashin.test.calculator;

import ru.sberbank.homework.kashin.main.calculator.CalculatorImpl;
import ru.sberbank.homework.your_lastname.Calculator;

import static ru.sberbank.homework.kashin.main.util.Assert.assertEquals;

public class CalculatorTest {
    private static Calculator calculator = new CalculatorImpl();

    public void calculateTest() {
        String expectedFirsf = "4809.0";
        String actualFirst = calculator.calculate("0345 * 0b10101");
        assertEquals(expectedFirsf, actualFirst);
        double expectedSecond = 4624.038461538461;
        double actualSecond = new Double(calculator.calculate("/ 1.04"));
        assertEquals(expectedSecond, actualSecond);
    }
}
