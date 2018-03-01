package ru.sberbank.homework.andreev;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExpressionParser {
    private List<BigDecimal> elements;
    private List<Operation> operation;

    public List<BigDecimal> getElements() {
        return elements;
    }

    public List<Operation> getOperation() {
        return operation;
    }

    ExpressionParser(String expression) {
        String[] splitExpression = expression.split(" ");
        operation = collectOperations(splitExpression);
        elements = collectElements(splitExpression);
    }

    private List<Operation> collectOperations(String[] splitExpression) {
        return Arrays.stream(splitExpression)
                .filter(e -> e.matches(RegExp.OPERATIONS))
                .map(this::getOperationOnSymbol)
                .collect(Collectors.toList());
    }

    private Operation getOperationOnSymbol(String symbol) {
        return Arrays.stream(Operation.values())
                .filter(e -> symbol.matches(e.getRegExpSymbol())).findFirst().get();
    }

    private List<BigDecimal> collectElements(String[] splitExpression) {
        return Arrays.stream(splitExpression)
                .filter(e -> e.matches(RegExp.LITERAL))
                .map(this::convertElement)
                .collect(Collectors.toList());
    }

    private BigDecimal convertElement(String element) {
        String innerElement = prepareElementForConversion(element);
        BigDecimal result = null;
        if (element.matches(RegExp.INT_DECIMAL)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement));
        } else if (element.matches(RegExp.INT_OCTAL)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement.replaceFirst("0", ""), 8));
        } else if (element.matches(RegExp.INT_HEXADECIMAL)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement.replaceFirst("0x", ""), 16));
        } else if (element.matches(RegExp.INT_BINARY)) {
            result = BigDecimal.valueOf(Long.parseLong(innerElement.replaceFirst("0b", ""), 2));
        } else if (element.matches(RegExp.FLOAT)) {
            result = BigDecimal.valueOf(Double.parseDouble(innerElement));
        }
        return result;
    }

    private String prepareElementForConversion(String element) {
        String elementWithoutUnderscore = element.replace("_", "");
        elementWithoutUnderscore = elementWithoutUnderscore.toLowerCase();
        char lastCharacter = elementWithoutUnderscore.charAt(elementWithoutUnderscore.length() - 1);
        if (lastCharacter == 'l') {
            return elementWithoutUnderscore.substring(0, elementWithoutUnderscore.length() - 1);
        }
        return elementWithoutUnderscore;
    }

}
