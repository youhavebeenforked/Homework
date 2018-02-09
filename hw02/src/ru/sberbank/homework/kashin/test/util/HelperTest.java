package ru.sberbank.homework.kashin.test.util;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.kashin.main.model.expressions.Multiplication;

import static ru.sberbank.homework.kashin.main.util.Assert.assertEquals;
import static ru.sberbank.homework.kashin.main.util.Helper.checkNotation;
import static ru.sberbank.homework.kashin.main.util.Helper.parser;

public class HelperTest {

    public void checkNotationTest() {
        Double actual = checkNotation("0b10101");
        Double expected = 21d;
        assertEquals(expected, actual);
    }

    public void parserTest() {
        Expression expected = new Multiplication();
        expected.setFirst(2.22);
        expected.setSecond(1.11);

        Expression actual = parser("2.22 * 1.11");

        assertEquals(expected, actual);
    }
}
