package ru.sberbank.homework.maruev;

/**
 * Created by Иван on 15.02.2018.
 */

import ru.sberbank.homework.common.Calculator;
import ru.sberbank.homework.maruev.operations.*;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;

public class HardCalculator implements Calculator {
    public static double result;
    private final static String MESSAGE_ERROR = "error > ";
    private final static String DIVISION_ERROR = "Division by zero";
    private final static String WR_EXPRESSION = "wrong expression";
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static Operator operator;

    @Override
    public String calculate(String userInput) {
        try {
            ConsoleParser.firstParsing(userInput);

            if (ConsoleParser.stackOperator.peek().equals(Operation.MULTIPLY.getOperation())) {
                operator = new MultiplyOperator();
            } else if (ConsoleParser.stackOperator.peek().equals(Operation.SUM.getOperation())) {
                operator = new SumOperator();
            } else if (ConsoleParser.stackOperator.peek().equals(Operation.SUBSTRACT.getOperation())) {
                operator = new SubstractOperator();
            } else if (ConsoleParser.stackOperator.peek().equals(Operation.DIVISION.getOperation())) {
                operator = new DivisionOperator();
            } else {
                throw new NumberFormatException();
            }
            return String.valueOf(decimalFormat.format(operator.getResult()));
        } catch (NumberFormatException e) {
            return MESSAGE_ERROR + ConsoleParser.currentSymbol;
        } catch (NullPointerException e) {
            return MESSAGE_ERROR + DIVISION_ERROR;
        } catch (InvalidParameterException e) {
            return MESSAGE_ERROR + WR_EXPRESSION;
        }
    }
}
