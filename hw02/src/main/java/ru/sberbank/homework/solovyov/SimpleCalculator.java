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

            lastResult = operation.executeOperation(firstNumber,secondNumber);
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

        try {
            //Если вещественное и суффикс long, то ошибка
            if (isRealNumber & isLongNumber) {
                throw new NumberFormatException();
            }

            //Убираем суффикс для long
            if (isLongNumber) {
                numberString = numberString.substring(0, numberString.length() - 1);
            }

            //Знак числа в начале
            boolean isPositive = true;
            if (numberString.length() > 0) {
                switch (numberString.charAt(0)) {
                    case '-':
                        isPositive = false;
                    case '+':
                        numberString = numberString.substring(1);
                }
            }

            numberString = numberString.replaceAll("[_]+", "_");

            //Литералы не могут начианться с _ и заканчиваться _
            if (numberString.startsWith("_") || numberString.endsWith("_")) {
                throw new NumberFormatException();
            }

            //Проверка количества точек и расстановки _ в вещественном числе
            if (isRealNumber) {
                String[] realNumberParts = numberString.split("[.]");
                if (realNumberParts.length > 2) {
                    throw new NumberFormatException();
                }
                if (realNumberParts[0].endsWith("_") | realNumberParts[1].startsWith("_")) {
                    throw new NumberFormatException();
                }
                numberString = numberString.replace("_", "");

                return new BigDecimal(numberString);
            }

            String number;

            if (numberString.matches("^0x[0-9a-f][0-9af_]*")) {
                number = numberString.substring(2);
                number = number.replace("_", "");
                return new BigDecimal((isPositive ? 1 : -1) * Long.parseLong(number, 16));
            }

            if (numberString.matches("^0b[01][01_]*")) {
                number = numberString.substring(2);
                number = number.replace("_", "");
                return new BigDecimal((isPositive ? 1 : -1) * Long.parseLong(number, 2));
            }
            if (numberString.matches("^0[0-7][0-7_]*")) {
                number = numberString.substring(1);
                number = number.replace("_", "");
                return new BigDecimal((isPositive ? 1 : -1) * Long.parseLong(number, 8));
            }

            numberString = numberString.replace("_", "");
            return new BigDecimal(numberString);

        } catch (NumberFormatException exception) {
            throw new NumberFormatException(inputString);
        }
    }
}

