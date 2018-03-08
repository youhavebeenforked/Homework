package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class NonParallelThreads {
    public static void main(String[] args) throws InterruptedException {
        long nonParallel = nonParallel();
        System.out.println("Линейное выполнение:" + nonParallel);
    }

    public static long nonParallel() {
        long millis = System.currentTimeMillis();
        new StringsTask().run();
        new CalculationTask().run();
        new SleepyTask().run();
        return System.currentTimeMillis() - millis;
    }
}
