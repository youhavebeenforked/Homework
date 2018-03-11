package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ConcurrentThreads {
    static int NUMBER_OF_REPEATS = 10;

    public static void main(String[] args) throws InterruptedException {
        long concurrentSumTime = 0;
        long serialSumTime = 0;
        for (int i = 0; i < NUMBER_OF_REPEATS; ++i) {
            long concurrentTasksExecTime = concurrentTasksExecution();
            long serialTasksExecTime = serialTasksExecution();
            concurrentSumTime += concurrentTasksExecTime;
            serialSumTime += serialTasksExecTime;
        }
        System.out.println("Average time of concurrent tasks execution: " + concurrentSumTime / NUMBER_OF_REPEATS);
        System.out.println("Average time of serial tasks execution: " + serialSumTime / NUMBER_OF_REPEATS);
    }

    public static long concurrentTasksExecution() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t[] = new Thread[]{
                new Thread(new CalculationTask()),
                new Thread(new SleepyTask()),
                new Thread(new StringsTask())
        };
        for (int i = 0; i < t.length; ++i) {
            t[i].start();
        }
        for (int i = 0; i < t.length; ++i) {
            t[i].join();
        }
        long tasksExecTime = System.currentTimeMillis() - start;
        System.out.println("All tasks completed! Time: " + tasksExecTime);
        return tasksExecTime;
    }

    public static long serialTasksExecution() {
        long millis = System.currentTimeMillis();
        new StringsTask().run();
        new CalculationTask().run();
        new SleepyTask().run();
        long tasksExecTime = System.currentTimeMillis() - millis;
        System.out.println("All tasks completed! Time: " + tasksExecTime);
        return tasksExecTime;
    }

}
