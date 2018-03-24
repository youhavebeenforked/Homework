package ru.sberbank.homework.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScalableThreadPool implements ThreadPool {

    private final int MIN_THREAD_COUNT;
    private final int MAX_THREAD_COUNT;
    private final Queue<Runnable> tasks;
    private final List<Thread> workers;
    private final Thread watcher;

    public ScalableThreadPool(int minThreadCount, int maxThreadCount) {
        this.tasks = new LinkedList<>();
        MIN_THREAD_COUNT = minThreadCount;
        MAX_THREAD_COUNT = maxThreadCount;
        List<Thread> minThreadList = IntStream.range(0, MIN_THREAD_COUNT).boxed()
                .map(e -> new Thread(new Worker(tasks)))
                .collect(Collectors.toList());
        this.workers = new ArrayList<>(maxThreadCount);
        workers.addAll(minThreadList);
        this.watcher =  new Thread(this::watchAndControl);
    }

    @Override
    public void start() {
        workers.forEach(Thread::start);
        watcher.start();
    }

    private void watchAndControl() {
        while (true) {
            synchronized (tasks) {
                if (tasks.isEmpty() && workers.size() > MIN_THREAD_COUNT) {
                    Thread thread = workers.get(workers.size() - 1);
                    workers.remove(thread);
                    thread.interrupt();
                }
                if (!tasks.isEmpty() && workers.size() < MAX_THREAD_COUNT) {
                    Thread newThread = new Thread(new Worker(tasks));
                    workers.add(newThread);
                    newThread.start();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.add(runnable);
            tasks.notifyAll();
        }
    }

    public void shutdown() {
        workers.forEach(Thread::interrupt);
        watcher.interrupt();
    }

}
