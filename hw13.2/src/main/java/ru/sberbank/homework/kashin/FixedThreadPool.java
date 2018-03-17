package ru.sberbank.homework.kashin;

import ru.sberbank.homework.common.ThreadPool;
import ru.sberbank.homework.kashin.exception.ThreadPoolException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;


public class FixedThreadPool implements ThreadPool {

    private final ConcurrentLinkedQueue<Runnable> tasks;
    private final AtomicBoolean execute;
    private final List<FixedThreadPoolThread> workers;

    public FixedThreadPool(int threadCount) {
        tasks = new ConcurrentLinkedQueue<>();
        execute = new AtomicBoolean(false);
        workers = new ArrayList<>();

        Stream.generate(() -> new FixedThreadPoolThread(tasks))
                .limit(threadCount)
                .forEach(workers::add);
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
        private final ConcurrentLinkedQueue<Runnable> tasksInPool;

        private FixedThreadPoolThread(ConcurrentLinkedQueue<Runnable> tasks) {
            tasksInPool = tasks;
        }

        @Override
        public void run() {
            try {
                while (execute.get() || !tasksInPool.isEmpty()) {
                    Runnable runnable;
                    while (nonNull(runnable = tasksInPool.poll())) {
                        runnable.run();
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (RuntimeException | InterruptedException e) {
                throw new ThreadPoolException(e.getCause());
            }
        }
    }
}
