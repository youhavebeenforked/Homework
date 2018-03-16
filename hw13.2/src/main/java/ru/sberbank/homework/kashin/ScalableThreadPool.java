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
    private AtomicInteger countThreads;
    private int minSize;
    private int maxSize;
    private ConcurrentLinkedQueue<Runnable> tasks;
    private AtomicBoolean execute;
    private AtomicInteger countActiveWorkers = new AtomicInteger(0);
    private List<ScalableThreadPool.ScalableThreadPoolThread> workers;

    private ScalableThreadPool(int minSize, int maxSize) {
        countThreads = new AtomicInteger(0);
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.tasks = new ConcurrentLinkedQueue<>();
        this.execute = new AtomicBoolean(false);
        this.workers = new ArrayList<>();
        for (int threadIndex = 0; threadIndex < minSize; threadIndex++) {
            ScalableThreadPool.ScalableThreadPoolThread thread = new ScalableThreadPool.ScalableThreadPoolThread(this.tasks);
            this.workers.add(thread);
            System.out.println("I`m new thread " + countThreads.incrementAndGet());
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
        private AtomicBoolean active;
//        private long
        private ConcurrentLinkedQueue<Runnable> tasksInPool;

        ScalableThreadPoolThread(ConcurrentLinkedQueue<Runnable> tasks) {
            this.active = new AtomicBoolean(false);
            this.tasksInPool = tasks;
        }

        @Override
        public void run() {
            try {
                while (execute.get() || !tasksInPool.isEmpty()) {
                    Runnable runnable;
                    while ((runnable =  tasksInPool.poll()) != null) {
                        executeTask(runnable);
                    }
                    TimeUnit.MILLISECONDS.sleep(1);
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
                active.set(true);
                runnable.run();
            } finally {
                active.set(false);
                countActiveWorkers.decrementAndGet();
            }
        }

        private void checkVacantWorkersAndTaskSizeAndAddWorkers(int vacantWorkers, int taskSize) {
            System.out.println(">>>       " + (taskSize - vacantWorkers));
            if (taskSize > vacantWorkers && countThreads.get() < maxSize) {
                int countNewWorkers = taskSize - vacantWorkers;
                for(int i = countNewWorkers; i >= 0; i--) {
                    if (!(countThreads.get() < maxSize)) {
                        break;
                    }
                    ScalableThreadPoolThread newThread = new ScalableThreadPoolThread(new ConcurrentLinkedQueue<>());
                    workers.add(newThread);
                    newThread.start();
                    System.out.println("I`m new thread " + countThreads.incrementAndGet());
                }
            }
        }
    }
}
