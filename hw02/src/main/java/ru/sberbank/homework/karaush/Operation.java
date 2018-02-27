package ru.sberbank.homework.karaush;

import java.util.HashMap;
import java.util.Map;

/**
 * В енаме храним допустимые операции и их стоковые представления
 */
public enum Operation {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private final String literal;

    private static Map<String, Operation> map = new HashMap<>();

    static {
        for (Operation op : Operation.values()) {
            map.put(op.literal, op);
        }
    }

    public static Operation getOperationByString(String op) {
        if (!map.containsKey(op)) {
            throw new IllegalArgumentException("error > " + op);
        }
        return map.get(op);
    }

    Operation(String s) {

        literal = s;
    }
}
