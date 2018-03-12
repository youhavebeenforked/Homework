package ru.sberbank.homework.abzaltdinov;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

public class ThreadsBenchmark {
    public static final int NUMBER_OF_REPEATS = 5;

    public static void main(String[] args) {
        ThreadsExecutor te[] = new ThreadsExecutor[]{new ConcurrentThreads(), new SerialThreads()};
        long[] sumTime = new long[te.length];
        for (int i = 0; i < NUMBER_OF_REPEATS; ++i) {
            for (int j = 0; j < te.length; ++j) {
                Thread[] threads = new Thread[]{
                        new Thread(new StringsTask()),
                        new Thread(new CalculationTask()),
                        new Thread(new SleepyTask())
                };
                long execTime = te[j].runThreads(threads);
                sumTime[j] += execTime;
            }
        }
        for (int i = 0; i < te.length; ++i) {
            System.out.println(te[i].getClass().getSimpleName() + ": " + sumTime[i]/NUMBER_OF_REPEATS + " ms");
        }
    }
}
