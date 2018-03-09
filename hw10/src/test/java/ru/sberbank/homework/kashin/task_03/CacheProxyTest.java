package ru.sberbank.homework.kashin.task_03;

import org.junit.Assert;
import org.junit.Test;
import ru.sberbank.homework.kashin.task_02.TestPerson;

import static org.junit.Assert.*;

public class CacheProxyTest {
    private CacheProxy cacheInMemory = new CacheProxy();
    private CacheProxy cacheSerialize = new CacheProxy("storage");


    @Test
    public void cacheInMemory() {
        TestPerson expected = new TestPerson(10, "Ivan", true);

        TestPerson actual = (TestPerson) cacheInMemory.cache(expected);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void cacheSerialize() {
        TestPerson expected = new TestPerson(10, "Ivan", true);

        TestPerson actual = (TestPerson) cacheSerialize.cache(expected);

        Assert.assertEquals(expected, actual);
    }
}