package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.your_lastname.Operation;

public class Parser {
    public static long parseLong(String number) {
        Tuple<String, Integer> result = removePrefix(removeSuffix(number));
        String formattedNumber = removeUnderscopes(result.first);
        int radix = result.second;
        return Long.valueOf(formattedNumber, radix);
    }

    public static double parseDouble(String number) {
        return null;
    }

    public static Operation parseOperation(String operation) {
        if (operation.length() != 1) {
            throw new UnsupportedOperationException("Operation must contain only one character!");
        }
        char operationChar = operation.charAt(0);
        switch (operationChar) {
            case '+':
                return Operation.ADDITION;
            case '-':
                return Operation.SUBTRACTION;
            case '*':
                return Operation.MULTIPLICATION;
            case '/':
                return Operation.DIVISION;
            default:
                throw new UnsupportedOperationException("Not supported operation");
        }
    }

    private static String removeSuffix(String number) {
        String formattedNumber;
        if (number.matches(".*[dflDFL]{1}$")) {
            formattedNumber = number.substring(0, number.length() - 1);
        } else {
            formattedNumber = number;
        }
        checkUnderscopes(formattedNumber);
        return formattedNumber;
    }

    private static void checkUnderscopes(String number) {
        if (number.startsWith("_") || number.endsWith("_")) {
            throw new RuntimeException("!!!");
        }
        //for floating numbers
        if (number.contains("_.") || number.contains("._")) {
            throw new RuntimeException("!!!");
        }
    }

    private static String removeUnderscopes(String number) {
        return number.replace("_", "");
    }

    /**
     * Removes prefixes: 0, 0x and 0b.
     *
     * @param number
     * @return Tuple of formatted input number and its radix
     */
    private static Tuple<String, Integer> removePrefix(String number) {
        int radix = 10;
        if (number.startsWith("0")) {
            number = number.substring(1);
            radix = 8;
            checkUnderscopes(number);

            switch (number.charAt(0)) {
                case 'x':
                    radix = 16;
                    number = number.substring(1);
                    break;
                case 'b':
                    radix = 2;
                    number = number.substring(1);
                    break;
            }
            checkUnderscopes(number);
        }
        return new Tuple<String, Integer>(number, radix);
    }

    private static class Tuple<X, Y> {
        public final X first;
        public final Y second;

        public Tuple(X first, Y second) {
            this.first = first;
            this.second = second;
        }
    }
}
