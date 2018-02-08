package ru.sberbank.homework.kashin.test.calculator;

import ru.sberbank.homework.kashin.main.calculator.CalculatorImpl;
import ru.sberbank.homework.kashin.main.util.Assert;
import ru.sberbank.homework.your_lastname.Calculator;

public class CalculatorTest {
    static Calculator calculator = new CalculatorImpl();

    public void calculateTest() {
        String expected = "4809.0";
        String actual = calculator.calculate("0345 * 0b10101");
        Assert.assertEquals(expected, actual);
    }
}
