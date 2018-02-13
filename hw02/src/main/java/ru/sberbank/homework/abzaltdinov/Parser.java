package ru.sberbank.homework.abzaltdinov;

public class Parser {

    public static double parseNumber(String number) throws NumberFormatException {
        double result;
        if (number.contains("."))
            result = Parser.parseDouble(number);
        else
            result = Parser.parseLong(number);
        return result;
    }

    private static long parseLong(String number) throws UnsupportedOperationException {
        Tuple<String, Integer> result = removePrefix(removeSuffix(number));
        String formattedNumber = removeUnderscopes(result.first);
        int radix = result.second;
        return Long.valueOf(formattedNumber, radix);
    }

    private static double parseDouble(String number) {
        return Double.valueOf(removeUnderscopes(removeSuffix(number)));
    }

    public static MyOperation parseOperation(String operation) {
        if (operation.length() != 1) {
            throw new UnsupportedOperationException("MyOperation must contain only one character!");
        }
        char operationChar = operation.charAt(0);
        switch (operationChar) {
            case '+':
                return new Addition();
            case '-':
                return new Subtraction();
            case '*':
                return new Multiplication();
            case '/':
                return new Division();
            default:
                throw new UnsupportedOperationException("Not supported operation");
        }
    }

    private static String removeSuffix(String number) {
        String formattedNumber;
        if (number.endsWith("l") || number.endsWith("L")) {
            formattedNumber = number.substring(0, number.length() - 1);
        } else {
            formattedNumber = number;
        }
        checkUnderscopes(formattedNumber);
        return formattedNumber;
    }

    private static void checkUnderscopes(String number) {
        if (number.startsWith("_") || number.endsWith("_")) {
            throw new NumberFormatException("Underscopes must not be at the beginning and end numbers");
        }
        //for floating numbers
        //if (number.contains("_.") || number.contains("._")) {
        //    throw new RuntimeException("!!!");
        //}
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
                case 'X':
                    radix = 16;
                    number = number.substring(1);
                    break;
                case 'b':
                case 'B':
                    radix = 2;
                    number = number.substring(1);
                    break;
            }
            checkUnderscopes(number);
        }
        return new Tuple<>(number, radix);
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
