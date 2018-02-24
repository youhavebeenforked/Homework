package ru.sberbank.homework.abzaltdinov;

import java.util.HashMap;

public class Parser {

    private static final String REGEX_CHECK_UNDERSCOPES = "^((_|0(_[bBxX]|[bBxX]_)).*|.*(\\._|_\\.).*|.*_[lL]?)$";
    private static HashMap<Character, Operation> operations;

    static {
        operations = new HashMap<>();
        operations.put('+', new Addition());
        operations.put('-', new Subtraction());
        operations.put('*', new Multiplication());
        operations.put('/', new Division());
    }

    private static HashMap<Character, Integer> alphabet;

    static {
        alphabet = new HashMap<>();
        for (int i = 0; i < 10; ++i) {
            alphabet.put((char) (i + '0'), i);
        }
        alphabet.put('A', 10);
        alphabet.put('B', 11);
        alphabet.put('C', 12);
        alphabet.put('D', 13);
        alphabet.put('E', 14);
        alphabet.put('F', 15);
    }

    private static HashMap<Integer, Character> reverseAlphabet;

    static {
        reverseAlphabet = new HashMap<>();
        for (int i = 0; i < 10; ++i) {
            reverseAlphabet.put(i, (char) (i + '0'));
        }
        reverseAlphabet.put(10, 'A');
        reverseAlphabet.put(11, 'B');
        reverseAlphabet.put(12, 'C');
        reverseAlphabet.put(13, 'D');
        reverseAlphabet.put(14, 'E');
        reverseAlphabet.put(15, 'F');
    }

    public static double parseNumber(String number) throws NumberFormatException {
        double result;
        if (number.contains(".")) {
            result = Parser.parseDouble(number);
        } else {
            result = Parser.parseLong(number);
        }
        return result;
    }

    public static Long parseLong(String number) throws NumberFormatException {
        boolean isNegative = false;
        if (number.startsWith("-")) {
            isNegative = true;
            number = number.substring(1);
        }
        if (number.startsWith("+")) {
            number = number.substring(1);
        }
        int radix = getRadix(number);
        int maxBitCount = getMaxBitCount(number);
        int maxLength = maxBitCount / (int) (Math.log(radix) / Math.log(2));
        checkUnderscopes(number);
        String formattedNumber = removeUnderscopes(removePrefix(removeSuffix(number)));
        formattedNumber = formattedNumber.toUpperCase();
        if (!isCharactersInAlphabet(formattedNumber, radix)) {
            throw new NumberFormatException("Number has invalid symbol(s)");
        }
        // catching negative numbers with sign bit == 1
        boolean isReversed = false;
        if (radix != 10 && formattedNumber.length() == maxLength) {
            int firstDigit = alphabet.get(formattedNumber.charAt(0));
            if (firstDigit >= radix / 2) {
                formattedNumber = Parser.reverseNegativeToPositive(formattedNumber, radix);
                isReversed = true;
                isNegative = !isNegative;
            }
        }
        long result = Long.valueOf(formattedNumber, radix);
        if (isReversed) {
            result++;
        }
        if (isNegative) {
            result = -result;
        }
        return result;
    }

    public static Double parseDouble(String number) throws NumberFormatException {
        checkUnderscopes(number);
        return Double.valueOf(removeUnderscopes(removeSuffix(number)));
    }

    public static Operation parseOperation(String operation) {
        if (operation.length() != 1) {
            throw new UnsupportedOperationException("Operation must contain only one character!");
        }
        char operationChar = operation.charAt(0);
        Operation concreteOperation = operations.get(operationChar);
        if (concreteOperation != null) {
            return concreteOperation;
        } else {
            throw new UnsupportedOperationException("Not supported operation!");
        }
    }

    private static int getRadix(String number) {
        int radix = 10;
        if (number.startsWith("0")) {
            radix = 8;
            if (number.length() > 1) {
                switch (number.charAt(1)) {
                    case 'x':
                    case 'X':
                        radix = 16;
                        break;
                    case 'b':
                    case 'B':
                        radix = 2;
                        break;
                }
            }
        }
        return radix;
    }

    private static int getMaxBitCount(String number) {
        int maxBitCount = 32; // for int;
        if (number.endsWith("L") || number.endsWith("l")) {
            maxBitCount = 64;
        }
        return maxBitCount;
    }

    private static String removeSuffix(String number) {
        String formattedNumber;
        if (number.endsWith("l") || number.endsWith("L")) {
            formattedNumber = number.substring(0, number.length() - 1);
        } else {
            formattedNumber = number;
        }
        return formattedNumber;
    }

    private static void checkUnderscopes(String number) {
        if (number.matches(REGEX_CHECK_UNDERSCOPES)) {
            throw new NumberFormatException("Wrong position of underscopes");
        }
    }

    private static String removeUnderscopes(String number) {
        return number.replace("_", "");
    }

    /**
     * Removes prefixes: 0, 0x and 0b.
     *
     * @param number input number
     * @return Tuple of formatted input number and its radix
     */
    private static String removePrefix(String number) {
        if (number.length() > 1 && number.startsWith("0")) {
            number = number.substring(1);
            char c = number.charAt(0);
            if (c == 'x'
                    || c == 'X'
                    || c == 'b'
                    || c == 'B') {
                number = number.substring(1);
            }
        }
        return number;
    }

    private static boolean isCharactersInAlphabet(String number, int radix) {
        for (int i = 0; i < number.length(); ++i) {
            Integer symbolNumber = alphabet.get(number.charAt(i));
            if (symbolNumber == null || symbolNumber >= radix) {
                return false;
            }
        }
        return true;
    }

    private static String reverseNegativeToPositive(String number, int radix) {
        char[] result = new char[number.length()];
        for (int i = 0; i < number.length(); ++i) {
            result[i] = reverseAlphabet.get(radix - (alphabet.get(number.charAt(i))) - 1);
        }
        return new String(result);
    }
}
