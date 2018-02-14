package ru.sberbank.homework.drozdov;

public class ExpressionParser implements Calculator {
    private String userInput;
    private String[] dividingExpression;
    private int index;
    private double number;
    private Operation operation;

    /**
     * Вычисляем значение выражения методом рекурсивного спуска
     *
     * @param userInput команда пользователя.
     * @return ответ типа String
     */
    @Override
    public String calculate(String userInput) {
        this.userInput = userInput;
        index = 0;
        ExpressionChecker checker = new ExpressionChecker(userInput);
        if (checker.isValid()) {
            this.userInput = checker.getRefactorString().toString();
            dividingExpression = this.userInput.split("\\s");
            Expression ans = sum();
            return String.valueOf(ans.evaluate());
        } else {
            return null;
        }
    }

    private void getNext() {
        if (index == dividingExpression.length) {
            return;
        }
        if (dividingExpression[index].matches("^[0-9]*[.][0-9]+$") || (dividingExpression[index].startsWith("-") && dividingExpression[index].length() > 1)) {
            number = Double.parseDouble(dividingExpression[index]);
            index++;
            operation = Operation.NUMBER;
        } else {
            char character = dividingExpression[index].charAt(0);
            switch (character) {
                case '+':
                    operation = Operation.ADDITION;
                    break;
                case '-':
                    operation = Operation.SUBTRACTION;
                    break;
                case '*':
                    operation = Operation.MULTIPLICATION;
                    break;
                case '/':
                    operation = Operation.DIVISION;
                    break;
                case '(':
                    operation = Operation.BRACKET;
                    break;
            }
            index++;
        }
    }

    private Expression end() {
        getNext();
        Expression result;
        switch (operation) {
            case NUMBER:
                result = new Number(number);
                getNext();
                break;
            case BRACKET:
                result = sum();
                getNext();
                break;
            default:
                result = null;
                break;
        }
        return result;
    }

    private Expression multiply() {
        Expression left = end();
        while (true) {
            switch (operation) {
                case MULTIPLICATION:
                    left = new Multiplication(left,end());
                    break;
                case DIVISION:
                    left = new Division(left,end());
                    break;
                default:
                    return left;
            }
        }
    }

    private Expression sum() {
        Expression left = multiply();
        while (true) {
            switch (operation) {
                case SUBTRACTION:
                    left = new Subtraction(left,multiply());
                    break;
                case ADDITION:
                    left = new Addition(left,multiply());
                    break;
                default:
                    return left;
            }
        }
    }
}