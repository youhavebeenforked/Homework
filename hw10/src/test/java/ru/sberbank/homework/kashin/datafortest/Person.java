package ru.sberbank.homework.kashin.datafortest;

public interface Person {

    int doItWithoutCache(int i);

    int doItInFile(int i);

    int doItInMemory(int i);

    int doItInMemoryAndEqualsWithoutSecondArgument(int i, int j);
}
