package ru.sberbank.homework.andreev;

import ru.sberbank.homework.common.Calculator;

import java.math.BigDecimal;
import java.util.*;

public class CalculatorOnArray implements Calculator {
    private List<BigDecimal> result = new ArrayList<>();
    private List<Operation> inputOperation;

    @Override
    public String calculate(String userInput) {
        ExpressionValidator userInputValidator = new ExpressionValidator(userInput);
        if (!userInputValidator.isExpressionValid()) {
            return "error > wrong expression";
        }
        if (userInputValidator.haveWrongElement()) {
            return "error > " + userInputValidator.getWrongElement();
        }
        ExpressionParser userInputParser = new ExpressionParser(userInput);
        List<BigDecimal> elements = userInputParser.getElements();
        inputOperation = userInputParser.getOperation();
        //user use previous result, or not
        if (elements.size() > inputOperation.size()) {
            result = elements;
        } else if (result.size() == 1) {
            result.addAll(elements);
        } else {
            return "error > wrong expression";
        }
        Arrays.stream(Operation.values())
                .map(Operation::getPriority)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(this::executeOperations);
        return result.get(0).setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
    }

    private void executeOperations(int priority) {
        for (int i = 0; i < inputOperation.size(); ) {
            if (priority == inputOperation.get(i).getPriority()) {
                result.set(i, inputOperation.get(i).getOperation().apply(result.get(i), result.get(i + 1)));
                result.remove(i + 1);
                inputOperation.remove(i);
            } else {
                i++;
            }
        }
    }

}
