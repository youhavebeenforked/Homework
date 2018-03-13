package ru.sberbank.homework.Polushin;

import ru.sberbank.homework.common.Calculator;
import ru.sberbank.homework.utils.StringConverter;
import ru.sberbank.homework.utils.UserInputException;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.LinkedList;
import java.util.Stack;

public class Calculation implements Calculator {
    private String output;
    private Double result = 0d;
    private boolean firstOperation = true;

    @Override
    public String calculate(String userInput) {
        LinkedList postfixExpression;
        try {
            postfixExpression = StringConverter.convertToPostfix(userInput);

            Stack<Double> temp = new Stack<>();

            /*
                Продолжение вычеслений или начало нового. И для первого вычисления обязательная форма a_@_b.
            */
            if (postfixExpression.size() % 2 == 0) {
                if (firstOperation == true) {
                    throw new UserInputException();
                }
                temp.push(result);

            } else {
                firstOperation = false;

            }

            while (!postfixExpression.isEmpty()) {
                if (postfixExpression.peekLast() instanceof Double) {
                    temp.push((Double) postfixExpression.pollLast());
                } else {
                    Operation operation = (Operation) postfixExpression.pollLast();
                    double var2 = temp.pop();
                    double var1 = temp.pop();
                    temp.push(operation.calculate(var1, var2));
                }
            }
            result = temp.pop();

            /*
            блок форматирования вывода.
             */
            DecimalFormat df = new DecimalFormat("#0.##");
            DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            df.setDecimalFormatSymbols(dfs);
            df.setRoundingMode(RoundingMode.HALF_UP);

            output = df.format(result);

        } catch (UserInputException e) {
            output = e.getMessage();
        }
        return output;
    }
}