package ru.sberbank.homework.koval.generics.test;

import java.util.ArrayList;
import java.util.List;

public class Assert {
    public static <E> void assertEquals(String message, List<? extends E> actual, List<? extends E> expected) {
        if (actual == null && expected == null) {
            return;
        }

        if (actual == null || expected == null) {
            throw new RuntimeException(message);
        }

        List<E> expectedCopy = new ArrayList<>(expected);

        for (E elem : actual) {
            if (!expectedCopy.remove(elem)) {
                throw new RuntimeException(message);
            }
        }

        if (!expectedCopy.isEmpty()) {
            throw new RuntimeException();
        }
    }

    public static <E> void assertEquals(String message, int actual, int expected) {
        if (actual != expected) {
            throw new RuntimeException(message);
        }
    }

    public static <E> void assertEquals(String message, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new RuntimeException(message);
        }
    }
}
