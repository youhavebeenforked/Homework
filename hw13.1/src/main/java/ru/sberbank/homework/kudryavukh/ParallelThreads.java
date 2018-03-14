package ru.sberbank.homework.kudryavukh;

import ru.sberbank.homework.common.tasks.CalculationTask;
import ru.sberbank.homework.common.tasks.SleepyTask;
import ru.sberbank.homework.common.tasks.StringsTask;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParallelThreads {
    public static void main(String[] args) throws InterruptedException {
        long millis = System.currentTimeMillis();
        Thread threadStringTask = new Thread(new StringsTask());
        Thread threadCalculationTask = new Thread(new CalculationTask());
        Thread threadSleepyTask = new Thread(new SleepyTask());
        System.out.println("Выполнить параллено? (yes/no)");
        Scanner in = new Scanner(System.in);
        String s = in.next();
        Pattern p = Pattern.compile("(y|Y)(e|E)?(s|S)");
        Matcher m = p.matcher(s);
        if(m.matches()) {
            threadStringTask.start();
            threadCalculationTask.start();
            threadSleepyTask.start();
            threadStringTask.join();
            threadCalculationTask.join();
            threadSleepyTask.join();
        }
        else {
            threadStringTask.start();
            threadStringTask.join();
            threadCalculationTask.start();
            threadCalculationTask.join();
            threadSleepyTask.start();
            threadSleepyTask.join();
        }
//        Thread daemonThread = new Thread(new DaemonTime());
//        daemonThread.setDaemon(true);
//        daemonThread.start();
        //TimeUnit.SECONDS.sleep(30);
        System.out.println("All tasks completed! Time: " + (System.currentTimeMillis() - millis));
    }
}

class DaemonTime implements Runnable {

    @Override
    public void run() {
        for (; ; ) {
            System.out.println("All tasks completed! Time: ");
        }
    }
}
