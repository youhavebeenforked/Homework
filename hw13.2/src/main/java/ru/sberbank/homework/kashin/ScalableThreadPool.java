package ru.sberbank.homework.kashin;

import ru.sberbank.homework.common.ThreadPool;
import ru.sberbank.homework.kashin.exception.ThreadPoolException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ScalableThreadPool implements ThreadPool {
    private final AtomicInteger countThreads;
    private final long maximumTimeOfThreadInactivity;    // миллисекунды, время неактивности потока через которое он убивается
    private final Integer minSize;
    private final Integer maxSize;
    private final ConcurrentLinkedQueue<Runnable> tasks;
    private final AtomicBoolean execute;
    private final AtomicInteger countActiveWorkers = new AtomicInteger(0);
    private final List<ScalableThreadPoolThread> workers;

    public ScalableThreadPool(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        countThreads = new AtomicInteger();
        tasks = new ConcurrentLinkedQueue<>();
        execute = new AtomicBoolean(false);
        workers = new ArrayList<>();
        maximumTimeOfThreadInactivity = 2000;

        Stream.generate(() -> new ScalableThreadPoolThread(tasks))
                .limit(minSize)
                .forEach(e -> {
                    workers.add(e);
                    countThreads.getAndIncrement();
                });
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

        private ScalableThreadPoolThread(ConcurrentLinkedQueue<Runnable> tasks) {
            tasksInPool = tasks;
        }

        @Override
        public void run() {
            try {
                while (execute.get() || !tasksInPool.isEmpty()) {
                    if ((System.currentTimeMillis() - timeVacation) > maximumTimeOfThreadInactivity) {
                        synchronized (countThreads) {
                            if ((System.currentTimeMillis() - timeVacation) > maximumTimeOfThreadInactivity && checkCountThreads())  {
                                countThreads.getAndDecrement();
                                break;
                            }
                        }
                    }
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
                    synchronized (countThreads) {
                        if (countThreads.get() >= maxSize) {
                            break;
                        } else {
                            ScalableThreadPoolThread newThread = new ScalableThreadPoolThread(tasks);
                            workers.add(newThread);
                            countThreads.getAndIncrement();
                            newThread.start();
                        }
                    }
                }
            }
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
