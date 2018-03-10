package ru.sberbank.homework.andreev;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ParallelThreads {
    public static void main(String[] args) {
        long millisecondsForMultiThread = multiThreadExecution();
        long millisecondsForSingleThread = singleThreadExecution();
        System.out.printf("All task completed! \n Time for single thread: %d \n Time for multi thread: %d",
                millisecondsForSingleThread, millisecondsForMultiThread);

    }

    public static long singleThreadExecution() {
        long millis = System.currentTimeMillis();
        new StringsTask().run();
        new CalculationTask().run();
        new SleepyTask().run();
        return (System.currentTimeMillis() - millis);
    }


    public static long multiThreadExecution() {
        long millis = System.currentTimeMillis();
        Thread stringThread = new Thread(new StringsTask());
        Thread calcTask = new Thread(new CalculationTask());
        Thread sleepTask = new Thread(new SleepyTask());
        stringThread.start();
        calcTask.start();
        sleepTask.start();
        try {
            stringThread.join();
            calcTask.join();
            sleepTask.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (System.currentTimeMillis() - millis);
    }
}
