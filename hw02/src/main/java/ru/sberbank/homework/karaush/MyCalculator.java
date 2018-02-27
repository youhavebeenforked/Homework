package ru.sberbank.homework.karaush;

public class MyCalculator implements Calculator {

    private static final double EPSILON = 1e-8;
    private CalculatorImpl calculator = new CalculatorImpl();
    private double currentVal;
    private int counter = 0;

    public String calculate(String userInput) {
        String[] lexemes = userInput.trim().split(" ");
        counter++;
        Operation op;
        double left, right;
        try {
            Validator.validate(userInput);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        if (lexemes.length == 2) {
            if (counter == 1)
                return "error > wrong expression";
            try {
                left = currentVal;
                right = Validator.stringToDecimal(lexemes[1]);
                op = Operation.getOperationByString(lexemes[0]);
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        } else {
            try {
                left = Validator.stringToDecimal(lexemes[0]);
                right = Validator.stringToDecimal(lexemes[2]);
                op = Operation.getOperationByString(lexemes[1]);
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
        switch (op) {
            case ADDITION:
                currentVal = calculator.add(left, right);
                break;
            case SUBTRACTION:
                currentVal = calculator.subtract(left, right);
                break;
            case MULTIPLICATION:
                currentVal = calculator.multiply(left, right);
                break;
            case DIVISION:
                currentVal = calculator.divide(left, right);
                break;
        }

        return format(currentVal);
    }

    private String format(double currentVal) {
        int intVal;
        if (Math.abs(Math.floor(currentVal) - currentVal) < EPSILON) {
            intVal = (int) currentVal;
        } else {
            return String.valueOf(Double.parseDouble(String.format("%.2f", currentVal)));
        }
        return String.valueOf(intVal);
    }
}
