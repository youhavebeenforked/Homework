package ru.sberbank.homework.dergun;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FixedThreadPoolTest {
    public int count = 0;

    @Test
    public void countThreads() throws InterruptedException {
        FixedThreadPool pool = new FixedThreadPool(2);
        pool.start();
        final int[] flag = {0};
        final Set<String> set = new HashSet<>();
        final String monitor = "";
        for (int i = 0; i < 20; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(100);
                    synchronized (monitor) {
                        if (!set.contains(Thread.currentThread().getName())) {
                            set.add(Thread.currentThread().getName());
                            count++;
                        }
                        flag[0]++;
                    }
                } catch (InterruptedException ignored) {
                }
            });
        }
        while (flag[0] < 19) Thread.sleep(1);
        Assert.assertEquals(2, count);
        System.out.println("Count threads = " + count);
    }

    @Test
    public void correctWorkWait() throws InterruptedException {
        FixedThreadPool pool = new FixedThreadPool(2);
        pool.start();
        Thread.sleep(10000);
    }

    @Test
    public void testShutdown() throws InterruptedException {
        FixedThreadPool pool = new FixedThreadPool(2);
        pool.start();
        final int[] flag = {0};
        final String monitor = "";
        for (int i = 0; i < 20; i++) {
            pool.execute(() -> {
                try {
                    synchronized (monitor) {
                        flag[0]++;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            });
        }
        Thread.sleep(500);
        pool.shutdown();
        Assert.assertEquals(2, flag[0]);
    }

}