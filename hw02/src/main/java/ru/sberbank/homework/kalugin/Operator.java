package ru.sberbank.homework.kalugin;

class Operator implements Element<Operation> {
    private Operation operation;

    Operator (char c) {
        if (c == '+') {
            operation = Operation.ADD;
        }
        else if (c == '-') {
            operation = Operation.SUBTRACT;
        }
        else if (c == '*') {
            operation = Operation.MULTIPLY;
        }
        else if (c == '/') {
            operation = Operation.DIVIDE;
        }
        else {
            throw new IllegalArgumentException("Assignment of unsupported operator: " + c);
        }
    }

    @Override
    public Operation getElement() {
        return operation;
    }
}
