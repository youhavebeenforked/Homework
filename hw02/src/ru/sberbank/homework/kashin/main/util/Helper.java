package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.model.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Objects.nonNull;

public class Helper {
    private static Double preResult;

    public static void setPreResult(Double preResult) {
        Helper.preResult = preResult;
    }

    public static String readConsole() {
        String userInput = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static Expression parser(String expString) {
        if (expString.startsWith("+") || expString.startsWith("-") || expString.startsWith("*") || expString.startsWith("/")) {
            if (nonNull(preResult)) {
                expString = preResult.toString() + " " + expString;
            } else {
                throw new RuntimeException("Чтобы продолжить вычисление, нужно ввести команду в формате: [оператор][пробел][число]. Или quit для выхода");
            }
        }
        String[] character = expString.split(" ");
        double first;
        double second;
        if (nonNull(character[0]) && nonNull(character[1]) && nonNull(character[2])) {
            first = checkNotation(character[0]);
            second = checkNotation(character[2]);
        } else {
            throw new RuntimeException("Неверное выражение");
        }
        Expression expression = Factory.getExpression(character[1]);
        expression.setFirst(first);
        expression.setSecond(second);
        return expression;
    }

    public static Double checkNotation(String number) {
        String result = number;

        if (number.contains("b")) {
            result = String.valueOf(Integer.parseInt(number.substring(2, number.length()), 2));
        } else if (number.startsWith("0")) {
            if (Integer.valueOf(number) == 0) {
                throw new RuntimeException("Делить на 0 нельзя!");
            }
            result = String.valueOf(Integer.parseInt(number.substring(1, number.length()), 8));
        } else if (number.contains("x") || number.contains("X")) {
            result = String.valueOf(Integer.parseInt(number.substring(2, number.length()), 16));
        }
        return Double.valueOf(result);
    }

}
