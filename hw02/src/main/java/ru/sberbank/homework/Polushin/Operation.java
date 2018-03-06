package ru.sberbank.homework.Polushin;

/**
 * Арифметическая операция.
 * Если использование Enum не ложится на вашу архитектуры, просто продемонстрируйте понимание Enum - предоставьте
 * свой вариант реализации с внутренними полями, описанием, значением. И можете его не использовать.
 * todo: удалить весь этот комментарий, написать свой
 */
public enum Operation {
    MULTIPLY('*') {
        @Override
        public Double calculate(double var1, double var2) {
            return var1 * var2;
        }

        @Override
        public String toString() {
            return this.name() + " " + this.getOperation();
        }
    },
    SUM('+') {
        @Override
        public Double calculate(double var1, double var2) {
            return var1 + var2;
        }

        @Override
        public String toString() {
            return this.name() + " " + this.getOperation();
        }
    },
    DIVISION('/') {
        @Override
        public Double calculate(double var1, double var2) {
            return var1 / var2;
        }

        @Override
        public String toString() {
            return this.name() + " " + this.getOperation();
        }
    },
    SUBTRACT('-') {
        @Override
        public Double calculate(double var1, double var2) {
            return var1 - var2;
        }

        @Override
        public String toString() {
            return this.name() + " " + this.getOperation();
        }
    },
    PARENTHESIS_LEFT('(') {

    },
    PARENTHESIS_RIGHT(')') {

    };

    private char operation;

    Operation(char c) {
        operation = c;
    }

    public char getOperation() {
        return this.operation;
    }

    public Double calculate(double var1, double var2) {
        return null;
    }

    public String toString() {

        return "No such operation";
    }
    //todo: почитай про меня и реализуй.
}
