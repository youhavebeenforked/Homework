package ru.sberbank.homework.andreev.task_03.method_classes;

import java.io.Serializable;

public class ForSerializationSubObject implements WithInternalParameter {
    Double d;
    WithInternalParameterImpl wipi;

    public ForSerializationSubObject(Double d) {
        this.d = d;
        wipi = new WithInternalParameterImpl(d+5);
    }
    public ForSerializationSubObject(){

    }

    @Override
    public Double substract(Double d) {
        return d - wipi.substract(d);
    }

    @Override
    public WithInternalParameter returMyself() {
        return this;
    }
}
