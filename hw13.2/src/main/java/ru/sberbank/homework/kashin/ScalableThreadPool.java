package ru.sberbank.homework.kashin;

import ru.sberbank.homework.common.ThreadPool;
import ru.sberbank.homework.kashin.exception.ThreadPoolException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ScalableThreadPool implements ThreadPool {
    private final AtomicInteger countThreads;
    private final long maximumTimeOfThreadInactivity;    // миллисекунды, время неактивности потока через которое он убивается
    private final Integer minSize;
    private final Integer maxSize;
    private final ConcurrentLinkedQueue<Runnable> tasks;
    private final AtomicBoolean execute;
    private final AtomicInteger countActiveWorkers = new AtomicInteger(0);
    private final List<ScalableThreadPool.ScalableThreadPoolThread> workers;

    private ScalableThreadPool(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        countThreads = new AtomicInteger(0);
        tasks = new ConcurrentLinkedQueue<>();
        execute = new AtomicBoolean(false);
        workers = new ArrayList<>();
        maximumTimeOfThreadInactivity = 2000;
        for (int threadIndex = 0; threadIndex < minSize; threadIndex++) {
            ScalableThreadPool.ScalableThreadPoolThread thread = new ScalableThreadPool.ScalableThreadPoolThread(tasks);
            workers.add(thread);
        }
    }

    public static ScalableThreadPool getInstance(int minSize, int maxSize) {
        return new ScalableThreadPool(minSize, maxSize);
    }

    @Override
    public void start() {
        execute.set(true);
        workers.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);
    }

    public void terminate() {
        execute.set(false);
        tasks.clear();
    }

    private class ScalableThreadPoolThread extends Thread {
        private long timeVacation = System.currentTimeMillis();
        private final ConcurrentLinkedQueue<Runnable> tasksInPool;

        ScalableThreadPoolThread(ConcurrentLinkedQueue<Runnable> tasks) {
            this.tasksInPool = tasks;
        }

        @Override
        public void run() {
            try {
                while (execute.get() || !tasksInPool.isEmpty()) {
                    if (checkVacantWorkersAndTaskSizeAndKillWorkers()) break;
                    Runnable runnable;
                    while ((runnable = tasksInPool.poll()) != null) {
                        executeTask(runnable);
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (RuntimeException | InterruptedException e) {
                throw new ThreadPoolException(e.getCause());
            }
        }

        private void executeTask(Runnable runnable) {
            int vacantWorkers = workers.size() - countActiveWorkers.get();
            int taskSize = tasksInPool.size();
            checkVacantWorkersAndTaskSizeAndAddWorkers(vacantWorkers, taskSize);
            try {
                countActiveWorkers.incrementAndGet();
                runnable.run();
            } finally {
                countActiveWorkers.decrementAndGet();
                timeVacation = System.currentTimeMillis();
            }
        }

        private void checkVacantWorkersAndTaskSizeAndAddWorkers(int vacantWorkers, int taskSize) {
            if (taskSize > vacantWorkers && countThreads.get() < maxSize) {
                int countNewWorkers = taskSize - vacantWorkers;
                for (int i = countNewWorkers; i >= 0; i--) {
                    if (countThreads.get() >= maxSize) {
                        break;
                    }
                    ScalableThreadPoolThread newThread = new ScalableThreadPoolThread(new ConcurrentLinkedQueue<>());
                    workers.add(newThread);
                    newThread.start();
                }
            }
        }

        private boolean checkVacantWorkersAndTaskSizeAndKillWorkers() {
            return (System.currentTimeMillis() - timeVacation) > maximumTimeOfThreadInactivity && checkCountThreads();
        }

        private synchronized boolean checkCountThreads() {
            if (countThreads.get() > minSize) {
                countThreads.decrementAndGet();
                return true;
            } else {
                return false;
            }
        }
    }
}
