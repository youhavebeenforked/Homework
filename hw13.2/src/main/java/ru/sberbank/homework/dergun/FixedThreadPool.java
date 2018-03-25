package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.ThreadPool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final Queue<Runnable> queue;
    private static Logger log = Logger.getLogger(FixedThreadPool.class.getName());

    public FixedThreadPool(int countThreads) {
        if (countThreads < 1) {
            throw new IllegalArgumentException("Incorrect count threads");
        }
        queue = new LinkedList<>();
        threads = new Thread[countThreads];

        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(() -> {
                while (!Thread.interrupted()) {
                    Runnable task = null;
                    synchronized (threads) {
                        if (!queue.isEmpty()) {
                            task = queue.poll();
                        }
                    }
                    try {
                        if (task != null) {
                            task.run();
                        } else {
                            synchronized (threads) {
                                threads.wait();
                            }
                        }
                    } catch (Exception e) {
                        log.warning(e.getMessage());
                    }
                }
            });
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (threads) {
            queue.add(runnable);
            threads.notify();
        }
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
