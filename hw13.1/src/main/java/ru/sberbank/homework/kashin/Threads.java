package ru.sberbank.homework.kashin;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class Threads {
    public static void main(String[] args) throws InterruptedException {
        long millis = System.currentTimeMillis();

        Thread firstThread = new Thread(new StringsTask());
        firstThread.start();

        Thread secondThread = new Thread(new CalculationTask());
        secondThread.start();

        Thread thirdThread = new Thread(new SleepyTask());
        thirdThread.start();

        while (firstThread.isAlive() || secondThread.isAlive() || thirdThread.isAlive()) {
            Thread.sleep(10);
        }
        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }
}
