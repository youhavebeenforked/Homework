package ru.sberbank.homework.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


public class FixedThreadPoolTest {
    private final int POOL_SIZE = 4;
    private FixedThreadPool fixedThreadPool;


    @Before
    public void setUp() throws Exception {
        fixedThreadPool = new FixedThreadPool(POOL_SIZE);
    }

    @After
    public void tearDown() throws Exception {
        fixedThreadPool.shutdown();
    }

    @Test(timeout = 100)
    public void checkDoNothingIfNotStart() {
        AtomicInteger i = new AtomicInteger(0);
        fixedThreadPool.execute(() -> i.addAndGet(1));
        assertEquals(0, i.get());
    }

    @Test(timeout = 100)
    public void checkHappyPath() {
        AtomicInteger i = new AtomicInteger(0);
        IntStream.range(0,10).boxed().forEach( e ->
                fixedThreadPool.execute(() -> i.addAndGet(1)));
        fixedThreadPool.start();
        wait(50);
        assertEquals(10, i.get());
    }

    @Test(timeout = 500)
    public void checkExecuteWhenWork() {
        AtomicInteger i = new AtomicInteger(0);
        IntStream.range(0,10).boxed().forEach( e ->
                fixedThreadPool.execute(() -> i.addAndGet(1)));
        fixedThreadPool.start();
        wait(50);
        assertEquals(10, i.get());

        IntStream.range(0,10).boxed().forEach( e ->
                fixedThreadPool.execute(() -> i.addAndGet(1)));
        wait(50);
        assertEquals(20, i.get());
    }

    private void wait(int millisecond){
        try {
            TimeUnit.MILLISECONDS.sleep(millisecond);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}