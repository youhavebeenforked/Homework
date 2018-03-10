package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class SimpleCalculator implements Calculator {

    private BigDecimal lastResult;
    private boolean hasFirstExpression = false;

    @Override
    public String calculate(String userInput) {

        try {
            Scanner scanner = new Scanner(userInput);
            List<String> tokens = new ArrayList<>(3);
            while (scanner.hasNext()) {
                tokens.add(scanner.next());
            }
            if (tokens.size() != 3 & tokens.size() != 2) {
                throw new IllegalArgumentException("wrong expression");
            }

            BigDecimal firstNumber;
            Operation operation;
            BigDecimal secondNumber;
            if (tokens.size() == 3) {
                firstNumber = getNumber(tokens.get(0));
                operation = getOperation(tokens.get(1));
                secondNumber = getNumber(tokens.get(2));
                hasFirstExpression = true;
            } else if (hasFirstExpression) {
                firstNumber = lastResult;
                operation = getOperation(tokens.get(0));
                secondNumber = getNumber(tokens.get(1));
            } else {
                throw new IllegalArgumentException("wrong expression");
            }

            lastResult = operation.executeOperation(firstNumber, secondNumber);
            return formatOutput(lastResult);
        } catch (IllegalArgumentException | ArithmeticException exception) {
            lastResult = null;
            hasFirstExpression = false;
            return "error > " + exception.getMessage();
        }
    }

    private String formatOutput(BigDecimal number) {
        DecimalFormat df = new DecimalFormat("###.##", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        return df.format(number);
    }

    private Operation getOperation(String signString) {
        if (signString.length() > 1) {
            throw new IllegalArgumentException("wrong expression");
        }
        Operation operation = Operation.getBySign(signString.charAt(0));
        if (operation == null) {
            throw new IllegalArgumentException("wrong expression");
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
        if (numberString.matches("^[+-]?0(_*[0-7]+)+$")) {
            return NumberNotation.OCTAL.getDecimal(numberString);
        }

        if (numberString.matches("^[+-]?([1-9]\\d*(_+\\d+)*|0)$")) {
            return NumberNotation.DECIMAL.getDecimal(numberString);
        }

        throw new NumberFormatException(inputString);
    }
}

