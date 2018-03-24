package ru.sberbank.homework.dergun;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FixedThreadPoolTest {
    public int count = 0;
    @Test
    public void test1() throws InterruptedException {
        FixedThreadPool pool = new FixedThreadPool(2);
        pool.start();
        boolean flag = true;
        final Set<String> set = new HashSet<String>();
        final String monitor = "";
        for (int i = 0; i < 20; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(100);
//                        System.out.println(Thread.currentThread().getName());
                        synchronized (monitor) {
                            if (!set.contains(Thread.currentThread().getName())) {
                                set.add(Thread.currentThread().getName());
                                count++;
                            }
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            });
        }
//        while (flag) {}
        Thread.sleep(5000);
        System.out.println("Count threads = " + count);
    }

}