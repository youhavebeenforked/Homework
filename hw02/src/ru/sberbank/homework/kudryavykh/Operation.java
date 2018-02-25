package ru.sberbank.homework.kudryavykh;

public enum Operation {

    ADD("+"), SUBSTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private String symbol;

    Operation(String translateEnum) {
        this.symbol = translateEnum;
    }

    private String getSymbol() {
        return symbol;
    }

    public static String getResult(Operation objectEnum, double first, double second) {
        String resultOperation;
        switch (objectEnum) {
            case ADD:{
                return resultOperation = Double.toString(first + second);
            }
            case DIVIDE: {
                if(second == 0) {
                    return null;
                }
                else {
                    return resultOperation = Double.toString(first / second);
                }
            }
            case MULTIPLY: {
                return resultOperation = Double.toString(first * second);
            }
            case SUBSTRACT: {
                return resultOperation = Double.toString(first - second);
            }
        }
        return null;
    }

    public static Operation setValue(String operator) {

        switch (operator)
        {
            case "+": {
                return Operation.ADD;
            }
            case "-": {
                return Operation.SUBSTRACT;
            }
            case "/": {
                return Operation.DIVIDE;
            }
            case "*": {
                return Operation.MULTIPLY;
            }
        }
        return null;
    }
}
