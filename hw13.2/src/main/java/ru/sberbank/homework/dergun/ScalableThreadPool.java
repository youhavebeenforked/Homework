package ru.sberbank.homework.dergun;

import ru.sberbank.homework.common.ThreadPool;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ScalableThreadPool implements ThreadPool {
    private static Logger log = Logger.getLogger(FixedThreadPool.class.getName());
    private final Set<Thread> threads;
    private final Queue<Runnable> queue;
    private final int MIN_SIZE;
    private final int MAX_SIZE;
    private final AtomicInteger numberBusyThreads = new AtomicInteger(0);

    public ScalableThreadPool(int min, int max) {
        this.MAX_SIZE = max;
        this.MIN_SIZE = min;
        threads = new HashSet<>();
        queue = new LinkedList<>();
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
                        if (!queue.isEmpty()
                                && threads.size() < MAX_SIZE
                                && numberBusyThreads.intValue() + 1 == threads.size()) {
                            Thread newThread = new Thread(new Worker());
                            newThread.start();
                            threads.add(newThread);
                        }
                    } else if (threads.size() > MIN_SIZE) {
                        threads.remove(Thread.currentThread());
                    }

                }

                if (task != null) {
                    numberBusyThreads.incrementAndGet();
                    try {
                        task.run();
                    } catch (Exception e) {
                        log.warning(e.getMessage());
                    }
                    numberBusyThreads.decrementAndGet();
                    continue;
                }

                synchronized (threads) {
                    try {
                        threads.wait();
                    } catch (InterruptedException e) {
                        log.warning(e.getMessage());
                    }
                }


            }
        }
    }
}
