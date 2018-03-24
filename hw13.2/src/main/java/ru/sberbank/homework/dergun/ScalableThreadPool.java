package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.ThreadPool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ScalableThreadPool implements ThreadPool {
    private final Set<Thread> threads;
    private final BlockingQueue<Runnable> queue;
    private final int MIN_SIZE;
    private final int MAX_SIZE;

    public ScalableThreadPool(int min, int max) {
        this.MAX_SIZE = max;
        this.MIN_SIZE = min;
        threads = new HashSet<>();
        queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < min; i++) {
            threads.add(new Thread(new Worker()));
        }
    }

    public int getCurrentSize() {
        synchronized (threads) {
            return threads.size();
        }
    }

    @Override
    public void start() {
        threads.forEach(Thread::start);
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (threads) {
            queue.add(runnable);
            threads.notify();
        }
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            while (!Thread.interrupted()) {
                Runnable task = null;
                synchronized (threads) {
                    if (!queue.isEmpty()) {
                        task = queue.poll();
                        if (!queue.isEmpty() && threads.size() < MAX_SIZE) {
                            Thread newThread = new Thread(new Worker());
                            newThread.start();
                            threads.add(newThread);
                        }
                    } else if (threads.size() > MIN_SIZE) {
                        threads.remove(Thread.currentThread());
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
                } catch (Exception ignored) {
                }
            }
        }
    }
}
