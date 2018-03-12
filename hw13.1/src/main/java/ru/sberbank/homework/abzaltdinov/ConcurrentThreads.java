package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ConcurrentThreads implements ThreadsExecutor {

    @Override
    public long runThreads(Thread[] threads) {
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long tasksExecTime = System.currentTimeMillis() - start;
        System.out.println("All tasks completed! Time: " + tasksExecTime);
        return tasksExecTime;
    }
}
