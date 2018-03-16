package ru.sberbank.homework.kashin;

import ru.sberbank.homework.common.ThreadPool;
import ru.sberbank.homework.kashin.exception.ThreadPoolException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Objects.nonNull;


public class FixedThreadPool implements ThreadPool {

    private ConcurrentLinkedQueue<Runnable> tasks;
    private AtomicBoolean execute;
    private List<FixedThreadPoolThread> workers;

    private FixedThreadPool(int threadCount) {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.execute = new AtomicBoolean(false);
        this.workers = new ArrayList<>();
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            FixedThreadPoolThread thread = new FixedThreadPoolThread(this.tasks);
            this.workers.add(thread);
        }
    }

    public static FixedThreadPool getInstance(int threadCount) {
        return new FixedThreadPool(threadCount);
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

    private class FixedThreadPoolThread extends Thread {
        private ConcurrentLinkedQueue<Runnable> tasksInPool;

        FixedThreadPoolThread(ConcurrentLinkedQueue<Runnable> tasks) {
            this.tasksInPool = tasks;
        }

        @Override
        public void run() {
            try {
                while (execute.get() || !tasksInPool.isEmpty()) {
                    Runnable runnable;
                    while (nonNull(runnable = tasksInPool.poll())) {
                        runnable.run();
                    }
                    TimeUnit.MILLISECONDS.sleep(1);
                }
            } catch (RuntimeException | InterruptedException e) {
                throw new ThreadPoolException(e.getCause());
            }
        }
    }
}
