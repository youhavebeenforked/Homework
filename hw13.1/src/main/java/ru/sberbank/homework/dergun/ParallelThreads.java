package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ParallelThreads {
    public static void main(String[] args) throws InterruptedException {
        long parallel = parallel();
        long nonParallel = nonParallel();
        System.out.println("Параллельное выполнение:" + parallel);
        System.out.println("Линейное выполнение:" + nonParallel);
        System.out.println("Разница: " + (Math.abs(parallel - nonParallel)));
    }

    public static long parallel() throws InterruptedException {
        long millis = System.currentTimeMillis();
        Thread t1 = new Thread(new SleepyTask());
        Thread t2 = new Thread(new CalculationTask());
        Thread t3 = new Thread(new SleepyTask());
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        return System.currentTimeMillis() - millis;
    }

    public static long nonParallel() {
        long millis = System.currentTimeMillis();
        new StringsTask().run();
        new CalculationTask().run();
        new SleepyTask().run();
        return System.currentTimeMillis() - millis;
    }
}
