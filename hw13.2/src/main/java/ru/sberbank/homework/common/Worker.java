package ru.sberbank.homework.common;

import java.util.Queue;

public class Worker implements Runnable {
    private final Queue<Runnable> tasks;

    public Worker(Queue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        Runnable task;
        while (true) {
            synchronized (tasks) {
                while (tasks.isEmpty()) {
                    try {
                        tasks.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                task = tasks.poll();
            }
            task.run();
        }
    }
}
