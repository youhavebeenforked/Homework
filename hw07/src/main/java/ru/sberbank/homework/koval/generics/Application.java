package ru.sberbank.homework.koval.generics;

import ru.sberbank.homework.koval.generics.test.CollectionUtilsTest;
import ru.sberbank.homework.koval.generics.test.CountMapImplTest;

public class Application {
    public static void main(String[] args) {
        CountMap<String> map = new CountMapImpl<>();
        CountMapImplTest.run();
        CollectionUtilsTest.run();
    }
}
