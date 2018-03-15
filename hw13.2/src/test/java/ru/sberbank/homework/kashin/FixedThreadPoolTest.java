package ru.sberbank.homework.kashin;

import org.junit.Assert;
import org.junit.Test;
import ru.sberbank.homework.kashin.exception.ThreadPoolException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedThreadPoolTest {
    private final int runnableCount = Runtime.getRuntime().availableProcessors();
    private final FixedThreadPool threadPool = FixedThreadPool.getInstance(runnableCount);
    private final AtomicInteger count = new AtomicInteger(0);

    @Test
    public void startSuccessfully() {
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        threadPool.start();
        sleep(100);
        Assert.assertEquals("Вызовом метода start() FixedThreadPool должен начать работу", 1, count.get());
    }

    @Test
    public void startUnsuccessfully() {
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        sleep(100);
        Assert.assertEquals("Без вызова метода start() FixedThreadPool не должен начать работу", 0, count.get());
    }

    @Test
    public void execute() {
        Runnable r = count::getAndIncrement;
        threadPool.execute(r);
        threadPool.start();
        sleep(100);
        Assert.assertEquals("Задача добавленная в FixedThreadPool должна инкрементировать count", 1, count.get());
    }

    @Test
    public void testTerminateWithoutExecutingPendingTask() {
        FixedThreadPool threadPoolWithHalfRunnableCount = FixedThreadPool.getInstance(runnableCount / 2);
        Runnable r = () -> {
            count.getAndIncrement();
            sleep(300);
        };
        for (int i = 0; i < runnableCount; i++) {
            threadPoolWithHalfRunnableCount.execute(r);
        }
        threadPoolWithHalfRunnableCount.start();
        sleep(100);
        threadPoolWithHalfRunnableCount.terminate();
        Assert.assertFalse("ThreadPool должен завершаться без ожидания невыполненых заданий", runnableCount == count.get());
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
        Assert.assertTrue("ThreadPool должен выполнить все задания после вызова terminate()", runnableCount == count.get());
    }

    private void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {
            throw new ThreadPoolException(e.getCause());
        }
    }
}