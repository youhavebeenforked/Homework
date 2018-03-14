package ru.sberbank.homework.your_lastname;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ParallelThreads {
    public static void main(String[] args) throws InterruptedException {
        long millis = System.currentTimeMillis();
        new Thread(new CalculationTask());
        new Thread(new SleepyTask());
        new Thread(new StringsTask());
        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }
}