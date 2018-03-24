package ru.sberbank.homework.andreev.task_03.method_classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MethodClass implements IMethodClass {
    @Override
    public List<String> createStringList(int count, String str) {
        ArrayList<String> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add("str" + i);
        }
        return result;
    }

    @Override
    public Integer sum(Integer i1, Integer i2) {
        return i1 + i2;
    }

    @Override
    public BigDecimal discriminant(Double a, Double b, Double c) {
        return BigDecimal.valueOf(b*b-4*a*c);
    }

    @Override
    public MethodClass mc() {
        return this;
    }

    @Override
    public List<Integer> collectSubnumber(Integer i) {
        return IntStream.range(0, i).boxed().collect(Collectors.toList());
    }
}
