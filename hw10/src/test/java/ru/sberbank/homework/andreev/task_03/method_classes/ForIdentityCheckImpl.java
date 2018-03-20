package ru.sberbank.homework.andreev.task_03.method_classes;

public class ForIdentityCheckImpl implements ForIdentityCheck {
    @Override
    public String methodWithoutEmptyMark(String y, Integer i, String y2, String y3, boolean f) {
        return y+i+y2+y3+f;
    }

    @Override
    public String methodWithEmptyMark(String y, Integer i, String y2, String y3, boolean f) {
        return y+i+y2+y3+f;
    }
}
