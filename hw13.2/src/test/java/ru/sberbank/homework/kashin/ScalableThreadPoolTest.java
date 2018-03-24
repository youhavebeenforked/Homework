package ru.sberbank.homework.kashin;

import org.junit.Test;
import ru.sberbank.homework.kashin.exception.ThreadPoolException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ScalableThreadPoolTest {
    private final int runnableCount = 10;
    private final AtomicInteger count = new AtomicInteger(0);
    private final int numberOfProcessors = Runtime.getRuntime().availableProcessors();

    @Test
    public void startSuccessfully() {
        ScalableThreadPool threadPool = new  ScalableThreadPool(1, numberOfProcessors);
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        threadPool.start();
        sleep(100);
        assertEquals("Вызовом метода start() FixedThreadPool должен начать работу", 1, count.get());
    }

    @Test
    public void startUnsuccessfully() {
        ScalableThreadPool threadPool = new  ScalableThreadPool(1, numberOfProcessors);
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        sleep(100);
        assertEquals("Без вызова метода start() FixedThreadPool не должен начать работу", 0, count.get());
    }

    @Test
    public void execute() {
        ScalableThreadPool threadPool = new  ScalableThreadPool(1, numberOfProcessors);
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        threadPool.start();
        sleep(100);
        assertEquals("Задача добавленная в FixedThreadPool должна инкрементировать count", 1, count.get());
    }

    @Test
    public void testTerminateWithoutExecutingPendingTask() {
        ScalableThreadPool threadPool = new  ScalableThreadPool(1, 1);
        Runnable r = () -> {
            count.getAndIncrement();
            sleep(300);
        };
        for (int i = 0; i < runnableCount; i++) {
            threadPool.execute(r);
        }
        threadPool.start();
        sleep(100);
        threadPool.terminate();
        assertFalse("ThreadPool должен завершаться без ожидания невыполненых заданий", runnableCount == count.get());
    }

    @Test
    public void testTerminateExecutingAllTask() {
        ScalableThreadPool threadPool = new  ScalableThreadPool(1, numberOfProcessors);
        Runnable r = count::getAndIncrement;
        for (int i = 0; i < runnableCount; i++) {
            threadPool.execute(r);
        }
        threadPool.start();
        sleep(300);
        threadPool.terminate();
        assertTrue("ThreadPool должен выполнить все задания после вызова terminate()", runnableCount == count.get());
    }

    @Test
    public void testWithMaximumOneThread() {
        ScalableThreadPool threadPool = new  ScalableThreadPool(1, 1);
        Runnable r = () -> {
            count.getAndIncrement();
            sleep(100);
        };
        for (int i = 0; i < runnableCount; i++) {
            threadPool.execute(r);
        }
        threadPool.start();
        sleep(300);
        threadPool.terminate();
        assertFalse("ThreadPool должен завершаться без ожидания невыполненых заданий", runnableCount == count.get());
    }

    @Test
    public void testWithMaximumNumberOfProcessorsThread() {
        ScalableThreadPool threadPool = new  ScalableThreadPool(2, runnableCount);
        Runnable r = () -> {
            count.getAndIncrement();
            sleep(100);
        };
        for (int i = 0; i < runnableCount; i++) {
            threadPool.execute(r);
        }
        threadPool.start();
        sleep(300);
        threadPool.terminate();
        assertTrue("ThreadPool должен успеть выполнить все задания до завершения", runnableCount == count.get());
    }

    private void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {
            throw new ThreadPoolException(e.getCause());
        }
    }
}
