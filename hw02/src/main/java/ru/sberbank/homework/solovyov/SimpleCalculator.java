package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class SimpleCalculator implements Calculator {

    private BigDecimal lastResult;
    private boolean isNextStep;

    public SimpleCalculator() {
        isNextStep = false;
        lastResult = new BigDecimal(0);
    }

    @Override
    public String calculate(String userInput) {

        try {
            Scanner scanner = new Scanner(userInput);
            int tokenCount = 0;
            while (scanner.hasNext()) {
                scanner.next();
                tokenCount++;
            }
            if (tokenCount != 3 & tokenCount != 2) {
                throw new IllegalArgumentException("wrong expression");
            }
            scanner = new Scanner(userInput);

            BigDecimal firstNumber = lastResult;
            String inputToken = "";
            try {
                inputToken = scanner.next();
                firstNumber = getNumber(inputToken);
                isNextStep = false;
            } catch (NumberFormatException exception) {
                isNextStep = true;
            }

            if (!isNextStep) {
                inputToken = scanner.next();
            }
            Operation operation = getOperation(inputToken);

            inputToken = scanner.next();
            BigDecimal secondNumber = getNumber(inputToken);

            lastResult = operation.executeOperation(firstNumber, secondNumber);
            return formatOutput(lastResult);
        } catch (IllegalArgumentException | ArithmeticException exception) {
            lastResult = new BigDecimal(0);
            return "error > " + exception.getMessage();
        }
    }

    private String formatOutput(BigDecimal number) {
        DecimalFormat df = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return df.format(number);
    }

    private Operation getOperation(String signString) {
        if (signString.length() > 1) {
            throw new IllegalArgumentException(signString);
        }
        Operation operation = Operation.getBySign(signString.charAt(0));
        if (operation == null) {
            throw new IllegalArgumentException(signString);
        }
        return operation;
    }

    private BigDecimal getNumber(String inputString) {
        String numberString = inputString.toLowerCase();

        boolean isLongNumber = numberString.endsWith("l");
        boolean isRealNumber = numberString.contains(".");

        //Если вещественное и суффикс long, то ошибка
        if (isRealNumber & isLongNumber) {
            throw new NumberFormatException(inputString);
        }

        //Убираем суффикс для long
        if (isLongNumber) {
            numberString = numberString.substring(0, numberString.length() - 1);
        }

        if (numberString.length() < 1) {
            throw new NumberFormatException(inputString);
        }

        if (isRealNumber) {
            if (numberString.matches("^[+-]?\\d+(_+\\d+)*\\.\\d+(_+\\d+)*$")) {
                return NumberNotation.DECIMAL.getDecimal(numberString);
            }
            throw new NumberFormatException(inputString);
        }

        if (numberString.matches("^[+-]?0x[\\da-f]+(_+[\\da-f]+)*$")) {
            return NumberNotation.HEXADECIMAL.getDecimal(numberString);
        }

        if (numberString.matches("^[+-]?0b[01]+(_+[01]+)*$")) {
            return NumberNotation.BINARY.getDecimal(numberString);

        }
        if (numberString.matches("^[+-]?0[0-7]+(_+[0-7]+)*$")) {
            return NumberNotation.OCTAL.getDecimal(numberString);
        }

        if (numberString.matches("^[+-]?\\d+(_+\\d+)*$")) {
            return NumberNotation.DECIMAL.getDecimal(numberString);
        }

        throw new NumberFormatException(inputString);
    }
}

