package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.your_lastname.Operation;

public class CalculatorImpl implements ru.sberbank.homework.common.Calculator {

    private String currentState;

    @Override
    public String calculate(String userInput) {
        String[] splittedUserInput = userInput.split(" ");
        switch (splittedUserInput.length) {
            case 3:
                double firstOperand;
                if (splittedUserInput[0].contains("."))
                    firstOperand = Parser.parseLong(splittedUserInput[0]);
                else
                    firstOperand = Parser.parseDouble(splittedUserInput[0]);
                Operation operation = Parser.parseOperation(splittedUserInput[1]);
                break;
            case 2:
                if (currentState == null) {
                    ErrorPrinter.printWrongExpression();
                }

                break;
            default:
                ErrorPrinter.printWrongExpression();
        }
        return null;
    }
}
