package ru.sberbank.homework.Polushin;

/**
 * Арифметическая операция.
 * Если использование Enum не ложится на вашу архитектуры, просто продемонстрируйте понимание Enum - предоставьте
 * свой вариант реализации с внутренними полями, описанием, значением. И можете его не использовать.
 * todo: удалить весь этот комментарий, написать свой
 */
public enum Operation {
    MULTIPLY("*") {
        @Override
        public String toString() {
            return super.toString();
        }
    },
    SUM("+") {
        @Override
        public String toString() {
            return super.toString();
        }
    },
    DIVISION("/") {
        @Override
        public String toString() {
            return super.toString();
        }
    },
    SUBTRACT("-") {
        @Override
        public String toString() {
            return super.toString();
        }
    },
    EXIT("QUIT") {
        @Override
        public String toString() {
            return super.toString();
        }
    };

    private String operation;

    private Operation(String s) {
        operation = s;
    }

    public String getOperation() {
        return this.operation;
    }

    public String toString() {

        return "No such operation";
    }
    //todo: почитай про меня и реализуй.
}
