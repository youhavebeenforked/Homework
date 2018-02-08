package ru.sberbank.homework.kashin.test.util;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.your_lastname.Operation;

import static ru.sberbank.homework.kashin.main.util.Assert.assertEquals;
import static ru.sberbank.homework.kashin.main.util.Helper.calculateHelper;
import static ru.sberbank.homework.kashin.main.util.Helper.checkNotation;
import static ru.sberbank.homework.kashin.main.util.Helper.parser;

public class HelperTest {

    public void checkNotationTest() {
        Double actual = checkNotation("0b10101");
        Double expected = 21d;
        assertEquals(expected, actual);
    }

    public void parserTest() {
        Expression expected = new Expression();
        expected.setFirst(2.22);
        expected.setSecond(1.11);
        expected.setOperator(Operation.MULTIPLICATIOM);

        Expression actual = parser("2.22 * 1.11");

        assertEquals(expected, actual);
    }

    public void calculateHelperTest() {
        String expected = "2.4642";

        Expression expression = new Expression();
        expression.setFirst(2.22);
        expression.setSecond(1.11);
        expression.setOperator(Operation.MULTIPLICATIOM);

        String actual = calculateHelper(expression);
        assertEquals(expected, actual);
    }


}
