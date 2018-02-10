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
        CheckValidExpression checker = new CheckValidExpression(userInput);
        if (checker.isValid()) {
            this.userInput = checker.getRefactorString().toString();
            dividingExpression = this.userInput.split("\\s");
            double ans = sum();
            return String.valueOf(ans);
        } else {
            return null;
        }
    }

    private void getNext() {
        if (index == dividingExpression.length) {
            return;
        }
        if (dividingExpression[index].matches("^[0-9]*[.][0-9]+$")) {
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

    private double end() {
        getNext();
        double result;
        switch (operation) {
            case NUMBER:
                result = number;
                getNext();
                break;
            case BRACKET:
                result = sum();
                getNext();
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    private double multiply() {
        double left = end();
        while (true) {
            switch (operation) {
                case MULTIPLICATION:
                    left = left * end();
                    break;
                case DIVISION:
                    left = left / end();
                    break;
                default:
                    return left;
            }
        }
    }

    private double sum() {
        double left = multiply();
        while (true) {
            switch (operation) {
                case SUBTRACTION:
                    left = left - multiply();
                    break;
                case ADDITION:
                    left = left + multiply();
                    break;
                default:
                    return left;
            }
        }
    }
}