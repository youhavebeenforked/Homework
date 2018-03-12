package ru.sberbank.homework.bobrov;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

import java.util.ArrayList;
import java.util.List;

public class ParallelThreads {
    public static void main(String[] args) throws InterruptedException {
        long millis = System.currentTimeMillis();
        List<Thread> tasks = new ArrayList<>();
        tasks.add(new Thread(new CalculationTask()));
        tasks.add(new Thread(new SleepyTask()));
        tasks.add(new Thread(new StringsTask()));

        for (Thread task : tasks) {
            task.start();
        }

        for (Thread task : tasks) {
            task.join();
        }
        System.out.println("All tasks completed! Time(Parallel): " + (System.currentTimeMillis() - millis));
    }
}
