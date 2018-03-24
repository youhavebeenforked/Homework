package ru.sberbank.homework.dergun.task_03;

import ru.sberbank.homework.dergun.task_03.Annotation.IgnoreArgument;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleTest implements ISimpleTest {
    private int count = 0;

    public void increment() {
        count++;
    }

    @Override
    public void incrementFile() {
        count++;
    }

    @Override
    public String setSomeArgument(@IgnoreArgument int count, String word, @IgnoreArgument int b) {
        this.count++;
        System.out.println(count);
        return word;
    }

    @Override
    public void zip(@IgnoreArgument int count, String word, @IgnoreArgument int b) {
    }

    @Override
    public List<Integer> returnListSize(int size) {
        return IntStream.range(1, size).boxed().collect(Collectors.toList());
    }

    public int getCount() {
        return count;
    }


}
