package ru.sberbank.homework.drozdov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class ExpressionChecker {
    private String expression;
    private StringBuilder refactorString;
    private HashMap<Integer, Character> validLiterals = new HashMap<>();
    private static HashMap<Integer, Character> operations = new HashMap<>();

    ExpressionChecker(String expression) {
        this.expression = expression;
        fillValidLiterals();
    }

    /**
     * проверяем все ли числа в нашем выражении либо литерал целочисленного типа, либо вещественное число
     */
    public boolean isValid() {
        if (checkWhitespacesAndInvalidLiterals()) {
            expression = expression.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");
            String[] arrayOfLiterals = expression.split("\\s+");
            refactorString = new StringBuilder();
            double num;
            for (int i = 0; i < arrayOfLiterals.length; i++) {
                String currentString = arrayOfLiterals[i];
                if (currentString.equals("")) {
                    continue;
                }
                if (currentString.length() == 1 && operations.containsValue(currentString.charAt(0))) {
                    refactorString.append(currentString.charAt(0)).append(" ");
                    continue;
                }
                try {
                    currentString = currentString.replaceAll("_", "");
                    if (currentString.startsWith("0x")) {
                        num = Integer.parseInt(currentString.substring(2), 16);
                    } else if (currentString.startsWith("0b")) {
                        num = Integer.parseInt(currentString.substring(2), 2);
                    } else if (currentString.startsWith("0") && currentString.length() > 1) {
                        num = Integer.parseInt(currentString.substring(1), 8);
                    } else if (currentString.contains(".")) {
                        num = Double.parseDouble(currentString);
                    } else if (currentString.endsWith("L")) {
                        num = Long.parseLong(currentString.substring(0, currentString.length() - 1));
                    } else {
                        num = Integer.parseInt(currentString);
                    }
                } catch (RuntimeException e) {
                    System.err.println("error > wrong expression");
                    return false;
                }
                refactorString.append(String.valueOf(num)).append(" ");
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * проверка на лишние пробелы, наличие лишних символов и правильность расставления скобок
     */
    public boolean checkWhitespacesAndInvalidLiterals() {
        int k = 0;
        int bracketsBalance = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == ' ') {
                k++;
            } else {
                k = 0;
            }
            switch (expression.charAt(i)) {
                case '(':
                    bracketsBalance++;
                    break;
                case ')':
                    bracketsBalance--;
                    break;
            }
            if (bracketsBalance < 0) {
                System.err.println("error > wrong expression");
                return false;
            }
            if (!validLiterals.containsValue(expression.charAt(i))) {
                System.err.println(String.format("%s %s%n","error > ",expression.charAt(i)));
                return false;
            }
            if (k == 2) {
                System.err.println("error > wrong expression");
                return false;
            }
        }
        if (bracketsBalance > 0) {
            System.err.println("error > wrong expression");
            return false;
        }
        return true;
    }

    public StringBuilder getRefactorString() {
        return refactorString;
    }

    private void fillValidLiterals() {
        for (int i = 0; i < 10; i++) {
            validLiterals.put(i, (char) (i + '0'));
        }
        ArrayList<Character> validLiteralsArray = new ArrayList<>(Arrays.asList('x', 'b', '_', 'L', ' ', '.', '(', ')', '+', '-', '/', '*'));
        for (int i = 0; i < validLiteralsArray.size(); i++) {
            validLiterals.put(i + 10, validLiteralsArray.get(i));
        }
        for (int i = 0; i < 6; i++) {
            operations.put(i, validLiteralsArray.get(i + 6));
        }
    }
}
