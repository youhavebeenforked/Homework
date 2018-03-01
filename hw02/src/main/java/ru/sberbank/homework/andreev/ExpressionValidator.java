package ru.sberbank.homework.andreev;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExpressionValidator {
    private String expression;
    private Optional<String> wrongElement;

    ExpressionValidator(String expression) {
        this.expression = expression;
        wrongElement = Optional.empty();
    }

    public boolean isExpressionValid() {
        String regexp = "(.+ )?(" + RegExp.OPERATIONS + " .+)+";
        return expression.matches(regexp);
    }

    public boolean haveWrongElement() {
        wrongElement = Arrays.stream(expression.split(" "))
                .filter((s) -> !(s.matches(RegExp.LITERAL) || s.matches(RegExp.OPERATIONS))).findFirst();
        return wrongElement.isPresent();
    }

    public String getWrongElement() {
        return wrongElement.orElse("Checking don't happened or all element is valid");
    }
}
