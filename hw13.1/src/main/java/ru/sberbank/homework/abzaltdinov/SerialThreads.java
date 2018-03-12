package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class SerialThreads implements ThreadsExecutor {

    @Override
    public long runThreads(Thread[] threads) {
        long millis = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        long tasksExecTime = System.currentTimeMillis() - millis;
        System.out.println("All tasks completed! Time: " + tasksExecTime);
        return tasksExecTime;
    }

}
