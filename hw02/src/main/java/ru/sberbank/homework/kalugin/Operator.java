package ru.sberbank.homework.kalugin;

class Operator implements Element<Operation> {
    private Operation operation;

    Operator (String s) {
        if (s.equals("+")) {
            operation = Operation.ADD;
        }
        else if (s.equals("-")) {
            operation = Operation.SUBTRACT;
        }
        else if (s.equals("*")) {
            operation = Operation.MULTIPLY;
        }
        else if (s.equals("/")) {
            operation = Operation.DIVIDE;
        }
        else {
            throw new IllegalArgumentException(s);
        }
    }

    @Override
    public Operation getElement() {
        return operation;
    }
}
