package ru.sberbank.homework.dergun;

import org.junit.Assert;
import org.junit.Test;

public class ScalableThreadPoolTest {
    @Test
    public void getCurrentSize() throws Exception {

    }

    @Test
    public void maxSizeThread() throws InterruptedException {
        final String monitor = "";
        ScalableThreadPool pool = new ScalableThreadPool(2, 8);

        final int[] flag = {0};
        pool.start();
        int max_size = 0;
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                }
                synchronized (monitor) {
                    flag[0]++;
                }
            });
        }
        while (flag[0] < 100) {
            max_size = Math.max(pool.getCurrentSize(), max_size);
            Thread.sleep(1);
        }
        Assert.assertEquals(8, max_size);
    }

    @Test
    public void minSizeThread() throws InterruptedException {
        final String monitor = "";
        ScalableThreadPool pool = new ScalableThreadPool(7, 18);

        final int[] flag = {0};
        pool.start();
        int min_size = 100;
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                }
                synchronized (monitor) {
                    flag[0]++;
                }
            });
        }
        while (flag[0] < 100) {
            min_size = Math.min(pool.getCurrentSize(), min_size);
            Thread.sleep(1);
        }
        Assert.assertEquals(7, min_size);
    }
}