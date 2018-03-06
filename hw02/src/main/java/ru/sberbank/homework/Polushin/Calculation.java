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

    @Override
    public String calculate(String userInput) {
        LinkedList postfixExpression;
        try {
            postfixExpression = StringConverter.convertToPostfix(userInput);
           // postfixExpression.forEach(System.out::println); //for debug only
            Stack<Double> temp = new Stack<>();
            if ((postfixExpression.peekFirst() instanceof Operation)) {
                temp.push(result);
            } else {
                result = 0d;
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

            DecimalFormat df = new DecimalFormat("#0.##");
            DecimalFormatSymbols dfs= df.getDecimalFormatSymbols();
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