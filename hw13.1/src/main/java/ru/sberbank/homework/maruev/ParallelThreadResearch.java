package ru.sberbank.homework.maruev;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

/**
 * Created by Иван on 11.03.2018.
 */
public class ParallelThreadResearch {

    public static void main(String[] args) throws InterruptedException{
        Thread stringsThread = new Thread(new StringsTask());
        Thread sleepyThread = new Thread(new SleepyTask());
        Thread calculationThread = new Thread(new CalculationTask());

        long millis = System.currentTimeMillis();

        stringsThread.start();
        sleepyThread.start();
        calculationThread.start();

        stringsThread.join();
        sleepyThread.join();
        calculationThread.join();

        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }
}
