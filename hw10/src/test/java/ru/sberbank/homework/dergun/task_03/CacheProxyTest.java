package ru.sberbank.homework.dergun.task_03;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;


public class CacheProxyTest {
    @BeforeClass
    public static void clearAllFileInRoot() throws IOException {
        File directory = Paths.get("src", "main", "resources").toFile();
        FileUtils.cleanDirectory(directory);
    }
    @Test
    public void cacheInFileZip() throws Exception {
        CacheSettings settings = new CacheSettings();
        settings.setTypeFile(true);
        settings.setZip(true);
    }

    @Test
    public void cacheFibonachi() throws Exception {
        CacheSettings settings = new CacheSettings();
        settings.setTypeFile(true);
        CacheProxy cacheProxy = new CacheProxy();
        IFibonachi fibonachi = new Fibonachi();
        Assert.assertEquals(0, fibonachi.fib(0));
        Assert.assertEquals(5, fibonachi.fib(5));
        Assert.assertEquals(55, fibonachi.fib(10));
        IFibonachi fibonachi2 = (IFibonachi) cacheProxy.cache(fibonachi);

        Assert.assertEquals(0, fibonachi2.fib(0));
        Assert.assertEquals(5, fibonachi2.fib(5));
        Assert.assertEquals(5, fibonachi2.fib(5));
        Assert.assertEquals(55, fibonachi2.fib(10));
    }

    /**
     * Проверка на инкерментах, будет ли вызываться метод, или возьмется результат его вычислений из памяти
     * @throws Exception
     */
    @Test
    public void cacheSimpleTestMemory() throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        ISimpleTest test = new SimpleTest();
        test.increment();
        test.increment();
        test.increment();
        ISimpleTest testOnIncrements = (ISimpleTest) cacheProxy.cache(test);
        testOnIncrements.increment();
        testOnIncrements.increment();
        testOnIncrements.increment();
        Assert.assertEquals(4, testOnIncrements.getCount());
    }
    /**
     * Проверка на инкерментах, будет ли вызываться метод, или возьмется результат его вычислений из файла
     * Плюс зaдаем имя файла
     *
     * @throws Exception
     */
    @Test
    public void cacheSimpleTestFile() throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        ISimpleTest test = new SimpleTest();
        test.incrementFile();
        test.incrementFile();
        test.incrementFile();
        ISimpleTest testOnIncrements = (ISimpleTest) cacheProxy.cache(test);
        testOnIncrements.incrementFile();
        testOnIncrements.incrementFile();
        testOnIncrements.incrementFile();
    }

    /**
     * Проверка на инкерментах, будет ли вызываться метод, или возьмется результат его вычислений из файла
     * Плюс зaдаем имя файла
     *
     * @throws Exception
     */
    @Test
    public void cacheSimpleTestFileIgnoreArgument() throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        ISimpleTest test = new SimpleTest();
        ISimpleTest testOnIncrements = (ISimpleTest) cacheProxy.cache(test);
        testOnIncrements.setSomeArgument(10, "lala", 15);
        testOnIncrements.setSomeArgument(15, "lala", 20);
        Assert.assertEquals(1, testOnIncrements.getCount());
    }

    @Test
    public void cacheSimpleTestFileZip() throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        ISimpleTest test = new SimpleTest();
        ISimpleTest testOnIncrements = (ISimpleTest) cacheProxy.cache(test);
        testOnIncrements.zip(10, "abba", 15);
    }

    @Test
    public void cacheSimpleTestListSizeMin() throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        ISimpleTest test = new SimpleTest();
        ISimpleTest testOnIncrements = (ISimpleTest) cacheProxy.cache(test);
        List<Integer> list = testOnIncrements.returnListSize(10);
        Assert.assertEquals(5, list.size());
        list = testOnIncrements.returnListSize(10);
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void cacheSimpleTestListSize() throws Exception {
        CacheProxy cacheProxy = new CacheProxy();
        ISimpleTest test = new SimpleTest();
        ISimpleTest testOnIncrements = (ISimpleTest) cacheProxy.cache(test);
        List<Integer> list = testOnIncrements.returnListSize(4);
        Assert.assertEquals(3, list.size());
        list = testOnIncrements.returnListSize(4);
        Assert.assertEquals(3, list.size());
    }

}