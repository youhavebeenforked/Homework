package ru.sberbank.homework.Polushin;

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
    POWER('^') {
        @Override
        public Double calculate(double var1, double var2) {
            return Math.pow(var1, var2);
        }

        @Override
        public String toString() {
            return this.name() + " " + this.getOperation();
        }
    },
    PARENTHESIS_LEFT('(') {
        @Override
        public String toString() {
            return this.name() + " " + this.getOperation();
        }
    },
    PARENTHESIS_RIGHT(')') {
        @Override
        public String toString() {
            return this.name() + " " + this.getOperation();
        }
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

}
