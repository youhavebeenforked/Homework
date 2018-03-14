package ru.sberbank.homework.bedarev;

public enum Operation {
    PLUS('+') {
        public Double calc(Double numOne, Double numTwo) {
            return numOne + numTwo;
        }
    },

    MINUS('-') {
        public Double calc(Double numOne, Double numTwo) {
            return numOne - numTwo;
        }
    },

    MULTI('*') {
        public Double calc(Double numOne, Double numTwo) {
            return numOne * numTwo;
        }
    },

    DEV('/'){
        public Double calc(Double numOne, Double numTwo) {
            return numOne / numTwo;
        }
    };

    public char symbol;

    public abstract Double calc(Double numOne, Double numTwo);

    Operation(char symbol) {
        this.symbol = symbol;
    }
}

