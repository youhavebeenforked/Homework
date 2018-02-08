package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.your_lastname.Operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Objects.nonNull;
import static ru.sberbank.homework.your_lastname.Operation.*;

public class Helper {
    private static Double preResult;

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
        Expression expression = new Expression();

        if (expString.startsWith("+") || expString.startsWith("-") || expString.startsWith("*") || expString.startsWith("/")) {
            if (nonNull(preResult)) {
                expString = preResult.toString() + " " + expString;
            } else {
                print("Вы ввели неверную команду");
                throw new RuntimeException("Неверная команда");
            }
        }
        String[] character = expString.split(" ");
        double firstNum = checkNotation(character[0]);
        double secondNum = checkNotation(character[2]);
        expression.setFirst(firstNum);
        expression.setSecond(secondNum);
        switch (character[1]) {
            case "+": {
                expression.setOperator(ADDITION);
                break;
            }
            case "-": {
                expression.setOperator(SUBTRATION);
                break;
            }
            case "*": {
                expression.setOperator(MULTIPLICATIOM);
                break;
            }
            case "/": {
                expression.setOperator(DIVISION);
                break;
            }
            default: {
                throw new RuntimeException("Неверное выражение");
            }

        }
        return expression;
    }

    public static String calculateHelper(Expression expression) {
        double first = expression.getFirst();
        double second = expression.getSecond();
        Operation operation = expression.getOperator();
        double result = 0;
        switch (operation) {
            case ADDITION: {
                result = first + second;
                break;
            }
            case SUBTRATION: {
                result = first - second;
                break;
            }
            case MULTIPLICATIOM: {
                result = first * second;
                break;
            }
            case DIVISION: {
                result = first / second;
                break;
            }

        }
        preResult = result;
        return Double.toString(result);

    }

    public static Double checkNotation(String number) {
        String result = number;

        if (number.contains("b")) {
            result = String.valueOf(Integer.parseInt(number.substring(2, number.length()), 2));
        } else if (number.startsWith("0")) {
            result = String.valueOf(Integer.parseInt(number.substring(1, number.length()), 8));
        } else if (number.contains("x") || number.contains("X")) {
            result = String.valueOf(Integer.parseInt(number.substring(2, number.length()), 16));
        }
        return Double.valueOf(result);
    }

}
