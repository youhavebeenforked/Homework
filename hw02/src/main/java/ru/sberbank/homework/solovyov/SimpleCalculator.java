package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
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

        if (userInput.compareTo("quit") == 0) {
            return userInput;
        }

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

            switch (operation) {
                case PLUS:
                    lastResult = firstNumber.add(secondNumber);
                    break;
                case SUBTRACTION:
                    lastResult = firstNumber.subtract(secondNumber);
                    break;
                case MULTIPLICATION:
                    lastResult = firstNumber.multiply(secondNumber);
                    break;
                case DIVISION:
                    if (secondNumber.compareTo(BigDecimal.ZERO) != 0) {
                        lastResult = firstNumber.divide(secondNumber, 2, RoundingMode.HALF_UP);
                    } else {
                        throw new ArithmeticException("Division by zero!");
                    }
            }
            return formatOutput(lastResult);
        } catch (IllegalArgumentException | ArithmeticException exception) {
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

    private BigDecimal getNumber(String numberString) {
        boolean isLongNumber = numberString.endsWith("L");
        boolean isRealNumber = numberString.indexOf('.') != -1;

        if (isRealNumber & isLongNumber) {
            throw new NumberFormatException(numberString);
        }

        if (isRealNumber) {
            return new BigDecimal(numberString);
        }

        if (isLongNumber) {
            numberString = numberString.substring(0, numberString.length() - 1);
        }

        int prefixPointer = 0;
        int sign;
        switch (numberString.charAt(prefixPointer)) {
            case '+':
                sign = 1;
                prefixPointer++;
                break;
            case '-':
                sign = -1;
                prefixPointer++;
                break;
            default:
                sign = 1;
        }
        if (prefixPointer >= numberString.length()) {
            throw new NumberFormatException(numberString);
        }

        try {
            if (numberString.charAt(prefixPointer) == '0' & prefixPointer + 1 < numberString.length()) {
                String number;
                switch (numberString.charAt(prefixPointer + 1)) {
                    case 'x':
                        number = numberString.substring(prefixPointer + 2);
                        number = number.replace("_", "");
                        return new BigDecimal(sign * Long.parseLong(number, 16));
                    case 'b':
                        number = numberString.substring(prefixPointer + 2);
                        number = number.replace("_", "");
                        return new BigDecimal(sign * Long.parseLong(number, 2) );
                    default:
                        number = numberString.substring(prefixPointer + 1);
                        number = number.replace("_", "");
                        return new BigDecimal(sign * Long.parseLong(number, 8) );
                }
            } else {
                numberString = numberString.replace("_", "");
                return new BigDecimal(numberString);
            }
        } catch (NumberFormatException exception) {
            throw new NumberFormatException(numberString);
        }
    }
}

