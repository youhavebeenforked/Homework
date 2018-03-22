package ru.sberbank.homework.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class FixedThreadPool implements ThreadPool {
    private final Queue<Runnable> tasks;
    private final List<Thread> workers;

    public FixedThreadPool(int amount) {
        this.tasks = new LinkedList<>();
        this.workers = IntStream.range(0, amount).boxed()
                .map(e -> new Thread(new Worker(tasks)))
                .collect(Collectors.toList());
    }

    @Override
    public void start() {
        workers.forEach(Thread::start);
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
    }
}
