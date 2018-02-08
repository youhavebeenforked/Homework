package ru.sberbank.homework.kashin.main.util;

import ru.sberbank.homework.kashin.main.model.Expression;
import ru.sberbank.homework.your_lastname.Operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ru.sberbank.homework.your_lastname.Operation.*;

public class Helper {

    public static String readConsole() {
        String userInput = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
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
        String[] simbols = expString.split(" ");
        Expression expression = new Expression();
        expression.setFirst(Double.valueOf(simbols[0]));
        expression.setSecond(Double.valueOf(simbols[2]));
        switch (simbols[1]) {
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
        return Double.toString(result);

    }

}
