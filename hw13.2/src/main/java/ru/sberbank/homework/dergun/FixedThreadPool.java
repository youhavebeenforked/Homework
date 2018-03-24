package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> queue;
    private final String monitor = "";

    public FixedThreadPool(int countThreads) {
        queue = new LinkedBlockingQueue<Runnable>();
        threads = new Thread[countThreads];

        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        Runnable task = null;
                        synchronized (monitor) {
                            if (!queue.isEmpty()) {
                                task = queue.poll();
                            }
                        }
                        try {
                            if (task != null) {
                                task.run();
                            } else {
                                Thread.currentThread().wait();
                            }
                        } catch (Exception ignored) {
                        }
                    }
                }
            });
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void execute(Runnable runnable) {
        synchronized (threads) {
            queue.add(runnable);
            threads.notify();
        }
    }
}
