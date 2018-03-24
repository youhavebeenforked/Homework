package ru.sberbank.homework.bobrov;

import ru.sberbank.homework.bobrov.oper.Operation;
import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


/**
 * Improved calculator.
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 08.02.2018
 */


public class CalcImpl implements Calculator {
    private String result;
    private Parser parser = new Parser();
    private Map<Character, Operation> operation = parser.getOperation();

    @Override
    public String calculate(String userInput) {
        if (operation.get(userInput.charAt(0)) != null && userInput.substring(1, 2).equals(" ") && result == null) {
            return "Error > wrong expression";
        } else if (operation.get(userInput.charAt(0)) != null && userInput.substring(1, 2).equals(" ") && result != null) {
            try {
                result = inputProcessing(String.format("%s %s", result, userInput));
            } catch (Exception e) {
                return e.getMessage();
            }
        } else {
            try {
                result = inputProcessing(userInput);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return result;
    }

    private String inputProcessing(String input) {
        parser.parsingString(input);
        double first = parser.getFirst();
        double second = parser.getSecond();
        Character symbolOperation = parser.getSymbolOperation();
        Double tmpResult = Double.parseDouble(operation.get(symbolOperation).calculate(first, second));
        return validateAnswer(tmpResult);

    }

    private String validateAnswer(Double answer) {
        if (answer % 1 == 0) {
            Long correctAnswer = (Math.round((answer)));
            return correctAnswer.toString();
        } else {
            answer = new BigDecimal(answer).setScale(2, RoundingMode.HALF_UP).doubleValue();
            return answer.toString();
        }
    }

}
