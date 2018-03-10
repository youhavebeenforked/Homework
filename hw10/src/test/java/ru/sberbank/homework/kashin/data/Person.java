package ru.sberbank.homework.kashin.data;

import ru.sberbank.homework.kashin.task_03.annotations.Cache;

public interface Person {

    @Cache(typeStorage = true, name = "qwerty")
    int doItInFile(int i);

    @Cache
    int doItInMemory(int i);
}
