package ru.sberbank.homework.maruev;

import java.security.InvalidParameterException;

/**
 * Created by Иван on 15.02.2018.
 */
public class AssertClass {

    private static String[] actualsTrue = {"1 + 1", "+ 2", "/ 2", "* 10"};
    private static String[] expectedTrue = {"2", "4", "2", "20"};
    private static String[] actualsFalse = {"_1 + 1", "^ 2", "1 + dd", "1 / 0", "1+1"};
    private static String[] expectedFalse = {"error > _1", "error > ^", "error > dd", "error > Division by zero", "error > wrong expression"};

    public static void main(String[] args) {
        AssertClass.assertEquals("1L + 1L", "2");
        AssertClass.assertEquals("1 / 2", "0.5");
        AssertClass.assertEquals("1.0 - 1", "0");
        AssertClass.assertEquals("5.0 * 2", "10");
        AssertClass.assertEquals("-1 - -1", "0");
        AssertClass.assertEquals("1 * 0.5", "0.5");

        AssertClass.assertEquals("4 - 1", "3");
        AssertClass.assertEquals("100_200 + 100", "100300");
        AssertClass.assertEquals("100_100._22 + 100", "error > 100_100._22");
        AssertClass.assertEquals("-0xA + +0xA", "0");
        AssertClass.assertEquals("0_11 - 0_11", "0");
        AssertClass.assertEquals("-0_11 + +0_11", "0");
        AssertClass.assertEquals("11_L + +0_11", "error > 11_L");

        AssertClass.assertSequence(actualsTrue, expectedTrue);
        AssertClass.assertSequence(actualsFalse, expectedFalse);

        System.out.println("Test accepted");

    }

    private static void assertEquals(String inputData, String expected) {
        HardCalculator hardCalculator = new HardCalculator();
        String result = hardCalculator.calculate(inputData);

        if (!result.equals(expected)) {
            throw new InvalidParameterException();
        }
    }

    private static void assertSequence(String[] inputData, String[] expected) {
        HardCalculator hardCalculator = new HardCalculator();

        for (int i = 0; i < inputData.length; i++) {
            if (!hardCalculator.calculate(inputData[i]).equals(expected[i])) {
                throw new InvalidParameterException();
            }
        }
    }
}
