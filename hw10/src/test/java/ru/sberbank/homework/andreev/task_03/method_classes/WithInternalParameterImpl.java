package ru.sberbank.homework.andreev.task_03.method_classes;

import java.io.Serializable;

public class WithInternalParameterImpl implements WithInternalParameter {
    Double d;

    public WithInternalParameterImpl(Double d) {
        this.d = d;
    }

    public WithInternalParameterImpl() {
    }

    @Override
    public Double substract(Double toSub) {
        return d-toSub;
    }

    @Override
    public WithInternalParameter returMyself() {
        return this;
    }
}
