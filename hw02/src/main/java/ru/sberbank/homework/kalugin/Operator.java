package ru.sberbank.homework.kalugin;

class Operator implements Element<Operation> {
    private Operation operation;

    Operator (char c) {
        switch(c) {
            case '+':
                operation = Operation.ADD;
                break;
            case '-':
                operation = Operation.SUBTRACT;
                break;
            case '*':
                operation = Operation.MULTIPLY;
                break;
            case '/':
                operation = Operation.DIVIDE;
                break;
            default:
                throw new IllegalArgumentException("Assignment of unsupported operator: " + c);
        }
    }

    @Override
    public Operation getElement() {
        return operation;
    }
}
