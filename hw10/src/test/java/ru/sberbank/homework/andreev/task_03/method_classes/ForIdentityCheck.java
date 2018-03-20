package ru.sberbank.homework.andreev.task_03.method_classes;

import ru.sberbank.homework.andreev.task_03.annotation.Cache;
import ru.sberbank.homework.andreev.task_03.annotation.Empty;

public interface  ForIdentityCheck {

    @Cache(identityBy = {Integer.class, String.class, Boolean.class})
    String methodWithoutEmptyMark(String y, Integer i, String y2, String y3, boolean f);

    @Cache(identityBy = {Empty.class, Integer.class, String.class, Empty.class,  boolean.class})
    String methodWithEmptyMark(String y, Integer i, String y2, String y3, boolean f);
}
