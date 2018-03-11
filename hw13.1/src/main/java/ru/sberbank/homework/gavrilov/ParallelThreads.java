package ru.sberbank.homework.gavrilov;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ParallelThreads {
    public static void main(String[] args) throws InterruptedException {
        Thread[] treads = new Thread[3];
        treads[0] = new Thread(new StringsTask());
        treads[1] = new Thread(new CalculationTask());
        treads[2] = new Thread(new SleepyTask());

        long millis = System.currentTimeMillis();
        for (Thread t : treads) {
            t.start();
        }
        for (Thread t : treads) {
            t.join();
        }
        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }
}
