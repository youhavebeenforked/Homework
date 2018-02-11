package ru.sberbank.homework.solovyov;

public enum Operation {
    PLUS('+'), SUBTRACTION('-'), MULTIPLICATION('*'), DIVISION('/');

    private char sign;

    Operation(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }

    public static Operation getBySign(char sign) {
        Operation[] ops = Operation.values();
        for (Operation op : ops) {
            if (op.getSign() == sign) {
                return op;
            }
        }
        return null;
    }
}
