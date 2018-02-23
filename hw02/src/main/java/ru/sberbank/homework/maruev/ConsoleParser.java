package ru.sberbank.homework.maruev;

import java.security.InvalidParameterException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by Иван on 15.02.2018.
 */
public class ConsoleParser {
    public static String currentSymbol;
    public static char operatorValue;
    public static double operandValue;
    public static Deque<Double> dequeSymbols = new ArrayDeque<>();
    public static Stack<Character> stackOperator = new Stack<>();

    public static void firstParsing(String input) {
        String[] list = input.split(" ");

        if (list.length < 2) {
            throw new InvalidParameterException();
        } else {
            for (int i = 0; i < list.length; i++) {
                if (i % 2 == 0) {
                    if (isOperand(list[i])) {
                        dequeSymbols.addLast(operandValue);
                    } else if (i == 0) {
                        otherParsing(input);
                        break;
                    } else {
                        currentSymbol = String.valueOf(list[i]);
                        throw new NumberFormatException();
                    }
                } else if (isOperator(list[i])) {
                    if (isOperator(list[i])) {
                        stackOperator.push(operatorValue);
                    } else {
                        currentSymbol = String.valueOf(list[i]);
                        throw new NumberFormatException();
                    }
                }
                if (dequeSymbols.size() > 2) {
                    dequeSymbols.removeFirst();
                }
            }
        }
    }

    public static void otherParsing(String input) {
        String[] list = input.split(" ");

        for (int i = 0; i < list.length; i++) {
            if (i % 2 == 0) {
                if (isOperator(list[i])) {
                    stackOperator.add(operatorValue);
                } else {
                    currentSymbol = String.valueOf(list[i]);
                    throw new NumberFormatException();
                }
            } else if (isOperand(list[i])) {
                dequeSymbols.addLast(operandValue);
            } else {
                currentSymbol = String.valueOf(list[i]);
                throw new NumberFormatException();
            }
        }
    }

    public static boolean isOperand(String operand) {
        operand = operand.toLowerCase();
        if (operand.startsWith("_") || operand.endsWith("_")
                || operand.contains("._") || operand.contains("_.") || operand.contains("_l")) {
            return false;
        }

        if (operand.endsWith("l")) {
            operand = operand.replaceAll("l", "");
        }

        if (operand.startsWith("0x") || operand.startsWith("-0x") || operand.startsWith("+0x")) {
            return changeOperand(operand, "0x", "_", 16);
        } else if (operand.startsWith("0b") || operand.startsWith("-0b") || operand.startsWith("+0b")) {
            return changeOperand(operand, "0b", "_", 2);
        } else if ((operand.startsWith("0") || operand.startsWith("-0") || operand.startsWith("+0")) && !operand.contains(".")) {
            return changeOperand(operand, "", "_", 8);
        } else {
            try {
                operand = removeAll(operand, "", "_");
                operandValue = Double.parseDouble(operand);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public static boolean isOperator(String operator) {
        if (operator.equals("+")) {
            operatorValue = operator.charAt(0);
            return true;
        } else if (operator.equals("-")) {
            operatorValue = operator.charAt(0);
            return true;
        } else if (operator.equals("*")) {
            operatorValue = operator.charAt(0);
            return true;
        } else if (operator.equals("/")) {
            operatorValue = operator.charAt(0);
            return true;
        } else {
            currentSymbol = operator;
            throw new NumberFormatException();
        }
    }

    private static String removeAll(String operand, String suffix, String symbol) {
        operand = operand.replace(symbol, "");
        operand = operand.replace(suffix, "");
        return operand;
    }

    private static boolean changeOperand(String operand, String suffix, String symbol, int radix) {
        operand = removeAll(operand, suffix, symbol);
        try {
            operandValue = Integer.parseInt(operand, radix);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
