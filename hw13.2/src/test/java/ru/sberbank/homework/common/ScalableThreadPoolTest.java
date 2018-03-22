package ru.sberbank.homework.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ScalableThreadPoolTest {

    private final int MIN_POOL_SIZE = 1;
    private final int MAX_POOL_SIZE = 5;
    private ScalableThreadPool scalableThreadPool;

    @Before
    public void setUp() throws Exception {
        scalableThreadPool = new ScalableThreadPool(MIN_POOL_SIZE, MAX_POOL_SIZE);
    }

    @After
    public void tearDown() throws Exception {
        scalableThreadPool.shutdown();
    }


    @Test(timeout = 100)
    public void checkDoNothingIfNotStart() {
        AtomicInteger i = new AtomicInteger(0);
        scalableThreadPool.execute(() -> i.addAndGet(1));
        assertEquals(0, i.get());
    }

    @Test(timeout = 100)
    public void checkHappyPath() {
        AtomicInteger i = new AtomicInteger(0);
        IntStream.range(0, 10).boxed().forEach(e ->
                scalableThreadPool.execute(() -> i.addAndGet(1)));
        scalableThreadPool.start();
        wait(50);
        assertEquals(10, i.get());
    }

    @Test(timeout = 5_000)
    public void checkWorkerIsGrow() {
        ConcurrentHashMap<Thread, Integer> threadMap = new ConcurrentHashMap<>();
        generateTask(threadMap, 100);
        scalableThreadPool.start();
        wait(4000);
        assertEquals(5, threadMap.keySet().size());
    }

    @Test(timeout = 8_000)
    public void checkWorkerIsDecrease() {
        ConcurrentHashMap<Thread, Integer> threadMap = new ConcurrentHashMap<>();
        generateTask(threadMap, 100);
        scalableThreadPool.start();
        wait(7100);
        assertEquals(5, threadMap.keySet().size());
        assertEquals(4, threadMap.keySet().stream()
                .map(Thread::getState)
                .filter(Thread.State.TERMINATED::equals)
                .count());
    }

    @Test(timeout = 15_000)
    public void checkNewExecutionIsStarted() {
        ConcurrentHashMap<Thread, Integer> threadMap = new ConcurrentHashMap<>();
        generateTask(threadMap, 100);
        scalableThreadPool.start();
        wait(7100);
        generateTask(threadMap, 100_000);
        wait(4000);
        assertEquals(9, threadMap.keySet().size());
    }

    private void generateTask(ConcurrentHashMap<Thread, Integer> threadMap, int milliseconds) {
        IntStream.range(0, 100).boxed().forEach(e -> scalableThreadPool
                .execute(() -> {
                    try {
                        threadMap.put(Thread.currentThread(), 0);
                        TimeUnit.MILLISECONDS.sleep(milliseconds);
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();
                    }
                }));
    }

    private void wait(int millisecond) {
        try {
            TimeUnit.MILLISECONDS.sleep(millisecond);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}