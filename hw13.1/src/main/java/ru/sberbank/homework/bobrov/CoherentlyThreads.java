package ru.sberbank.homework.bobrov;


import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

/**
 * TODO: comment
 *
 * @author Dmitriy Bobrov (bobrov.dmitriy@gmail.com)
 * @since 12.03.2018
 */


public class CoherentlyThreads {
    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        new StringsTask().run();
        new CalculationTask().run();
        new SleepyTask().run();
        System.out.println("All tasks completed! Time(Coherently): " + (System.currentTimeMillis() - millis));

    }
}
