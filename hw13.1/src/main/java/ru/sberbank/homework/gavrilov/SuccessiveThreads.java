package ru.sberbank.homework.gavrilov;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class SuccessiveThreads {
    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        new StringsTask().run();
        new CalculationTask().run();
        new SleepyTask().run();
        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }
}
