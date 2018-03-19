package ru.sberbank.homework.bobrov;

import ru.sberbank.homework.bobrov.oper.Add;
import ru.sberbank.homework.bobrov.oper.Div;
import ru.sberbank.homework.bobrov.oper.Multipl;
import ru.sberbank.homework.bobrov.oper.Operation;
import ru.sberbank.homework.bobrov.oper.Subtract;

import java.util.HashMap;
import java.util.Map;

/**
 * Improved calculator.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 09.02.2018
 */


public class Parser {
    private Character symbolOperation;
    private double first;
    private double second;
    private Map<Character, Operation> operation = new HashMap<>();


    public Character getSymbolOperation() {
        return symbolOperation;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }

    public Map<Character, Operation> getOperation() {
        if (operation.isEmpty()) {
            fillOperation();
        }
        return operation;
    }

    public void parsingString(String inputString) {
        String secondDigit = null;
        String firstDigit = null;
        int spaceCount = 0;
        inputString = inputString.toLowerCase();

        for (Character character : inputString.toCharArray()) {
            if (character == ' ') {
                spaceCount++;
            }
        }
        if (spaceCount != 2) {
            throw new IllegalArgumentException("error > wrong expression");
        }

        if (operation.isEmpty()) {
            fillOperation();
        }
        try {
            firstDigit = inputString.substring(0, inputString.indexOf(" "));
            first = parsingDigit(firstDigit);
        } catch (Exception e) {
            if (firstDigit == null) {
                throw new IllegalArgumentException("error > wrong expression");
            } else {
                throw new NumberFormatException(String.format("Error > %s", firstDigit));
            }
        }
        try {
            secondDigit = inputString.substring(inputString.lastIndexOf(" "), inputString.length()).trim();
            second = parsingDigit(secondDigit);
        } catch (Exception e) {
            if (secondDigit == null) {
                throw new IllegalArgumentException("error > wrong expression");
            } else {
                throw new NumberFormatException(String.format("Error > %s", secondDigit));
            }
        }
        String tmpSymbol = inputString.replace(firstDigit, "");
        symbolOperation = parsingOperation(tmpSymbol.replace(secondDigit, "").trim());
    }

    public double parsingDigit(String input) {
        if (input.startsWith("_") || input.endsWith("_") || input.startsWith("0b_") || input.contains("_.")
                || input.contains("_l") || input.contains("0x_") || input.contains("_f")) {
            throw new NumberFormatException(String.format("Error > %s", input));
        }
        if (input.contains("_")) {
            input = input.replaceAll("_", "");
        }
        if (input.endsWith("l")) {
            input = input.substring(0, input.length() - 1);
        }
        if (input.contains(".") || input.endsWith("d")) {
            return Double.parseDouble(input);
        }
        if (input.matches("^[+\\-]?[1-9]\\d*$")) {
            return Long.valueOf(input);
        } else if (input.matches("^[+\\-]?0b[0-1]+")) {
            input = input.replaceFirst("0b", "");
            return Long.valueOf(input, 2);
        } else if (input.matches("[+\\-]?0[x][0-9a-fA-F]+")) {
            input = input.replaceFirst("0x", "");
            return Long.valueOf(input, 16);
        } else if (input.matches("[+\\-]?0[1-70-7]+")) {
            if (input.startsWith("-")) {
                return Long.valueOf(input, 8);
            } else {
                return Long.valueOf(input.substring(1, input.length()), 8);
            }
        }
        throw new NumberFormatException(String.format("Error > %s", input));
    }

    private Character parsingOperation(String input) {
        if (input.isEmpty()) {
            throw new NumberFormatException("wrong expression");
        }
        Character result = input.charAt(0);
        if (!operation.containsKey(result)) {
            throw new IllegalArgumentException(String.format("Error > %s", input));
        }
        return result;
    }

    private void fillOperation() {
        operation.put('+', new Add());
        operation.put('-', new Subtract());
        operation.put('*', new Multipl());
        operation.put('/', new Div());
    }

}
