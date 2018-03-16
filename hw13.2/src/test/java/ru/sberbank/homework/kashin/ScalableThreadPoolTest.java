package ru.sberbank.homework.kashin;

import org.junit.Test;
import ru.sberbank.homework.kashin.exception.ThreadPoolException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ScalableThreadPoolTest {
    private final int runnableCount = 10;
    private final ScalableThreadPool threadPool = ScalableThreadPool.getInstance(1, 50);
    private final AtomicInteger count = new AtomicInteger(0);

    @Test
    public void startSuccessfully() {
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        threadPool.start();
        sleep(100);
        assertEquals("Вызовом метода start() FixedThreadPool должен начать работу", 1, count.get());
    }

    @Test
    public void startUnsuccessfully() {
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        sleep(100);
        assertEquals("Без вызова метода start() FixedThreadPool не должен начать работу", 0, count.get());
    }

    @Test
    public void execute() {
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        threadPool.start();
        sleep(100);
        assertEquals("Задача добавленная в FixedThreadPool должна инкрементировать count", 1, count.get());
    }

    @Test
    public void testTerminateWithoutExecutingPendingTask() {
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
        Runnable r = count::getAndIncrement;
        for (int i = 0; i < runnableCount; i++) {
            threadPool.execute(r);
        }
        threadPool.start();
        sleep(100);
        threadPool.terminate();
        assertTrue("ThreadPool должен выполнить все задания после вызова terminate()", runnableCount == count.get());
    }

    private void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {
            throw new ThreadPoolException(e.getCause());
        }
    }


    @Test
    public void test() {
        Runnable r = () -> {
            count.getAndIncrement();
        };
        for (int i = 0; i < 10; i++) {
            threadPool.execute(r);
        }
        threadPool.start();
        sleep(5000);

        for (int i = 0; i < 1000; i++) {
            threadPool.execute(r);
        }
        sleep(10000);

        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}